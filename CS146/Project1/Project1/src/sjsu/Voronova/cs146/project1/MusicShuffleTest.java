package sjsu.Voronova.cs146.project1;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class MusicShuffleTest {
	
	MusicShuffle shuffler;
	ArrayList<String> songPlaylist;
	
	@Before
	public void setUp() throws Exception {
		shuffler = new MusicShuffle("testFiles/Playlist.txt");
		songPlaylist = shuffler.getPlaylistArray();
		
	}

	/*
	 * Tests the method to see if MusicShuffel converts file to an array properly
	 */
	@Test
	public void testReadPlaylistFile() {
		try {
			shuffler.readPlaylistFile("testFiles/ShuffleTest1.txt");
			shuffler.createNewPlaylistFile();
			songPlaylist = shuffler.getPlaylistArray();
			BufferedReader expected = new BufferedReader(new FileReader("testFiles/ShuffleTest1.txt"));
			
			for(String song: songPlaylist) {
				assertEquals(expected.readLine(), song);
			}
			expected.close();
		}
		catch(Exception x) {
			fail("File not found");
		}
		
	}
	
	/*
	 * Test to see that the method creates text file properly with the being same as the read file. 
	 */
	@Test
	public void testCeateNewPlaylistFile() {
		
		try {
			shuffler.createNewPlaylistFile();
			BufferedReader Out = new BufferedReader(new FileReader("outputFiles/VoronovaIrinaPlaylist.txt"));
			BufferedReader In = new BufferedReader(new FileReader("testFiles/Playlist.txt"));
			String expectedLine;
			String actualLine;
			
			while((expectedLine = In.readLine()) != null) {
				actualLine = Out.readLine();
				assertEquals(expectedLine, actualLine);
				
			}
			Out.close();
			In.close();
		}
		catch(Exception x) {
			fail("File not found");
		}
	}
	/*
	 * Tests the shuffling of the songs
	 */
	@Test
	public void testShuffleSongs() {
		shuffler.setMyRandom(20);
		try {
			shuffler.shuffleSongs();
			BufferedReader Out = new BufferedReader(new FileReader("outputFiles/VoronovaIrinaPlaylist.txt"));
			BufferedReader In = new BufferedReader(new FileReader("testFiles/Target2.txt"));
			String expectedLine;
			String actualLine;
			
			while((expectedLine = In.readLine()) != null) {
				actualLine = Out.readLine();
				assertEquals(expectedLine, actualLine);
				
			}
			Out.close();
			In.close();
		}
		catch(Exception x) {
			fail("File not found");
		}
		
	}

}
