package movies;


public class Movie implements Comparable<Movie>{

	private String title;
	private int    year;
	
	public Movie(String t, int y){
		
		title = t;
		year = y;
	}
	
	/*Compares 2 movies first by title using String's compareTo() method and returns the difference
	 * if movie have same title compares by year, returns the difference
	 */
	
	public int compareTo(Movie m) {
		
		int compTitle = this.title.compareTo(m.title);
		
		if(compTitle != 0) {
			return compTitle;
		}
		
		return this.year - m.year;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getYear() {
		return year;
	}
	
	public boolean equals(Object x) {
		
		Movie m = (Movie)x;
		
		return this.compareTo(m) == 0; //compares movies and if compareTo() returns zero it means they are equal
	}
	
	public int hashCode() {
		
		return title.hashCode() + year;  
		
	}
	
	public String toString() { 
		
		return "Movie " + title + " " + year;
	}
	
	/* returns array of 10 movies
	 * 2 of the movies are same object
	 * 2 movies have same year but different title
	 * 2 pairs of movies that have same title but different years
	 * The other 2 movies have nothing in common with any other movie
	 */
	public static Movie[] getTestMovies() { 
		
		Movie testMovie = new Movie("Hot Jalapenos Topping", 2016);
		Movie testMovie2 = testMovie;
		
		Movie[] m = 	{new Movie("Shrek The Savior", 1999),
					 new Movie("Shrek The Savior", 2000),
					 new Movie("Does Jumin Han is Gay?", 2006),
					 new Movie("Yes he does", 2006),
					 testMovie,
					 testMovie2,
					 new Movie("Mistake Messenger", 2016),
					 new Movie("Love Nikki 3D", 2017),
					 new Movie("Adventures of Trina the Cat", 2022),
					 new Movie("Zen x Jumin Han is Cannon", 9999)};
		
		
		return m;
	}

}
