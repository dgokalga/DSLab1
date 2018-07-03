/* Lab 1
 *
 * Module: LStack.java
 *
 * Author: D.G.
 *
 * The purpose of this module is to create a linked-stack implementation.
 *  
 * Methods: is_Empty(), peek(), push(), pop(), emptyLStack(), getSize()
 *
 */


class Node {
	public char data; // stack items will be of char datatype
	public Node next; // points to data in transition
}

public class LStack {
	private Node top; // Node type pointing to the top of the stack
	private int size;

	/*
	 * Constructor method instantiates top node
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
		} else {
			return false;
		}
	}

	/*
	 * Observes the top element of the stack 
	 * 
	 * Returns the top element of the stack
	 */
	public char peek() {
		return top.data;
	}
	
	/*
	 * Adds element to the top of the stack 
	 * Input: char datatype element
	 */
	public void push(char item) {
		Node temp = new Node();
		temp.data = item;
		temp.next = top;
		top = temp;
		size++;
	}

	/*
	 * Removes top element of the stack
	 * 
	 * Returns removed element 
	 */
	public char pop() {
		char item;
		Node temp = top;
		if (is_Empty() == true) {
			return top.data;
		} else {
			item = top.data;
			top = top.next;
			temp.next = null;
			size--;
			return item;

		}
	}
	
	/*
	 * Removes every element in stack
	 */
	public void emptyLStack() {
		while (is_Empty() == false) {
			pop();
		}
	}

	/*
	 * Determines size of the stack
	 * 
	 * Returns int size
	 */
	public int getSize() {
		return size;
	}

}
