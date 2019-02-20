package sjsu.voronova.cs146.project3;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class RBTTest {

	@Test
	public void test() {
		RedBlackTree<String> rbt = new RedBlackTree<String>();
        rbt.insert("D");
        rbt.insert("B");
        rbt.insert("A");
        rbt.insert("C");
        rbt.insert("F");
        rbt.insert("E");
        rbt.insert("H");
        rbt.insert("G");
        rbt.insert("I");
        rbt.insert("J");
        assertEquals("DBACFEHGIJ", makeString(rbt));
        String str=     "Color: 1, Key:D Parent: \n"+
                        "Color: 1, Key:B Parent: D\n"+
                        "Color: 1, Key:A Parent: B\n"+
                        "Color: 1, Key:C Parent: B\n"+
                        "Color: 1, Key:F Parent: D\n"+
                        "Color: 1, Key:E Parent: F\n"+
                        "Color: 0, Key:H Parent: F\n"+
                        "Color: 1, Key:G Parent: H\n"+
                        "Color: 1, Key:I Parent: H\n"+
                        "Color: 0, Key:J Parent: I\n";
		assertEquals(str, makeStringDetails(rbt));
            
    }
    
    //tests checking
	public static void checkSpellingTest() throws IOException {
		RedBlackTree<String> dictionary = new RedBlackTree<String>();
		BufferedReader bf = new BufferedReader(new FileReader("Files/Dictionary.txt"));
		
		String word = bf.readLine();
		while(word != null) {
			dictionary.insert(word);
			word = bf.readLine();
		}
		bf.close();
		assertEquals("abelmeholah", dictionary.lookup("abelmeholah").key);
		assertEquals("zymosthenic", dictionary.lookup("zymosthenic").key);
		assertEquals(null, dictionary.lookup("Irina"));
		assertEquals("pequotlakes", dictionary.lookup("pequotlakes").key);
		assertEquals(null, dictionary.lookup("whytho"));
		
	}
    
    public static String makeString(RedBlackTree<String> t)
    {
       class MyVisitor implements RedBlackTree.Visitor<String> {
          String result = "";
          public void visit(RedBlackTree.Node<String> n)
          {
             result = result + n.key;
          }
       };
       MyVisitor v = new MyVisitor();
       t.preOrderVisit(v);
       return v.result;
    }

    public static String makeStringDetails(RedBlackTree<String> t) {
    	{
    	       class MyVisitor implements RedBlackTree.Visitor<String> {
    	          String result = "";
    	          public void visit(RedBlackTree.Node<String> n)
    	          {
    	        	  
    	        	  if(!(n.key).equals("")) {
    	        		  if(n.isRed)
    	        			  n.color = 0;
    	        		  else
    	        			  n.color = 1;
    	        			  
    	        		  result = result +"Color: "+ n.color+", Key:"+n.key+" Parent: "+n.parent.key+"\n";
    	        	  }
    	             
    	          }
    	       };
    	       MyVisitor v = new MyVisitor();
    	       t.preOrderVisit(v);
    	       return v.result;
    	 }
    }
  // add this in your class  
  //  public static interface Visitor
  //  {
  //  	/**
  //     This method is called at each node.
  //     @param n the visited node
  //  	 */
  //  	void visit(Node n);
  //  }
 
  
  // public void preOrderVisit(Visitor v)
  //  {
  //  	preOrderVisit(root, v);
  //  }
 
 
  // private static void preOrderVisit(Node n, Visitor v)
  //  {
  //  	if (n == null) return;
  //  	v.visit(n);
  //  	preOrderVisit(n.left, v);
  //  	preOrderVisit(n.right, v);
  //  }
    

}
