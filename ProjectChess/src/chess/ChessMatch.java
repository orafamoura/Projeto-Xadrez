package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch { //nessa classe vai ter as regras do xadrez
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>(); //pecas na mesa
	private List<Piece> capturedPieces = new ArrayList<>(); // pecas capturadas
	
	public ChessMatch() {
		board = new Board(8, 8); // tamanho do tabuleiro
		turn = 1; //turno 1
		currentPlayer = Color.WHITE; // quem comeca
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public ChessPiece[][] getPieces(){ // retorna uma matriz de pecas de xadrez correspondentes a essa partida
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; //
		for (int i=0; i<board.getRows(); i++) { //percorremos a matriz de pecas do tabuleiro e pra cada peca fazemos um downcasting pra ChessPiece
			for (int j=0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j); // o codigo ta falando que para cada posicao i e j do meu tabuleiro, receba o board.piece i e j
			}
		}
		return mat;
	}
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){ // 
		Position position = sourcePosition.toPosition(); // converteu para matriz normal
		validateSourcePosition(position); //validando a posicao de origem
		return board.piece(position).possibleMoves(); // retorna os movimentos possiveis a partir da posicao de origem
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) { // posicao de origem e posicao de destino;
		Position source = sourcePosition.toPosition(); // convertemos para posicao da matriz
		Position target = targetPosition.toPosition();
		validateSourcePosition(source); // validamos se tem uma peca nessa posicao
		validateTargetPosition(source, target); // validando a posicao de destino
		Piece capturedPiece = makeMove(source, target); 
		
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("You can't put yourself in check");
		}
		
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		if(testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		} else {
			nextTurn(); // proximo turno
		}
		return (ChessPiece)capturedPiece; //downcasting para chessPiece, pois a peca capturada era do tipo Piece, como vimos em cima.
	}
	
	private Piece makeMove(Position source, Position target) { 
		Piece p = board.removePiece(source); // retiramos a peca na posicao de origem
		Piece capturedPiece = board.removePiece(target); // removemos a posivel peca na posicao target
		board.placePiece(p, target); //agora colocamos a p em target
		
		if(capturedPiece != null) { // remove pecas do tabuleiro e adiciona em pecas capturadas
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		
		return capturedPiece; 
	}
	
	private void undoMove(Position source, Position target, Piece capturedPiece) { // retorna a peca capturada
		Piece p = board.removePiece(target);
		board.placePiece(p, source);
		
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}
		
	private void validateSourcePosition(Position position) {
		if(!board.ThereIsAPiece(position)) { // ! se nao existir uma peca nessa posicao
			throw new ChessException("There is no piece on source position");
		}
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) { // se o jogador atual for diferente da peca e cor 
			throw new ChessException("The chosen piece is not yours");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("there is no possible moves for the chosen piece");
		}
	}
	
	private void validateTargetPosition(Position source, Position target) {
		if(!board.piece(source).possibleMove(target)) { // testamos se a posicao target, e uma possibleMove em relacao a posicao source
			throw new ChessException("The chosen piece can't move to target position");
		}
	}
	
	private void nextTurn() { // proximo turno
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE; // trocando o player no turno
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) { //  uma operacao de colocar pecas passando as posicoes na coordenadas  do xadrez
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece); // lista de pecas no tabuleiro
	}
	
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {
		List<Piece> list = piecesOnTheBoard.stream().filter(x ->((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Piece p : list) {
			if(p instanceof King) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("There is no " + color + "king on the board"); //essa excecao nao e pra acontecer, se acontecer tem algum problema no xadrez, por isso ela nao e tratada
	}
	
	private boolean testCheck(Color color) {
		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x ->((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList()); //lista de pecas do oponente
		for(Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			if(mat[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Color color) {
		if(!testCheck(color)) {
			return false;
		}
		List<Piece> list = piecesOnTheBoard.stream().filter(x ->((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for(Piece p : list) {
			boolean[][] mat = p.possibleMoves();
			for(int i=0; i<board.getRows();i++) {
				for(int j=0; j<board.getColumns();j++) {
					if(mat[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if(!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void initialSetup() { // responsavel por iniciar a partida de xadrez, por colocar as pecas no tabuleiro
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
       
//depois de criar o metodo ChessPosition conseguimos colocar as posicoes sem ser pela matriz
        placeNewPiece('b', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 8, new King(board, Color.BLACK));
	}	
}
