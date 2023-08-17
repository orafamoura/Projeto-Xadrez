package boardgame;

public class Piece {

	protected Position position; //ela e protected pois e uma posicao simples de matriz, nao aparece na camada chess
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null; // coloco como nulo para afirmar que a posicao de uma peca recem criada nao foi colocada no tabuleiro ainda
	} // nao precisa colocar o null, sem colocar ele ja considera como null.

	protected Board getBoard() { //ele e protected pois e de uso interno.
		return board;
	}

	
	
	
	
}
