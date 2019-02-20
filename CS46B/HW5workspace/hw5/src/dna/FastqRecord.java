package dna;

//
// FastqRecord contains the defline, sequence, and quality string
// defline starts with "@"
//


public class FastqRecord implements DNARecord 
{
	private String defline;
	private String sequence;
	private String quality;
	
	//
	// Throws a RecordFormatException if the first character of defline is not @
	//
	public FastqRecord(String defline, String sequence, String quality) throws RecordFormatException
	{
		if(defline.charAt(0) != '@' ) {
			throw new RecordFormatException("Bad first character in defline: saw " + defline.charAt(0) + ", excpexted @");
		}
		
		this.defline = defline;
		this.sequence = sequence;
		this.quality = quality;
	}
	
	
	
	public String getDefline() {
		
		return defline;
	}

	
	public String getSequence() {
		
		return sequence;
	}

	
	public boolean equals (Object x) {
		
		FastqRecord fastq = (FastqRecord) x;
		
		if(!this.defline.equals(fastq.defline))
			return false;
		if(!this.sequence.equals(fastq.sequence))
			return false;
		
		return this.quality.equals(fastq.quality);
	}
	
	//
	// Return true if quality contains at least one '!' char
	// or at least one ‘#’ char.
	//
	public boolean qualityIsLow()
	{
		if(quality.contains("!")) {
			return true;
		}
		
		return quality.contains("#");
	}
	
	
	
	public int hashCode()
	{
		return defline.hashCode() + sequence.hashCode() + quality.hashCode();
	}


	


	
}
