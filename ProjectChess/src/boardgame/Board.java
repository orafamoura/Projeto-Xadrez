package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces; //matriz de pecas
	
	public Board(int rows, int columns) {
		if (rows < 1 || columns < 1) { // defensiva, quantidade de linhas e colunas tem que ser maior que 1
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
		if (!positionExists(row, column)) { // defensivo, se a posicao nao existe ...
			throw new BoardException("Position not on the Board");
		}
		return pieces[row][column];
	}
	
	public Piece piece(Position position) { // fazemos uma sobrecarga, com position para retornar a piece pela position
		if (!positionExists(position)) { // defensivo, se a posicao nao existe ...
			throw new BoardException("Position not on the Board");
		}
		return pieces[position.getRow()][position.getColumn()]; 
	}
	
	public void placePiece(Piece piece, Position position) { //ele vai ser responsavel por colocar a (piece na position do tabuleiro)
		if(ThereIsAPiece(position)) { // defensivo, se ja existe uma peca nessa posicao ...
			throw new BoardException("There is already a piece on position" + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece; //na linha position.row e na position.column e vai atribuir a peca com argumento = piece
		 piece.position = position; // essa peca nao esta mais na posicao null, a posicao da peca e acessivel diretamente por estar como protected em class piece
	}
	
	private boolean positionExists(int row, int column) {  // fica mais facil testar pela linha e coluna
		return row >= 0 && row < rows && column >= 0 && column < columns; 
	}
	
	public boolean positionExists(Position position) { //
		return positionExists(position.getRow(),position.getColumn()); // Reaproveitando o metodo de cima
	}
	
	public boolean ThereIsAPiece(Position position) { 
		if (!positionExists(position)) { // antes de testar o ThereIsAPiece ele testa se a posicao existe
			throw new BoardException("Position not on the Board");
		}
		return piece(position) != null; // testando se tem uma peca nessa posicao
	}
	
	
}
