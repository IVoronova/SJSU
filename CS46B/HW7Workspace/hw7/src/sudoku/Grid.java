package sudoku;

import java.util.*;


public class Grid 
{
	private int[][]						values;
	

	
	//
	// Constructs a Grid instance from a string[] as provided by TestGridSupplier.
	// See TestGridSupplier for examples of input.
	// Dots (empty spaces) in input strings represent 0s in values[][].
	//
	public Grid(String[] rows)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
		{
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i=0; i<9; i++)
			{
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
	}
	
	
	
	public String toString()
	{
		String s = "";
		for (int j=0; j<9; j++)
		{
			for (int i=0; i<9; i++)
			{
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char)('0' + n);
			}
			s += "\n";
		}
		return s;
	}


	//
	// Copy ctor. Duplicates its source. You’ll call this 9 times in next9Grids.
	//
	Grid(Grid src)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
			for (int i=0; i<9; i++)
				values[j][i] = src.values[j][i];
	}
	
	
	
	//
	// Finds an empty member of values[][]. Returns an array list of 9 grids that look like the current grid,
	// except the empty member contains 1, 2, 3 .... 9. Returns null if the current grid is full. 
	//
	
	public ArrayList<Grid> next9Grids()
	{		
		int xOfNextEmptyCell = -1;
		int yOfNextEmptyCell = -1;

		// Find x,y of an empty cell.
		for(int x = 0; x < 9; x++) {
			for(int y = 0; y < 9; y++) {
				if(values[x][y] == 0) {
					xOfNextEmptyCell = x;
					yOfNextEmptyCell = y;
					break;
				}
			}
			if(xOfNextEmptyCell >= 0 || yOfNextEmptyCell >= 0)
				break;
		}
		
		if(xOfNextEmptyCell < 0 || yOfNextEmptyCell < 0)
			return null;

		//Array for the next 9 possible grids
		ArrayList<Grid> nextGrids = new ArrayList<Grid>();

		// Create 9 new grids and adds to nextGrids
		for(int i = 1; i < 10; i++) {
			Grid g = new Grid(this);
			g.values[xOfNextEmptyCell][yOfNextEmptyCell] = i;
			nextGrids.add(g);
			
		}
		
		return nextGrids;
	}
	
	// checks if the array of 9 numbers contains repeats, returns true if no repeats
	private boolean containsNoneZeroRepeats(int[] line) {
		for(int i = 0; i < 9; i++) {
			if( line[i] != 0) {
				for(int j = i + 1; j < 9; j++) {
					if(line[j] != 0 && line[i] == line[j]) {
						
						return true;
					}
			}
			}
		}
		return false;
	}
	
	
	//
	// Returns true if this grid is legal. A grid is legal if no row, column, or
	// 3x3 block contains a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.
	//
	public boolean isLegal()
	{
		// Check every row.
		
		for(int row = 0; row < 9; row++) {
			if(containsNoneZeroRepeats(values[row]))
				return false;
			
			
		}

		// Check every column.
		for(int y = 0; y < 9; y++) {
			int position = 0;
			int[] column = new int[9];
			for(int x = 0; x < 9; x++) {
				column[position] = values[x][y];
				position++;
			}
			if(containsNoneZeroRepeats(column)) 
				return false;
			
		}
		// Check every block.
		for(int x = 0; x < 7; x += 3) {
			for(int y = 0; y < 7; y += 3) {
				int position = 0;
				int[] block = new int[9];
				for(int xi = x; xi < x + 3; xi++) {
					for(int yi = y; yi < y + 3; yi++) {
						block[position] = values[xi][yi];
						position++;
					}
				}
				if(containsNoneZeroRepeats(block))
					return false;
				
			}
		}
		// All rows/cols/blocks are legal.
		return true;
	}

	
	
	//
	// Returns true if every cell member of values[][] is a digit from 1-9.
	//
	public boolean isFull()
	{
		for(int x = 0; x < 9; x++) {
			for(int y = 0; y < 9; y++) {
				if(values[x][y] == 0) {
					return false;
				}
			}
		}
		
		return true;
	}
	

	//checks if two grids are the same
	public boolean equals(Object x)
	{
		Grid that = (Grid) x;
		for(int yCoord = 0; yCoord < 9; yCoord++) {
			for(int xCoord = 0; xCoord < 9; xCoord++) {
				if(this.values[xCoord][yCoord] != that.values[xCoord][yCoord]) 
					return false;
				
			}
		}
		return true;
	}
	
	/*
	public static void main(String[] args) {
		Grid test1 = TestGridSupplier.getSolution1();
		ArrayList<Grid> test2 = test1.next9Grids();
		System.out.println(test1.isLegal());
		
	}
		
	*/
}
