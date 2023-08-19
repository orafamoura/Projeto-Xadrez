package chess;

import boardgame.Board;
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
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	
	private void initialSetup() {
		placeNewPiece('b', 6, new Rook(board,Color.WHITE));
		placeNewPiece('e', 8, new King(board,Color.BLACK));
		placeNewPiece('e', 1, new King(board,Color.WHITE));
	}
	
	
	
	
}
