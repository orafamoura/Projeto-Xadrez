package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch { //nessa classe vai ter as regras do xadrez

	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8); // tamanho do tabuleiro
		initialSetup();
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
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) { // posicao de origem e posicao de destino;
		Position source = sourcePosition.toPosition(); // convertemos para posicao da matriz
		Position target = targetPosition.toPosition();
		validateSourcePosition(source); // validamos se tem uma peca nessa posicao
		Piece capturedPiece = makeMove(source, target); 
		return (ChessPiece)capturedPiece; //downcasting para chessPiece, pois a peca capturada era do tipo Piece, como vimos em cima.
	}
	
	private Piece makeMove(Position source, Position target) { 
		Piece p = board.removePiece(source); // retiramos a peca na posicao de origem
		Piece capturedPiece = board.removePiece(target); // removemos a posivel peca na posicao target
		board.placePiece(p, target); //agora colocamos a p em target
		return capturedPiece; 
	}
	
	
	private void validateSourcePosition(Position position) {
		if(!board.ThereIsAPiece(position)) { // ! se nao existir uma peca nessa posicao
			throw new ChessException("There is no piece on source position");
		}
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) { //  uma operacao de colocar pecas passando as posicoes na coordenadas  do xadrez
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	
	private void initialSetup() { // responsavel por iniciar a partida de xadrez, por colocar as pecas no tabuleiro
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));
//depois de criar o metodo ChessPosition conseguimos colocar as posicoes sem ser pela matriz
        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
	
	
	
	
}
