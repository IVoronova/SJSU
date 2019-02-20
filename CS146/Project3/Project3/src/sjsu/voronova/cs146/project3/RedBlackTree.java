package sjsu.voronova.cs146.project3;

public class RedBlackTree<Key extends Comparable<Key>> {	
		private static RedBlackTree.Node<String> root;
		private static RedBlackTree.Node<String> emptyRoot = new RedBlackTree.Node<String>("");
		//root will point to this as parent to avoid nullpointer exceptions

		public static class Node<Key extends Comparable<Key>> { //changed to static 
			
			  Key key;  		  
			  Node<String> parent;
			  Node<String> leftChild;
			  Node<String> rightChild;
			  boolean isRed;
			  int color;
			  
			  public Node(Key data){
				  this.key = data;
				  leftChild = null;
				  rightChild = null;
				  isRed = true;
				  color = 0;
			  }		
			  
			  public int compareTo(Node<Key> n){ 	//this < that  <0
			 		return key.compareTo(n.key);  	//this > that  >0
			  }
			  
			  public boolean isLeaf(){
				  if (this.equals(root) && this.leftChild == null && this.rightChild == null) return true;
				  if (this.equals(root)) return false;
				  if (this.leftChild == null && this.rightChild == null){
					  return true;
				  }
				  return false;
			  }
		}

		 public boolean isLeaf(RedBlackTree.Node<String> n){
			  if (n.equals(root) && n.leftChild == null && n.rightChild == null) return true;
			  if (n.equals(root)) return false;
			  if (n.leftChild == null && n.rightChild == null){
				  return true;
			  }
			  return false;
		  }
		
		public interface Visitor<Key extends Comparable<Key>> {
			/**
			This method is called at each node.
			@param n the visited node
			*/
			void visit(Node<Key> n);  
		}
		
		public void visit(Node<Key> n){
			System.out.println(n.key);
		}
		
		public void printTree(){  //preorder: visit, go left, go right
			RedBlackTree.Node<String> currentNode = root;	
			printTree(currentNode);
		}
		
		private void printTree(RedBlackTree.Node<String> node){
			System.out.println(node.key);
			if (node.isLeaf()){
				return;
			}
			printTree(node.leftChild);
			printTree(node.rightChild);
		}
		
		// place a new node in the RB tree with data the parameter and color it red, unless it's the root
		private void addNode(String data){  	//this < that  <0.  this > that  >0
			Node<String> n = new Node<String>(data);
			if(root != null) {
				Node<String> currentNode;

				if(n.compareTo(root) < 0 && root.leftChild != null) {
					currentNode = root.leftChild;
				}else if(root.rightChild != null){
					currentNode = root.rightChild;
				}else
					currentNode = root;
			
				while(!currentNode.isLeaf()) {
					if(n.compareTo(currentNode) < 0) {
						if(currentNode.leftChild == null)
							break;
						currentNode = currentNode.leftChild;
					}else {
						if(currentNode.rightChild == null)
							break;
						currentNode = currentNode.rightChild;
					}
				}
				n.parent = currentNode;
				if(n.compareTo(currentNode) < 0)
					currentNode.leftChild = n;
				else
					currentNode.rightChild = n;
			}
			else {
				root = n;
				root.isRed = false;
				root.color = 1;
				root.parent = emptyRoot;
			}
			fixTree(n);
			
		}	

		public void insert(String data){
			addNode(data);
		}
		
		//returns the node with the given key
		public RedBlackTree.Node<String> lookup(String k){ 
			
			Node<String> currentNode = root;
			while(currentNode != null) {
				if(k.compareTo(currentNode.key) < 0) {
					currentNode = currentNode.leftChild;
				}else if(k.compareTo(currentNode.key) > 0) {
					currentNode = currentNode.rightChild;
				}else {
					return currentNode;
				}
			}
			return null;
		}
	 	
		//the parent's node other child is returned
		public RedBlackTree.Node<String> getSibling(RedBlackTree.Node<String> n){  
			if(n == root)
				return null;
			Node<String> parentNode = n.parent;
			if(n.compareTo(parentNode) < 0)
				return parentNode.rightChild;
			else
				return parentNode.leftChild;
		}
		
		//sibling of the node's parent is returned
		public RedBlackTree.Node<String> getAunt(RedBlackTree.Node<String> n){
			Node<String> parentNode = n.parent;
			if(n == root || parentNode == root)
				return null;
			Node<String> grandNode = parentNode.parent;
			if(parentNode.compareTo(grandNode) < 0)
				return grandNode.rightChild;
			else
				return grandNode.leftChild;
			
		}
		
		public RedBlackTree.Node<String> getGrandparent(RedBlackTree.Node<String> n){
			return n.parent.parent;
		}
		
		//rotaes the given node down to the left
		public void rotateLeft(RedBlackTree.Node<String> n){
			Node<String> diffParent = n.rightChild;
			n.rightChild =  diffParent.leftChild;
			if(diffParent.leftChild != null)
				diffParent.leftChild.parent = n;
			diffParent.parent = n.parent;
			if( n == root) {
				root = diffParent;
				root.parent = emptyRoot;
			}
			else if(n == n.parent.leftChild)
				n.parent.leftChild = diffParent;
			else n.parent.rightChild = diffParent;
			diffParent.leftChild = n;
			n.parent = diffParent;
		
			
		}
		
		//rotates the give node down to the right
		public void rotateRight(RedBlackTree.Node<String> n){
			Node<String> diffParent = n.leftChild;
			n.leftChild =  diffParent.rightChild;
			if(diffParent.rightChild != null)
				diffParent.rightChild.parent = n;
			diffParent.parent = n.parent;
			if( n == root) {
				root = diffParent;
				root.parent = emptyRoot;
			}
			else if(n == n.parent.rightChild)
				n.parent.rightChild = diffParent;
			else n.parent.leftChild = diffParent;
			diffParent.rightChild = n;
			n.parent = diffParent;
		}
		
		//makes it a red and black tree my moves nodes around and changing their color
		private void fixTree(RedBlackTree.Node<String> current) {
			if(current == root) {
				current.isRed = false;
				current.color = 1;
			}
			else if (!current.parent.isRed) {
				current.parent.color = 1;
			}else {
				
				if(getAunt(current) == null || !getAunt(current).isRed ) {
					
					Node<String> originalParent = current.parent;
					if(getGrandparent(current).leftChild == originalParent) {
						//Case 1
						if(current == originalParent.rightChild) {
							rotateLeft(originalParent);
							fixTree(originalParent);
						//Case 3	
						}else {
							originalParent.isRed = false;
							originalParent.color = 1;
							getGrandparent(current).isRed = true;
							getGrandparent(current).color = 0;
							rotateRight(getGrandparent(current));
						}
					//parent is right child		
					}else {
						//case 4
						if(current == originalParent.rightChild) {
							originalParent.isRed = false;
							originalParent.color = 1;
							getGrandparent(current).isRed = true;
							getGrandparent(current).color = 0;
							rotateLeft(getGrandparent(current));
						//Case 2	
						}else {
							rotateRight(originalParent);
							fixTree(originalParent);
						}
					}
				//aunt is red	
				}else {
					current.parent.isRed = false;
					current.color = 1;
					getAunt(current).isRed = false;
					getAunt(current).color = 1;
					getGrandparent(current).isRed = true;
					getGrandparent(current).color = 0;
					fixTree(getGrandparent(current));
				}
			}
				
			
		}
		
		public boolean isEmpty(RedBlackTree.Node<String> n){
			if (n.key == null){
				return true;
			}
			return false;
		}
		 
		public boolean isLeftChild(RedBlackTree.Node<String> parent, RedBlackTree.Node<String> child)
		{
			if (child.compareTo(parent) < 0 ) {//child is less than parent
				return true;
			}
			return false;
		}

		public void preOrderVisit(Visitor<String> v) {
		   	preOrderVisit(root, v);
		}
		 
		 
		private static void preOrderVisit(RedBlackTree.Node<String> n, Visitor<String> v) {
		  	if (n == null) {
		  		return;
		  	}
		  	v.visit(n);
		  	preOrderVisit(n.leftChild, v);
		  	preOrderVisit(n.rightChild, v);
		}	
	}

