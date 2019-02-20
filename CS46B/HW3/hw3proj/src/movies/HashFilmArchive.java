package movies;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class HashFilmArchive extends HashSet<Movie> implements FilmArchive{


	private static final long serialVersionUID = 1L; //default for HashSet

	
	public ArrayList<Movie> getSorted() {
		TreeSet<Movie> tree = new TreeSet<Movie>(this);
		
		return new ArrayList<Movie>(tree);
	}

	//no add() method needed because HashSet add() does exactly what is needed
	

	public static void main(String[] args) {
		HashFilmArchive archive = new HashFilmArchive();
		
		for (Movie m: Movie.getTestMovies()) {
			archive.add(m); 
		}
		
		for (Movie m: archive) {
			System.out.println(m); 
		}
		
		System.out.println("**************"); 
		
		for (Movie m: archive.getSorted()) {
			System.out.println(m);
		}
		
		}
}
