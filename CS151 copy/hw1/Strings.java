package hw1;

import java.util.ArrayList;
import java.util.Scanner;

public class Strings {
	
	public static String uniqueLetters(String str) {
		ArrayList<Character> charArray = new ArrayList<Character>();
		ArrayList<Character> repeats = new ArrayList<Character>();
		str = str.toLowerCase();
		str = str.replace(" ", "");
		for(int i = 0; i < str.length(); i++) {
			if(!charArray.contains(str.charAt(i)))
				charArray.add(str.charAt(i));
			else if (!repeats.contains(str.charAt(i)))
				repeats.add(str.charAt(i));

		}
		for(char c: repeats) {
			charArray.remove((Character) c);
		}
		String output = "";
		for(char c: charArray) {
			output = output + c;
		}
		return output;
	}
	
	public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Type a word or a sentence:");
		String str = sc.nextLine();
		sc.close();
		System.out.println("Unique characters: " + uniqueLetters(str));
		
	}
}
