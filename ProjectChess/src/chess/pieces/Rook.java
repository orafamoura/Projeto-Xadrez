package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0,0);
		
		//above  --  enquanto a posicao for valida e estiver vazia
		p.setValues(position.getRow() - 1, position.getColumn()); // posicao p recebe a posicao da peca, com a linha acima(-1) e a coluna no mesmo lugar
		while(getBoard().positionExists(p) && !getBoard().ThereIsAPiece(p)) { // enquanto a posicao p existir e nao tiver uma peca
			mat[p.getRow()][p.getColumn()] = true; // entramos na matriz mat e marcamos como true
			p.setRow(p.getRow() - 1); //aqui anda mais 1 pra cima
		}
		if(getBoard().positionExists(p)&& isThereOpponentPiece(p)) { // se posicao existente p for verdadeira, e onde tem um inimigo verdadeiro tambem 
			mat[p.getRow()][p.getColumn()] = true; // marcar como true
		}
		//left
		p.setValues(position.getRow(), position.getColumn() - 1);
		while(getBoard().positionExists(p) && !getBoard().ThereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if(getBoard().positionExists(p)&& isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//right
		p.setValues(position.getRow(), position.getColumn() + 1);
		while(getBoard().positionExists(p) && !getBoard().ThereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
			if(getBoard().positionExists(p)&& isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		//below
		p.setValues(position.getRow() + 1, position.getColumn());
		while(getBoard().positionExists(p) && !getBoard().ThereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setRow(p.getRow() + 1);
		}
		if(getBoard().positionExists(p)&& isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}	
			
			
			
		return mat;
	}
	
	
	
	
}
