package boardgame;

public abstract class Piece {

	protected Position position; //ela e protected pois e uma posicao simples de matriz, nao aparece na camada chess
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null; // coloco como nulo para afirmar que a posicao de uma peca recem criada nao foi colocada no tabuleiro ainda
	} // nao precisa colocar o null, sem colocar ele ja considera como null.

	protected Board getBoard() { //ele e protected pois e de uso interno.
		return board;
	}

	public abstract boolean[][] possibleMoves();
	
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()]; // metodo concreto utilizando um metodo abstrato
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves(); // mais um metodo padrao que depende de um metodo abstrato
		for(int i=0;i<mat.length;i++) {
			for(int j=0;j<mat.length; j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	
}
