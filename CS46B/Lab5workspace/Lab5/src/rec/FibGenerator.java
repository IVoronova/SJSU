package rec;

public class FibGenerator {
	
	private int[] callCounter;
	private int computeFibRecurse (int n) {
		callCounter[n]++;
		if( n == 1 || n == 2)
			return 1;
		
		return computeFibRecurse(n - 1) + computeFibRecurse(n - 2);
	}

	public int nthFib(int n) {
		
		callCounter = new int[n+1];
		return computeFibRecurse(n);
	}
	
	public void printCallCounter() {
		
		for(int n = 0; n < callCounter.length; n++) {
			System.out.println(callCounter[n] +" call to fib(" + n + ")");
		}
	}
	
	public static void main(String[] args) {
		System.out.println("STARTTING");
		FibGenerator gen = new FibGenerator();
		for (int x = 1; x <= 20; x++) {
			System.out.println(x + " Fib number = " + gen.nthFib(x));
		}
		gen.printCallCounter();
	}
}
