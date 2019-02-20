package movies;

import java.util.ArrayList;
import java.util.TreeSet;

public class TreeFilmArchive extends TreeSet<Movie> implements FilmArchive{

	
	private static final long serialVersionUID = 1L; // default for TreeSet

	
	public ArrayList<Movie> getSorted() {
		
		return new ArrayList<Movie>(this);
	}

	
	public static void main(String[] args) {
		TreeFilmArchive archive = new TreeFilmArchive();
		
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
