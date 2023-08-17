package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces; //matriz de pecas
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; // instanciando uma matriz com linhas e colunas
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public Piece piece (int row, int column) { // vai me retornar a matriz pieces na linha row na coluna column
		if (!positionExists(row, column)) {
			throw new BoardException("Position not on the Board");
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position position) { // fazemos uma sobrecarga, com position para retornar a piece pela position
		if (!positionExists(position)) {
			throw new BoardException("Position not on the Board");
		}
		return pieces[position.getRow()][position.getColumn()]; 
	}
	
	public void placePiece(Piece piece, Position position) {
		if(TthereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position" + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;		
		 piece.position = position;	
	}
	
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(),position.getColumn());
	}
	
	public boolean TthereIsAPiece(Position position) {
		if(TthereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position" + position);
		}
		return piece(position) != null;
	}
	
	
}