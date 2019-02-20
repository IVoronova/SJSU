package movies;

import java.util.ArrayList;

public interface FilmArchive {
	
	public ArrayList<Movie> getSorted(); // sorts movies using compareTo, first my name then by year
	public boolean add (Movie m); /*  looks if the arg movie is in the archive, if yes returns false, 
									if no adds the movie to array and sorts it returning true */

}
