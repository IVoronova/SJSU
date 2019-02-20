package sudoku;

import java.util.*;


public class Solver 
{
	private Grid					    		problem;
	private ArrayList<Grid>				solutions;
	
	
	public Solver(Grid problem)
	{
		this.problem = problem;
	}
	
	//solves a given sudoku grid
	public void solve()
	{
		solutions = new ArrayList<>();
		solveRecurse(problem);
	}
	
		
	
	//
	// Standard backtracking recursive solver.
	//
	private void solveRecurse(Grid grid)
	{		
		Evaluation eval = evaluate(grid);
		
		if (eval == Evaluation.ABANDON)
		{
			// Abandon evaluation of this illegal board.
			return;
		}
		// if grid is full and legal solution is found and it is added to solutions array
		else if (eval == Evaluation.ACCEPT)
		{
			solutions.add(grid);
			
			return;
		}
		//grid is legal but no full therefore it continues to look for solutions
		else
		{
			ArrayList<Grid> nextGrids = grid.next9Grids();
			for(Grid g: nextGrids) {
				solveRecurse(g);
			}
		}
	}
	
	// evaluates if a grid is legal and full
	public Evaluation evaluate(Grid grid)
	{
		if(!grid.isLegal()) //grid is illegal
			return Evaluation.ABANDON;
		
		if(grid.isFull())//grid is legal and full
			return Evaluation.ACCEPT;
		
		return Evaluation.CONTINUE;// grid is legal but not full
	}

	
	public ArrayList<Grid> getSolutions()
	{
		return solutions;
	}
	
	
	public static void main(String[] args)
	{
		Grid g = TestGridSupplier.getPuzzle2();
		Solver solver = new Solver(g);
		System.out.println("Will solve\n" + g);
		solver.solve();
		System.out.println("Solutions:");
		
		if(solver.getSolutions() == null) {
			System.out.println("No solutions to given grid");
		}
		for(Grid grid: solver.getSolutions()) {
			System.out.println(grid);
			System.out.println(grid.equals(TestGridSupplier.getSolution2()));
			System.out.println();
		}
		
	}
}
