package ics4ustart;

import java.util.Random;

public class Board {
	private Cell[][] board;
	private int rows;
	private int cols;

	public Board(int aRows, int aCols) {
		board = new Cell[aRows][aCols];
		rows = aRows;
		cols = aCols;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = new Cell(CellState.EMPTY); // no color
			}
		}
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getCols(){
		return cols;
	}
	public void place(CellState cell,int col) {
		for(int n=6;n>-1;n--) {
		CellState lecc = board[n][col].getState();
		if(lecc==CellState.EMPTY) {
			board[n][col].setState(cell);
			return;
			}
		}
	}
	public boolean rowLimit(int col) {
	CellState lecc = board[0][col].getState();
		if(lecc == CellState.EMPTY) {
			return false;
		}else {
			return true;
		}
	}
	public boolean isValid(int x, int y){
		return (x>=0 && x < rows) && (y>=0 && y < cols);
	}
	//public boolean winCheck(int[][] board, CellState player) {
		
	//}
	
	
	
		
	
	
	public void display() {
		System.out.println("BOARD");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.printf("%s ", board[i][j]);
			}
			System.out.println();
		}
	}
}
