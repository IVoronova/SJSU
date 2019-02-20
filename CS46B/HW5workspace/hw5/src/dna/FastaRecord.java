package dna;

//
// Fasta DNA record with only defline and sequence
// defline starts with ">"
//

public class FastaRecord implements DNARecord 
{	
	private String defline;
	private String sequence;
	//
	// Throws RecordGFormatException if the 1st char of the defline is  not '>'.
	//
	public FastaRecord(String defline, String sequence) throws RecordFormatException
	{
		if(defline.charAt(0) != '>' ) {
			throw new RecordFormatException("Bad first character in defline: saw " + defline.charAt(0) + ", excpexted >");
		}
		
		this.defline = defline;
		this.sequence = sequence;
	}
	
	
	public FastaRecord(FastqRecord fastqRec)
	{
		this.defline = ">" + fastqRec.getDefline().substring(1);
		this.sequence = fastqRec.getSequence();
		
	}


	
	public String getDefline() {
		
		return defline;
	}


	
	public String getSequence() {
		
		return sequence;
	}
	
	
	public boolean equals(Object x) {
		FastaRecord fasta = (FastaRecord) x;
		
		if(!this.defline.equals(fasta.defline))
			return false;
		
		return this.sequence.equals(fasta.sequence);
		
	}

	
	public int hashCode() {
		return defline.hashCode() + sequence.hashCode();
	}
}
