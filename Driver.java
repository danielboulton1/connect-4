package kiyan.zaman;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Driver {
	
	public static void main(String[] args) throws InterruptedException {

		// Setup constants for the Board
		final int ROWS = 7;
		final int COLS = 7;
		
		getMode(ROWS, COLS);
		
	}
	
	private static void getMode(int r, int c) throws InterruptedException {
		Scanner in = new Scanner(System.in);
		boolean done = false;
		int mode = 0;
		while(!done) {
			System.out.println("Singleplayer(1) or Multiplayer(2)?:");
			try {
				mode = Integer.parseInt(in.nextLine().trim());
			}catch(Exception e){
				System.out.println("Please enter an integer");
			}
			
			if(mode > 0 && mode < 3) {
				done = true;
			}
		}
		if(mode == 1) {
			singlePlayer(r, c);
		}else {
			multiPlayer(r, c);
		}
	}
	
	static void singlePlayer(int r, int c) throws InterruptedException {
		
		Board board = new Board(r, c);
		board.display();
		boolean done = false;
		
		int column = 0;
		int turn = 1;
		int row = 0;		
		while (!done) {
			Random rand= new Random();
			boolean v = false;
			boolean b = false;
			if(turn%2==0) {
				System.out.println();
				System.out.println("*************");
				System.out.println("AI's turn");
				System.out.println("*************");
				TimeUnit.MILLISECONDS.sleep(750);
				//checks for strategic opportunities
				int offPos = Board.checkOffense();
				int defPos = Board.checkDefense();
				//if offensive position or secondarily a defensive is found, places there, else chooses randomly
				if(offPos != -1) {
					Board.placePiece2(offPos, Board.findEmptyRow(offPos));
				}else if(defPos!=-1){
					Board.placePiece2(defPos, Board.findEmptyRow(defPos));
				}else {
					boolean bool = false;
					while(!bool) {
						column = rand.nextInt(7);
						row = Board.findEmptyRow(column);
						if(row==-1) {
							
						}else {
							bool = true;
						}
					}
					Board.placePiece2(column, row);
				}
		
				board.display();
				v = Board.checkWin();
				b = Board.checkDiagonalWin();
				if(v==true || b==true) {
					System.out.println("AI wins!");
					done = true;
				}
			}else {
				System.out.println();
				System.out.println("*************");
				System.out.println("Player 1's turn");	
				System.out.println("*************");
				column = getColumn(c);
				board.placePiece1(column, Board.findEmptyRow(column));
				
				board.display();
				v = Board.checkWin();
				b = Board.checkDiagonalWin();
				if(v==true || b==true) {
					System.out.println("Player 1 wins!");
					done = true;
				}
			}
				
			
			turn++;
		}
	}

	private static void multiPlayer(int r, int c) {
	
		Board board = new Board(r, c);
		board.display();
		boolean done = false;
				

		int column = 0;
		int row = 0;
		int turn = 1;
				
		while (!done) {
			boolean v = false;
			boolean b = false;
			if(turn%2==0) {
				System.out.println("Player 2's turn");
			}else {
				System.out.println("Player 1's turn");			
			}
					
			column = getColumn(c);
			row = Board.findEmptyRow(column);
			if(turn%2==0) {
				Board.placePiece2(column, row);
			}else {
				board.placePiece1(column, row);
			}
			board.display();
			v = Board.checkWin();
			b = Board.checkDiagonalWin();
			if(v==true || b==true) {
				if(turn%2==0) {
					System.out.println("Player 2 wins!");
				}else {
					System.out.println("Player 1 wins!");
				}
				done = true;
				}
			turn++;
		}
	}
	
	@SuppressWarnings("resource")
	private static int getColumn(int c) {
		boolean valid = false;
		int column = 0;
		Scanner in = new Scanner(System.in);
		while(!valid) {
			System.out.println("Which Column? (1-7): ");
			try {
				column = Integer.parseInt(in.nextLine().trim());
			}catch(Exception e){
				System.out.println("Please enter an integer from 1-7");
			}
			
			if(column > 0 && column < c + 1 && Board.findEmptyRow(column-1) != -1) {
				valid = true;
			}
		}
		return column - 1;
	}
	
}
