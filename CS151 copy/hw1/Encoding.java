package hw1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Encoding {
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
		Scanner sc = new Scanner(System.in);
		System.out.println("Number of . ");
		String m = sc.nextLine();
		System.out.print("Number of - ");
		String n = sc.nextLine();
		sc.close();
		System.out.println("All the results: " + morseCodes(Integer.parseInt(m), Integer.parseInt(n)));
	}
}
