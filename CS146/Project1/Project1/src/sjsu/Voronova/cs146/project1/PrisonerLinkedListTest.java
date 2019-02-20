package sjsu.Voronova.cs146.project1;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PrisonerLinkedListTest {
	
	PrisonerLinkedList prisoners;
	@Before
	public void setUp() throws Exception {
		prisoners = new PrisonerLinkedList();
	}

	@Test
	public void testIsEmpty() {
		assertTrue(prisoners.isEmpty());
	}

	@Test
	public void testAdd() {
		prisoners.add(9); 
		assertFalse(prisoners.isEmpty());
		assertEquals(1, prisoners.getSize());
	}
	
	@Test
	public void testEraseList() {
		prisoners.add(5);
		prisoners.add(78);
		prisoners.eraseList();
		assertEquals(-1, prisoners.getHead());
		assertEquals(-1, prisoners.getTail());
		assertEquals(0, prisoners.getSize());
	}

	@Test
	public void testEliminatePrisoners() {
		//test case 1
		prisoners.add(1);
		prisoners.add(2);
		prisoners.add(3);
		prisoners.add(4);
		prisoners.add(5);
		prisoners.add(6);
		assertEquals(1,prisoners.eliminatePrisoners(2));
		prisoners.eraseList();
		//case 2
		prisoners.add(1);
		assertEquals(1,prisoners.eliminatePrisoners(9));
		prisoners.eraseList();
		// case 3
		prisoners.add(1);
		prisoners.add(2);
		prisoners.add(3);
		prisoners.add(4);
		prisoners.add(5);
		prisoners.add(6);
		prisoners.add(7);
		assertEquals(4,prisoners.eliminatePrisoners(7));
		prisoners.eraseList();
		//case 4
		prisoners.add(1);
		prisoners.add(2);
		assertEquals(2,prisoners.eliminatePrisoners(8));
		prisoners.eraseList();
		//case 5
		prisoners.add(1);
		prisoners.add(2);
		prisoners.add(3);
		prisoners.add(4);
		prisoners.add(5);
		assertEquals(3,prisoners.eliminatePrisoners(1));
		//case 6 (my test case)
		prisoners.eraseList();
		prisoners.add(1);
		prisoners.add(2);
		prisoners.add(3);
		prisoners.add(4);
		prisoners.add(5);
		prisoners.add(6);
		assertEquals(0,prisoners.eliminatePrisoners(-476));
		//case 7 (my test case)
		prisoners.eraseList();
		prisoners.add(1);
		prisoners.add(2);
		prisoners.add(3);
		prisoners.add(4);
		prisoners.add(5);
		prisoners.add(6);
		assertEquals(0,prisoners.eliminatePrisoners(0));
		//case 8 (my test case)
		prisoners.eraseList();
		prisoners.add(1);
		prisoners.add(2);
		prisoners.add(3);
		prisoners.add(4);
		prisoners.add(5);
		prisoners.add(6);
		assertFalse(prisoners.eliminatePrisoners(Integer.MAX_VALUE) == 0);
		
	}

}
