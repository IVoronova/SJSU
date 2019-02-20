package sjsu.Voronova.cs146.project1;

/*
 * Circular LinkedList of prisoners, data being prisoner's number and should be added 
 * in increasing order from 1
 */
public class PrisonerLinkedList {
	
	private ListNode tail;
	private ListNode head;
	private int size;
	
	public PrisonerLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	/*
	 * Node for the PrisonerLinkedList class, only contain a number
	 */
	private class ListNode{
		
		private ListNode next;
		private int data;
		
		public ListNode(int data) {
			this.data = data;
		}
		
		public void setNext(ListNode node) {
			next = node;
		}
		public ListNode getNext() {
			return next;
		}
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	/*
	 * Adds new prisoner at the end of the list
	 */
	public void add(int data) {
		
		ListNode dataNode = new ListNode(data);
		if(size == 0) {
			setHead(dataNode);
		}else {
			tail.setNext(dataNode);
			tail = dataNode;
			tail.setNext(head);
			size++;
		}
	}
	/*
	 * Makes the list contain only one node which is the head and tail of the list
	 */
	private void setHead(ListNode dataNode) {
		head = dataNode;
		tail = dataNode;
		head.setNext(tail);
		tail.setNext(head);
		size = 1;
	}
	
	/*
	 * Counts of prisoners using key, eliminating the prisoner(deletes node) if the count ends on them
	 * Then starting from the next prisoners counts again until all but one are eliminated
	 * Returns the position of winning prisoner
	 * Returns 0 if the key is invalid (is below 1)
	 */
	public int eliminatePrisoners(int key) {
		
		if(key < 1)
			return 0; //return 0 if the key is not valid
		
		ListNode currentNode = head;// Count starts at head
		
		while(size != 1) {
			
			for(int i = 1; i < key; i++) {//the loop makes currentNode end at the node right before the node that is going to be deleted
				currentNode = currentNode.getNext(); //moves forward by 1
			}
			currentNode.setNext(currentNode.getNext().getNext()); //skips node right after currentNode, deleting the node in the process
			currentNode = currentNode.getNext(); //moves forward by 1
			size--;
		}
		setHead(currentNode);
		return currentNode.data;
	}
	
	public int getHead() {
		if(head == null)
			return -1;
		return head.data;
	}
	
	public int getTail() {
		if(tail == null)
			return -1;
		return tail.data;
	}
	
	public int getSize() {
		return size;
	}
	
	//Erases everything from the list bringing it to state before prisoners were added
	public void eraseList() {
		head = null;
		tail = null;
		size = 0;
	}
	
}
