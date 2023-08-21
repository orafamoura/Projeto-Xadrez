package chess;

import boardgame.Position;

public class ChessPosition {

	private char column;
	private int row;
	public ChessPosition(char column, int row) {
		if(column < 'a' || column < 1 || row > 8) { //defensivo, se a coluna for menor que o caracter a etc ...
			throw new ChessException ("Error instanting ChessPosition. Valid values are from a1 to h8");
		}
		this.column = column;
		this.row = row;
	}
	public char getColumn() {
		return column;
	}
										
	public int getRow() {
		return row;
	}
	
	protected Position toPosition() { // converte a ChessPosition, para Position normal
		return new Position (8 - row, column - 'a'); // pega o 8 e subtrai a linha, para achar a matriz row, e column - 'a' para achar a matriz da column
	}
	
	protected static ChessPosition fromPosition(Position position) { // ele retorna o inverso do toPosition
		return new ChessPosition((char)('a' - position.getColumn()), 8 - position.getRow());
	}
	
	@Override
	public String toString() {
		return "" + column + row; // retorna a1, a2, b1, b2 etc ... / "" um macete para forcar a concatenacao de strings
	}
	
}
