package application;

import chess.ChessPiece;

public class UI {

	public static void printBoard(ChessPiece[][] pieces) { //
		for (int i=0; i<pieces.length; i++) { // essa logica imprime o tabuleiro no formato adequado
			System.out.print((8 - i) + " "); //imprime do 8 ate o 1
			for(int j=0; j<pieces.length; j++) {
				printPiece(pieces[i][j]); //imprime a peca
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	private static void printPiece(ChessPiece piece) { // aqui e um metodo que imprime 1 peca
		if (piece == null) { // se a peca for igual a null, nao tinha uma peca nessa posicao do tabuleiro
			System.out.print("-"); 
		} else {
			System.out.print(piece); //caso contrario imprimo a peca
		}
		System.out.print(" ");
	
	} 
}
