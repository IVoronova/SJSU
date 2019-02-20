package cast;

public class Primitives {

	public static void dumpMaxValues() {
		System.out.println("Byte max " +     Byte.MAX_VALUE);
		System.out.println("Short max " +   Short.MAX_VALUE);
		System.out.println("Int max " +   Integer.MAX_VALUE);
		System.out.println("Long max " +     Long.MAX_VALUE);
		System.out.println("Float max " +   Float.MAX_VALUE);
		System.out.println("Double max " + Double.MAX_VALUE);
	}
	
	public static void main(String[] args) {
		dumpMaxValues();
		
		//long to int
		long l = Long.MAX_VALUE;
		int i = (int)l;
		System.out.println("Long to int: " + l + ", " + i);
		
		//long - 5 to int
		long l2 = Long.MAX_VALUE - 5;
		int i2 = (int)l2;
		System.out.println("Long  to int: " + l2 + ", " + i2);
		
		//int to long
		int i3 = Integer.MAX_VALUE;
		long l3 = (long)i3;
		System.out.println("Int to long: " + i3 + ", " + l3);
		
		//byte 100 to double
		byte b = 100;
		double d = (double)b;
		System.out.println("byte to double: " + b + ", " + d);
		
		//double to byte
		double d2 = 45.67;
		byte b2 = (byte)d2;
		System.out.println("Double to byte: " + d2 + ", " + b2);
		
		//double to byte
		double d3 = 456.789;
		byte b3 = (byte)d3;
		System.out.println("Double to byte: " + d3 + ", " + b3);
		
		//float to long
		float f = 12345.6789f;
		long l4 = (long)f;
		System.out.println("Float to long: " + f + ", " + l4 );
		
		//float to long
		float f2 = Float.MAX_VALUE;
		long l5 = (long) f2;
		System.out.println("Float to long: " + f2 + ", " + l5);
		
		//long to float
		//using same long as before
		float f3 = (float)l;
		System.out.println("Long to float: " + l + ", " + f3);
		
	}
}
