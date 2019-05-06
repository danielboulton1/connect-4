package kiyan.zaman;

public class Board {
	
	private static Cell[][] board;
	private static int rows;
	private static int cols;
	
	static CellState player1 = CellState.P1;
	static CellState player2 = CellState.P2;
	static CellState empty = CellState.EMPTY;
	
	public Board(int aRows, int aCols) {
		board = new Cell[aRows][aCols];
		rows = aRows;
		cols = aCols;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = new Cell(empty); // no color
			}
		}
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getCols(){
		return cols;
	}
	
	public static int findEmptyRow(int column) {
		for(int i = rows-1; i>=0; i--) {
			if(board[i][column].getState() == empty) {
				return i;
			}
		}
		return -1;
	}
	
	public void placePiece1(int column, int row) {
		board[row][column].setState(player1);
	}
	
	public static void placePiece2(int column, int row) {
		board[row][column].setState(player2);
	}
	
	public static boolean checkWin() {
		for(int i = rows; i>0; i--) {
			for(int j = 0; j<cols-3; j++) {
				//checks for horizontal W's
				if(board[i-1][j].getState() != empty && board[i-1][j].getState() == board[i-1][j+1].getState() && board[i-1][j].getState() == board[i-1][j+2].getState() && board[i-1][j].getState() == board[i-1][j+3].getState()) {
					return true;
				}
				//vertical W's
				if(board[j][i-1].getState() != empty && board[j][i-1].getState() == board[j+1][i-1].getState() && board[j][i-1].getState() == board[j+2][i-1].getState() && board[j][i-1].getState() == board[j+3][i-1].getState()) {
					return true;
				}
				
			}
		}
		return false;
	}

	public static int checkOffense() {
		
		for(int i = rows; i>0; i--) {
			for(int j = 0; j<cols-3; j++) {
				//checks for potential horizontal W's
				if(board[i-1][j].getState() == player2 && player2 == board[i-1][j+1].getState() && player2 == board[i-1][j+2].getState() && findEmptyRow(j+3) == i-1) {
					return j+3;
				}else if(board[i-1][j+3].getState() == player2 && player2 == board[i-1][j+1].getState() && player2 == board[i-1][j+2].getState() && findEmptyRow(j) == i-1) {
					return j;
				}else if(board[i-1][j].getState() == player2 && player2 == board[i-1][j+3].getState() && player2 == board[i-1][j+2].getState() && findEmptyRow(j+1) == i-1) {
					return j+1;
				}else if(board[i-1][j].getState() == player2 && player2 == board[i-1][j+1].getState() && player2 == board[i-1][j+3].getState() && findEmptyRow(j+2) == i-1) {
					return j+2;
				}
				//potential vertical W's
				if(board[j+3][i-1].getState() == player2 && player2 == board[j+1][i-1].getState() && board[j+3][i-1].getState() == board[j+2][i-1].getState() && findEmptyRow(i-1) == j) {
					return i-1;
				}
				
			}
		}
		//checks for potential Top-Down Diagonal W's
		for(int i = 0; i<rows-3; i++) {
			for(int j = 0; j<cols-3; j++) {
				if(board[i][j].getState() == player2 && player2 == board[i+1][j+1].getState() && player2 == board[i+2][j+2].getState() && findEmptyRow(j+3) == i+3) {
					return j+3;
				}else if(board[i][j].getState() == player2 && player2 == board[i+1][j+1].getState() && player2 == board[i+3][j+3].getState() && findEmptyRow(j+2) == i+2) {
					return j+2;
				}else if(board[i][j].getState() == player2 && player2 == board[i+2][j+2].getState() && player2 == board[i+3][j+3].getState() && findEmptyRow(j+1) == i+1) {
					return j+1;
				}else if(board[i+1][j+1].getState() == player2 && player2 == board[i+2][j+2].getState() && player2 == board[i+3][j+3].getState() && findEmptyRow(j) == i) {
					return j;
				}
			}
		}
		//checks for potential Bottom-Up Diagonal W's
		for(int i = rows-1; i>rows-5; i--) {
			for(int j = 0; j<cols-3; j++) {
				if(board[i][j].getState() == player2 && player2 == board[i-1][j+1].getState() && player2 == board[i-2][j+2].getState() && findEmptyRow(j+3) == i-3) {
					return j+3;
				}else if(board[i][j].getState() == player2 && player2 == board[i-1][j+1].getState() && player2 == board[i-3][j+3].getState() && findEmptyRow(j+2) == i-2) {
					return j+2;
				}else if(board[i][j].getState() == player2 && player2 == board[i-2][j+2].getState() && player2 == board[i-3][j+3].getState() && findEmptyRow(j+1) == i-1) {
					return j+1;
				}else if(board[i-1][j+1].getState() == player2 && player2 == board[i-2][j+2].getState() && player2 == board[i-3][j+3].getState() && findEmptyRow(j) == i) {
					return j;
				}
			}
		}
		return -1;
	}

	public static int checkDefense() {
		
		for(int i = rows; i>0; i--) {
			for(int j = 0; j<cols-3; j++) {
				//checks for potential horizontal W's
				if(board[i-1][j].getState() == player1 && player1 == board[i-1][j+1].getState() && player1 == board[i-1][j+2].getState() && findEmptyRow(j+3) == i-1) {
					return j+3;
				}else if(board[i-1][j+3].getState() == player1 && player1 == board[i-1][j+1].getState() && player1 == board[i-1][j+2].getState() && findEmptyRow(j) == i-1) {
					return j;
				}else if(board[i-1][j].getState() == player1 && player1 == board[i-1][j+3].getState() && player1 == board[i-1][j+2].getState() && findEmptyRow(j+1) == i-1) {
					return j+1;
				}else if(board[i-1][j].getState() == player1 && player1 == board[i-1][j+1].getState() && player1 == board[i-1][j+3].getState() && findEmptyRow(j+2) == i-1) {
					return j+2;
				}
				//potential vertical W's
				if(board[j+3][i-1].getState() == player1 && player1 == board[j+1][i-1].getState() && player1 == board[j+2][i-1].getState() && findEmptyRow(i-1) == j) {
					return i-1;
				}
				
			}
		}
		//checks for potential Top-Down Diagonal W's
		for(int i = 0; i<rows-3; i++) {
			for(int j = 0; j<cols-3; j++) {
				if(board[i][j].getState() == player1 && player1 == board[i+1][j+1].getState() && player1 == board[i+2][j+2].getState() && findEmptyRow(j+3) == i+3) {
					return j+3;
				}else if(board[i][j].getState() == player1 && player1 == board[i+1][j+1].getState() && player1 == board[i+3][j+3].getState() && findEmptyRow(j+2) == i+2) {
					return j+2;
				}else if(board[i][j].getState() == player1 && player1 == board[i+2][j+2].getState() && player1 == board[i+3][j+3].getState() && findEmptyRow(j+1) == i+1) {
					return j+1;
				}else if(board[i+1][j+1].getState() == player1 && player1 == board[i+2][j+2].getState() && player1 == board[i+3][j+3].getState() && findEmptyRow(j) == i) {
					return j;
				}
			}
		}
		//checks for potential Bottom-Up Diagonal W's
		for(int i = rows-1; i>rows-5; i--) {
			for(int j = 0; j<cols-3; j++) {
				if(board[i][j].getState() == player1 && player1 == board[i-1][j+1].getState() && player1 == board[i-2][j+2].getState() && findEmptyRow(j+3) == i-3) {
					return j+3;
				}else if(board[i][j].getState() == player1 && player1 == board[i-1][j+1].getState() && player1 == board[i-3][j+3].getState() && findEmptyRow(j+2) == i-2) {
					return j+2;
				}else if(board[i][j].getState() == player1 && player1 == board[i-2][j+2].getState() && player1 == board[i-3][j+3].getState() && findEmptyRow(j+1) == i-1) {
					return j+1;
				}else if(board[i-1][j+1].getState() == player1 && player1 == board[i-2][j+2].getState() && player1 == board[i-3][j+3].getState() && findEmptyRow(j) == i) {
					return j;
				}
			}
		}
		return -1;
	}
	
	public static boolean checkDiagonalWin() {
		
		//checks for Top-Down Diagonal W's
		for(int i = 0; i<rows-3; i++) {
			for(int j = 0; j<cols-3; j++) {
				if(board[i][j].getState() != empty && board[i][j].getState() == board[i+1][j+1].getState() && board[i][j].getState() == board[i+2][j+2].getState() && board[i][j].getState() == board[i+3][j+3].getState()) {
					return true;
				}
				
				
			}
		}
		//Bottom-Up Diagonal W's
		for(int i = rows-1; i>rows-5; i--) {
			for(int j = 0; j<cols-3; j++) {
				
				if(board[i][j].getState() != empty && board[i][j].getState() == board[i-1][j+1].getState() && board[i][j].getState() == board[i-2][j+2].getState() && board[i][j].getState() == board[i-3][j+3].getState()) {
					return true;
				}
				
				
				
			}
		}
		return false;
	}
	
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
