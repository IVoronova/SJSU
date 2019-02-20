package dotlab;

import java.io.*;


public class DotReader 
{
	private BufferedReader			br;
	
	
	public DotReader(BufferedReader br)
	{
		this.br = br;
	}
	

	public Dot readDot() throws IOException, DotException
	{
		// Read next line from reader.
		String line = br.readLine();
		
		// Return null if EOF.
		if (line == null)
			return null;
		
		// Split line into pieces.
		String[] pieces = line.split(",");
		
		// Check for exactly 4 pieces.
		if (pieces.length != 4)
		{
			String msg = "Wrong number of terms in line: " + line;
			throw new DotException(msg);
		}
		
		// Convert numeric pieces from strings to numbers.
		int x = Integer.parseInt(pieces[1]);
		int y = Integer.parseInt(pieces[2]);
		int r = Integer.parseInt(pieces[3]);
		
		// Build and return an instance of Dot.
		return new Dot(pieces[0], x, y, r);
	}
}
