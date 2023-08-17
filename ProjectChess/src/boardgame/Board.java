package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces; //matriz de pecas
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns]; // instanciando uma matriz com linhas e colunas
	}
	
	public int getRows() {
		return rows;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public int getColumns() {
		return columns;
	}
	
	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public Piece piece (int row, int column) { // vai me retornar a matriz pieces na linha row na coluna column
		return pieces[row][column];
	}
	
	public Piece piece(Position position) { // fazemos uma sobrecarga, com position para retornar a piece pela position
		return pieces[position.getRow()][position.getColumn()]; 
	}
	
	public void placePiece(Piece piece, Position position) {
		pieces[position.getRow()][position.getColumn()] = piece;		
		 piece.position = position;	
	}
	
	
	
}
