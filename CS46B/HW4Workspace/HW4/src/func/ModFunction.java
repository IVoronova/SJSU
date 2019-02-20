package func;

public class ModFunction implements DoubleFunctionOfTwoInts{

	//returns x modulo y
	public double fOfXY(int x, int y) {
		
		if (y == 0) {
			y = 1;
		}
		
		return x % y;
	}

	
	public String getName() {
		return "Modulo";
	}

}
