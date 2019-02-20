package func;

public class ComplexSquaredNormFunction implements DoubleFunctionOfTwoInts{

	
	public double fOfXY(int x, int y) {
		
		Complex c = new Complex(x,y);
		Complex m = Complex.multiply(c, c);
		
		return m.norm();
	}

	
	public String getName() {

		return "Complex Squared Norm";
	}

}
