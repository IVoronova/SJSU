package dna;

import java.io.*;


//
// Reads lines from a BufferedReader and builds a FastqRecord.
//


public class FastqReader 
{
	
	private BufferedReader theBufferedReader;
	
	public FastqReader(BufferedReader bf) {
		theBufferedReader = bf;
	}
	
	// Returns next record in the file, or null if EOF (end-of-file).
	public FastqRecord readRecord() throws IOException, RecordFormatException
	{
		String defline = theBufferedReader.readLine();
		 if( defline == null)
			 return null;
		
		String sequence = theBufferedReader.readLine();
		String plus = theBufferedReader.readLine();
		
		if(!plus.equals("+"))
			throw new RecordFormatException("Bad line 4: saw " + plus + ", excpected +");
		
		String quality = theBufferedReader.readLine();
		
		return new FastqRecord(defline, sequence, quality);

	}
}
