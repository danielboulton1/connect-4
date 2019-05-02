package ics4ustart;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) throws InterruptedException {
		/*
		 * Scanner in = new Scanner(System.in); boolean facts=true; while(facts) {
		 * if(in.hasNextInt()) { int dRows = in.nextInt(); if (dRows<4) {
		 * System.out.print("input the # of rows. must be greater than 4."); }
		 * if(in.hasNextInt()) { int dCols = in.nextInt(); if (dCols<4) {
		 * System.out.print("input the # of rows. must be greater than 4."); } }}
		 */
		// Setup constants for the Board
		final int ROWS = 7;
		final int COLS = 7;

		// create the board
		Board board = new Board(ROWS, COLS);

		boolean done = false;
		String value = "";
		CellState p1 = CellState.P1;
		CellState p2 = CellState.P2;
		while (!done) {
			boolean limit = false;
			board.display();
			System.out.println("Player 1");
			while(!limit) {
				//note: always puts p1 & p2 in first row
			int column = getColumn();
			if (board.rowLimit(column)==false) {
			board.place(p1, column);
			board.display();
			limit=true;
			}else {
				System.out.println("That column is full");
			}
			}
			limit = false;
			
			System.out.println("Player 2");
			while(!limit) {
				int column = getColumn();
				if (board.rowLimit(column)==false) {
				board.place(p2, column);
				board.display();
				limit=true;
				}else {
					System.out.println("Outside of range");
				}
			board.display();
		}
	}
		}

	public static int getColumn() {
		boolean valid = false;
		Scanner in = new Scanner(System.in);
		int column = 0;
		while (valid != true) {
			System.out.println("Which Column?");
			String givenS = in.nextLine();
			valid = verify(givenS);
			if (valid == true) {
				 column = Integer.parseInt(givenS);
			}
			else {
				System.out.println("Invalid");
			}
			
		}
		return column;

	}
	private static boolean verify(String col) 
	{
		try 
		{
			int given = Integer.parseInt(col);
			if(given>= 0 && given <= 6) 
			{
				return true;
			}
		}
		catch(Exception E){
			System.out.println("Yous' a dummi Boi");
			return false;
		}
		finally{}
		return false;
	}
}
