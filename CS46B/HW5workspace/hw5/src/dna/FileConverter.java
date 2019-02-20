package dna;

import java.io.*;
import java.util.*;

//
// Reads while with FASTQ format data, then creates new file in FASTA format using appropriate
// FASTQ records from the given file
//
public class FileConverter 
{
	private File fastq;
	private File fasta;
	
	public FileConverter(File fastq, File fasta) {
		this.fastq = fastq;
		this.fasta = fasta;
	}
	
	//
	// Writes a fasta file consisting of conversion of all records from the fastq with
	// sufficient quality and unique defline.
	//
	public void convert() throws IOException
	{
		
		FileReader fr =  new FileReader(fastq);
		BufferedReader br = new BufferedReader(fr);
		FastqReader fqr = new FastqReader(br);

		
		FileWriter fw = new FileWriter(fasta);
		PrintWriter pw = new PrintWriter(fw);
		FastaWriter faw = new FastaWriter(pw);
		
		FastqRecord fastqRec;
		
		while(true) {
			try {
				//creates new fastqRecord from the file given
				fastqRec = fqr.readRecord();
				
				//if the fastqRecord is null it means this is the end of the file
				//therefore exit the while loop
				if(fastqRec == null) {
					break;
				}
				
				//if the quality of the fastqRecord is good enough it is added
				//to new file in fasta format
				if(!fastqRec.qualityIsLow()) {
					faw.writeRecord(new FastaRecord(fastqRec));
					
				}
			}
			catch(RecordFormatException ex){
				//do nothing if format exception occurs when creating fastqRecord
			}
		}
		
		fr.close();
		br.close();
		fw.close();
		pw.close();
	}
	
		
	
	public static void main(String[] args)
	{
		System.out.println("Starting");
		try
		{
			File fastq = new File("data/HW4.fastq");
			if (!fastq.exists())
			{
				System.out.println("Can't find input file " + fastq.getAbsolutePath());
				System.exit(1);
			}
			File fasta = new File("data/HW4.fasta");
			FileConverter converter = new FileConverter(fastq, fasta);
			converter.convert();
		}
		catch (IOException x)
		{
			System.out.println(x.getMessage());
		}
		System.out.println("Done");
	}
}
