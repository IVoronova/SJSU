package func;

public class Complex 
{
	private double			real;
	private double			imaginary;
	
	
	
	public Complex(double real, double imaginary)
	{
		this.real = real;
		this.imaginary = imaginary;
	}
	
	
	public Complex(Complex source)
	{
		real = source.real;
		imaginary = source.imaginary;
	}
	
	
	// Getter method.
	public double getReal()
	{
		return real;
	}
	

	// Getter method.
	public double getImaginary()
	{
		return imaginary;
	}
	
	
	
	public static Complex add(Complex c1, Complex c2)
	{
		return new Complex(c1.real + c2.real, c1.imaginary + c2.imaginary);
	}
	
	
	
	public static Complex multiply(Complex c1, Complex c2)
	{
		double a = c1.real;
		double b = c1.imaginary;
		double c = c2.real;
		double d = c2.imaginary;
		
		return new Complex((a*c) - (b*d), (a*d) + (b*c));

	}
	
	
	
	public double norm()
	{
		return Math.hypot(real, imaginary);
	}
}
