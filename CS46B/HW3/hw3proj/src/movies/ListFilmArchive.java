package movies;

import java.util.ArrayList;
import java.util.TreeSet;

public class ListFilmArchive extends ArrayList<Movie> implements FilmArchive{

	
	private static final long serialVersionUID = 1L; // default for extending ArrayList


	public ArrayList<Movie> getSorted() {
		
		TreeSet<Movie> tree = new TreeSet<Movie>(this);
		
		return new ArrayList<Movie>(tree);
	}

	
	public boolean add(Movie m) {
		 
		for (Movie movie: this) {
			if (movie.equals(m)) {
				return false;
			}
		}
		
		boolean added = super.add(m);
		
		return added;
		
	}
	
	public static void main(String[] args) {
		ListFilmArchive archive = new ListFilmArchive();
		
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
