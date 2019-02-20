package dotlab;


public class Dot 
{
	private static String[] 	LEGAL_COLOR_NAMES =
	{
		"RED", "YELLOW", "BLUE", "CYAN", "GREEN", "MAGENTA", "ORANGE", "BLACK"
	};


	private String 		colorName;
	private int			x;
	private int			y;
	private int 		radius;
	
	
	String getColorName()
	{
		return colorName;
	}


	int getX()
	{
		return x;
	}
	
	
	int getY()
	{
		return y;
	}
	
	
	int getRadius()
	{
		return radius;
	}
	
	
	Dot(String colorName, int x, int y, int radius)
	{
		// Only accept a legal color name.
		boolean found = false;
		for (String legalName: LEGAL_COLOR_NAMES)
		{
			if (colorName.equals(legalName))
			{
				found = true;
				break;
			}
		}
		if (!found)
			throw new IllegalArgumentException("Illegal color name " + colorName);
		
		this.colorName = colorName;
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	
	public String toString()
	{
		return "Dot: color=" + colorName + ", radius=" + radius + " at (" + x + ", " + y + ")";
	}
	
	
	public static void main(String[] args)
	{
		Dot d = new Dot("RED", 100, 100, 100);
		System.out.println(d);
	}
}
