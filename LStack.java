/*Lab 1: Linked List String Stack Class 
 *
 *Author: Devesh Gokalgandhi
 *
 *Methods: is_Empty, push, pop, peek, emptyLStack 
 *
 */


class Node {
	public char data; // Stack items will be of String type
	public Node next; // 
}

public class LStack {  
	private Node top; // Node type pointing to the top of the stack
	private int size;
	/*
	 * Constructor method
	 * instantiates top node
	 */
	public LStack() {
		top = new Node();
		top.next = null; // empty Stack with top being null
		size = 0;
	} 
	
	/*
	 * Verifies whether stack is empty or not 
	 * 
	 * returns boolean type True (if empty) or False (not empty)
	 */
	public boolean is_Empty() {
		if (top.next == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * 
	 * 
	 */
	public char peek() {
		return top.data;
	}
	public void push(char item) {
		Node temp = new Node();
		temp.data = item;
		temp.next = top;
		top = temp;
		size++;
		}
	
	public char pop() {
		char item;
		Node temp = top;
		if (is_Empty() == true) {
			return top.data;
		}
		else {
			item = top.data;
			top = top.next;
			temp.next = null;
			size--;
			return item;
			
		}
	}
	
	public void emptyLStack() {
		while(is_Empty() == false) {
			pop();
		}
	}
	public int getSize() {
		return size;
	}

	
}



