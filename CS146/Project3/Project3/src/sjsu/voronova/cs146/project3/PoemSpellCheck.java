package sjsu.voronova.cs146.project3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PoemSpellCheck {
	public static RedBlackTree<String> dictionary = new RedBlackTree<String>();

	//creates red and black tree of the dictionary from the file
	public static void createDictionary() throws IOException {
	
		BufferedReader bf = new BufferedReader(new FileReader("Files/Dictionary.txt"));
	
		String word = bf.readLine();
		while(word != null) {
			dictionary.insert(word);
			word = bf.readLine();
		}
		bf.close();
	}
	
	//reads textfile and check for words in the dictionary, if a word is not in the dictionary it is returned in the arraylist
	public static ArrayList<String> checkMispellings(String Filepath) throws IOException {
		ArrayList<String> mispelling = new ArrayList<String>();
		ArrayList<String> poem = new ArrayList<String>();
		BufferedReader bf = new BufferedReader(new FileReader(Filepath));
		String line = bf.readLine();
		
		while(line != null) {
			line.toLowerCase();
			String[] split = line.split(" ");
			for(String s: split) {
				poem.add(s);
			}
			line = bf.readLine();
		}
		for(String s: poem) {
			if(dictionary.lookup(s) == null)
				mispelling.add(s);
		}
		
		return mispelling;
	}

	public static void main(String[] arg) {
		long start = 0;
		long dictionaryTime = 0;
		long checkingTime;
		try {
			start = System.currentTimeMillis();
			createDictionary();
			dictionaryTime = System.currentTimeMillis() - start;
			start = System.currentTimeMillis();
			ArrayList<String> checked = checkMispellings("Files/TheRoadNotTaken.txt");
			checkingTime = System.currentTimeMillis() - start;
			if(checked.isEmpty()) {
				System.out.println("No mistakes");
			}else {
				System.out.println("Mistakes:");
				for(String s: checked)
					System.out.println(s);
			}
			
			System.out.println("Time for creating the dictionary in msec:" + dictionaryTime);
			System.out.println("Time for checking in msec:" + checkingTime);
		}catch(FileNotFoundException x){
			System.out.println("File not found");
		}catch(IOException x) {
			System.out.println(x.getMessage());
		}
	}
}
