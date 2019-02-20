package sjsu.Voronova.cs146.project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/*
 * MusicShuffle takes text file of list of songs, shuffles the songs and creates new text file with the given songs shuffled.
 */
public class MusicShuffle {
	
	private ArrayList<String> playlist; //Array of all the songs from the text file
	private Random myRanGen; //random generator for generating random numbers for shuffling
	
	/*
	 * Takes text file and converts it to an ArrayList<String> of the songs
	 * Throws exception if file is not found
	 *//Users/iv.kitty/Desktop/CS146/Project1/Project1/src/sjsu/Voronova/cs146/project1/MusicShuffle.java
	public MusicShuffle(String filePath) throws Exception{
		
		readPlaylistFile(filePath);
		myRanGen = new Random(0); //seed is 0
		
	}
	
	public MusicShuffle() {
		myRanGen = new Random(0); //seed is 0
		
	}
	
	/*
	 * Converts a text file to arraylist of songs and stores in playlist array
	 */
	public void readPlaylistFile(String filePath) throws Exception{
		
		playlist = new ArrayList<String>();
		BufferedReader bf = new BufferedReader(new FileReader(filePath));
		String song = bf.readLine();
		
		while(song != null) {
			playlist.add(song);
			song = bf.readLine();
		}
		bf.close();
	}
	
	public ArrayList<String> getPlaylistArray(){
		return playlist;
	}
	
	
	public void setMyRandom(int seed) {
		myRanGen.setSeed(seed);
	}
	
	
	//Creates new test file with the songs of the array playlist
	public void createNewPlaylistFile() throws Exception{
		PrintWriter pw = new PrintWriter("outputFiles/VoronovaIrinaPlaylist.txt");
		for(String s: playlist) {
			pw.println(s);
		}
		pw.close();
	}
	
	/*Shuffles the songs in the array playlist using Fisher–Yates shuffle. Swaps random element in the array
	with the end of the array. Repeats the process with the array excluding the last element until there is only one element left
	*/
	public void shuffleSongs() throws Exception{
		int subArrayEnd = playlist.size() - 1;
		String key = playlist.get(subArrayEnd); //for keeping track of a song
		
		while(subArrayEnd > 0) {
			int i = myRanGen.nextInt(subArrayEnd); //returns random integer in the range of the subarray
			playlist.set(subArrayEnd, playlist.get(i));
			playlist.set(i, key);
			subArrayEnd--;
			key = playlist.get(subArrayEnd);
		}
		
		createNewPlaylistFile();
	}
	
	
	
}
