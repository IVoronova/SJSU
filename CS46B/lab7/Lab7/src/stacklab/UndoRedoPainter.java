package stacklab;

import java.util.*;


public class UndoRedoPainter extends Painter
{
	
	private Stack<Circle> history = super.getHistory();
	private Stack<Circle> undoHistory = super.getUndoHistory();
	
	public UndoRedoPainter(){
		super.setUndoButtonEnabled(false);
		super.setRedoButtonEnabled(false);
	}
	
	// Called when the user pushes the Undo button.
	void undo()
	{
		if(history.isEmpty())
			return;
		
		undoHistory.add(history.pop());
		super.setRedoButtonEnabled(true);
		
		if(history.isEmpty())
			super.setUndoButtonEnabled(false);
		
		repaint();
	}


	// Called when the user pushes the Redo button.
	void redo()
	{
		if(undoHistory.isEmpty())
			return;
		history.add(undoHistory.pop());
		
		if(undoHistory.isEmpty())
			super.setRedoButtonEnabled(false);
		
		if(!history.isEmpty())
			super.setUndoButtonEnabled(true);

		repaint();
	}
	
	
	public static void main(String[] args)
	{
		new UndoRedoPainter().setVisible(true);
	}
}
