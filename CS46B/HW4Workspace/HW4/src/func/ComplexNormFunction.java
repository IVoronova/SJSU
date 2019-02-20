package func;

public class ComplexNormFunction implements DoubleFunctionOfTwoInts {

	
	public double fOfXY(int x, int y) {
		
		Complex c = new Complex(x,y);
		return c.norm();
	}

	
	public String getName() {
		
		return "Complex Norm";
	}

}
