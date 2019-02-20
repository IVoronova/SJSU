package hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class TestProject {

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
	public static Set<String> morseCodes(int m, int n)
	   {
	     ArrayList<String> strArray = new ArrayList<>();
	    	 morseCodeGen(m, n, "", strArray);
	     Set<String> result = new TreeSet<>(strArray);
			return result;
	   }
	   private static void morseCodeGen(int m, int n, String str, ArrayList<String> result) {
		
			if ( m == 0 && n == 0) {
				result.add(str);
				return;
			}
			if(m > 0)
				 morseCodeGen(m - 1, n, str + ".", result);
			if(n > 0)
				morseCodeGen(m, n - 1, str + "-", result);
				
			return;
			}
	
	
	public static void main(String[] arg) {
		/*Scanner sc = new Scanner(System.in);
		System.out.println("Test 1 \nInput String: ");
		String str = sc.nextLine();
		System.out.println("Unique letters: " + uniqueLetters(str));
		String m = sc.nextLine();
		String n = sc.nextLine();
		sc.close();
		*/
		System.out.println(morseCodes(2,1));
		
		
	}
}
