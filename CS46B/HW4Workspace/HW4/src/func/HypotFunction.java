package func;

public class HypotFunction implements DoubleFunctionOfTwoInts{

	//returns hypotenuse of right triangle with x and y as legs
	public double fOfXY(int x, int y) {
		
		return Math.hypot(x,y);
	}

	
	public String getName() {
		
		return "Hypotenuse";
	}

}
