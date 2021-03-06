package rec;

public class FactorialGenerator {
	
	private double computeFactorialRecurse(int n) {
		
		assert n >= 0 : "Illegal n: " + n;
		
		if(n == 0) {
			return 1;
		}
		
		
		return n * computeFactorialRecurse(n - 1);
		
	}

	public double nthFactorial(int n) {
		
		return computeFactorialRecurse(n);
	}
	
	public static void main(String[] args) {
		FactorialGenerator fGenerator = new FactorialGenerator();
		System.out.println("Factorial of -1 = " + fGenerator.nthFactorial(6));
		
		/*for(int x = 1; x <= 32 ; x++) {
			System.out.println("Factorial of " + x +" = " + fGenerator.nthFactorial(x));
		}
		*/
		//System.out.println(Long.MAX_VALUE);
	}
}
