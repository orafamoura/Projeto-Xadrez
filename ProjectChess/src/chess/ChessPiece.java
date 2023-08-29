package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

	private Color color;
	private int moveCount;
	

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	public void increasedMoveCount() {
		moveCount++;
	}
	
	public void decreaseMoveCount() {
		moveCount--;
	}
	
	public ChessPosition getChessPosition() { //a classe position em piece e protected, mas com esse get podemos acessa-lo
		return ChessPosition.fromPosition(position);
	}
	
	protected boolean isThereOpponentPiece(Position position) { // se existe uma peca adversaria em uma dada posicao
		ChessPiece p = (ChessPiece)getBoard().piece(position); //downcasting pra ChessPiece
		return p != null && p.getColor() != color; // testando se o p e diferente de nulo // get color e diferente da outra peca
	}
	
	
	
}
