/* Lab 1
 *
 * Module: Language.java
 *
 * Author: D.G.
 *
 * The purpose of this module is to evaluate input strings to determine whether
 * they belong to language L, in which
 * 	L1 = { w: w contains equal numbers of A's and B's (in any order) and no other characters} 
	L2 = { w: w is of the form A^n B^n, for some n > 0 } 
	L3 = { w: w is of the form A^n B^2n, for some n > 0 } 
	L4 = { w: w is of the form (A^n B^m)^p, for some m,n,p > 0 } 
	L5 = { w: w contains an odd number of A’s (n > 0) and even number of B’s (m > 0) in any order 
	and no other characters}  
	L6 = { w: w is of the form ((A^n B^m)C)^p, for some m,n,p > 0 } 
 * 
 * Methods: Language(), L1(), L2(), L3(), L4(), L5(), L6(), checkLang()
 *
 */

public class Language {
	LStack stackA = new LStack(); // hold A's
	LStack stackB = new LStack(); // hold B's
	
	/*
	 * Constructor method 
	 */
	public Language() {

	}
	
	/*
	 * Evaluate whether string is part of language L1: 
	 * equal amount of A's, B's, no order, no other characters
	 * 
	 * Input: string object
	 * 
	 * Returns boolean value true if string is part of L1, or false otherwise
	 */
	public boolean L1(String input) {
		stackA.emptyLStack();
		stackB.emptyLStack();
		for (int x = 0; x < input.length(); x++) {
			if (input.charAt(x) == 'A') { // add original A's to A stack
				stackA.push(input.charAt(x));
			} else if (input.charAt(x) == 'B') { // add original B's to B stack
				stackB.push(input.charAt(x));
			} else {
				return false; // ensures only A's, B's, or null
			}
		}
		while (!stackA.is_Empty() && !stackB.is_Empty()) { // if A stack and B stack not empty
			stackA.pop(); // remove element from top of A stack
			stackB.pop(); // remove element for top of B stack
		}
		if (stackA.is_Empty() && stackB.is_Empty()) { // if equal amount of A's and B's
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Evaluate whether string is part of language L2: 
	 * A^n B^n, for some n > 0
	 * 
	 * Input: string object
	 * 
	 * Returns boolean value true if string is part of L2, or false otherwise
	 */
	public boolean L2(String input) {
		stackA.emptyLStack();
		stackB.emptyLStack();
		boolean b_char = false; // true when B is encountered
		if (input.length() == 0) { // accounts for empty string
			return false;
		} else {
			for (int x = 0; x < input.length(); x++) {
				if (input.charAt(x) == 'A' && !b_char) { // add original A's
					stackA.push(input.charAt(x));
				} else if (input.charAt(x) == 'B') { // add original B's after A's
					b_char = true;
					stackB.push(input.charAt(x));
				} else {
					return false; // ensures A's, B's (in order) or null
				}
			}
			while (!stackA.is_Empty() && !stackB.is_Empty()) { // if both stacks are not empty
				stackA.pop(); // remove element from A stack
				stackB.pop(); // remove element from B stack
			}

			if (stackA.is_Empty() && stackB.is_Empty()) { // if equal amount of A's and B's
				return true;
			} else {
				return false;
			}
		}
	}
	
	/*
	 * Evaluate whether string is part of language L3: 
	 * A^n B^2n, for some n > 0
	 * 
	 * Input: string object
	 * 
	 * Returns boolean value true if string is part of L3, or false otherwise
	 */
	public boolean L3(String input) {
		stackA.emptyLStack();
		stackB.emptyLStack();

		boolean b_char = false; // true when B is encountered
		boolean less_b = true; // false when less than 2 B's for each A
		if (input.length() == 0) { // accounts for empty string
			return false;
		} else {
			for (int x = 0; x < input.length(); x++) {
				if (input.charAt(x) == 'A' && !b_char) { // add original A's
					stackA.push(input.charAt(x));
				} else if (input.charAt(x) == 'B') { // add original B's
					b_char = true;
					stackB.push(input.charAt(x));
				} else {
					return false; // ensures A's, B's or null
				}
			}
			while (!stackA.is_Empty() && !stackB.is_Empty()) {
				stackA.pop();
				stackB.pop();
				if (stackB.is_Empty()) { // less than two B's for each A (ex. 2 A's, 3 B's)
					less_b = false;
				}
				stackB.pop(); // for every A removed, remove two B's
			}
			if (less_b) {
				if (stackA.is_Empty() && stackB.is_Empty()) { // if double amount of B's to A's
					return true;
				} else { // more than 2 B's for each A
					return false;
				}
			} 
			else { // less than 2 B's for each A
				return false;
			}
		}
	}
	
	/*
	 * Evaluate whether string is part of language L4: 
	 * (A^n B^m)^p, for some m,n,p > 0
	 * 
	 * Input: string object
	 * 
	 * Returns boolean value true if string is part of L4, or false otherwise
	 */
	public boolean L4(String input) {
		stackA.emptyLStack();
		stackB.emptyLStack();
		LStack stackA2 = new LStack(); // pattern stack containing A's after original stack
		LStack stackB2 = new LStack(); // pattern stack containing B's
		boolean compare = false; // true if pattern stack matches original stack
		boolean b_char = false; // true when B is encountered
		boolean pattern = false; // true after pushing first A^n B^m

		if (input.length() == 0) { // accounts for empty string
			return false;
		} else {
			for (int x = 0; x < input.length(); x++) {
				if (input.charAt(x) == 'A' && !b_char && !pattern) { // push original A's
					stackA.push(input.charAt(x));
				} else if (input.charAt(x) == 'B' && !pattern) { // push original B's
					b_char = true;
					stackB.push(input.charAt(x));
				} else if (input.charAt(x) == 'A' && b_char && !pattern) { // push pattern A after original B
					stackA2.push(input.charAt(x));
					b_char = false;
					pattern = true;
				} else if (input.charAt(x) == 'A' && !b_char && pattern) { // push pattern A's after pattern A
					stackA2.push(input.charAt(x));

				} else if (input.charAt(x) == 'B' && pattern) { // push pattern B's after pattern A's
					stackB2.push(input.charAt(x));
					b_char = true;
					if (stackA.getSize() == stackA2.getSize() && stackB.getSize() == stackB2.getSize()) {
						compare = true; // for P = 2
						stackA2.emptyLStack(); // reset pattern stack for P > 2
						stackB2.emptyLStack(); // reset pattern stack for P > 2
					} else {
						compare = false; // pattern A's, B's dont match original A's, B's
					}
				} else if (input.charAt(x) == 'A' && b_char && pattern) { // push pattern A if P > 2
					stackA2.push(input.charAt(x));
					b_char = false;
				} else {
					return false; // character other than A, B, or null was encountered
				}
			}
		}
		if (stackA.is_Empty() || stackB.is_Empty()) { // if only A's or B's
			return false;
		} else if (stackA2.getSize() != 0 && stackB2.getSize() == 0) { // if no pattern stack B's
			return false;
		} else if (compare) { // original stack matches pattern
			return true;
		} else if (stackA2.getSize() == 0 && stackB2.getSize() == 0) { // for P = 1
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Evaluate whether string is part of language L5: 
	 * odd number of A’s (n > 0) and even number of B’s (m > 0) in any order and no other characters
	 * 
	 * Input: string object
	 * 
	 * Returns boolean value true if string is part of L5, or false otherwise
	 */
	public boolean L5(String input) {
		stackA.emptyLStack();
		stackB.emptyLStack();
		if (input.length() == 0) { // accounts for empty string
			return false;
		}
		else {
			for (int x = 0; x < input.length(); x++) {
				if (input.charAt(x) == 'A') {
					stackA.push(input.charAt(x));
				} else if (input.charAt(x) == 'B') {
					stackB.push(input.charAt(x));
				} else {
					return false; // ensures A's, B's, or null
				}
			}
			if (stackA.is_Empty() || stackB.is_Empty()) { // A's and no B's or B's and no A's
				return false;
			}
			else if (stackA.getSize() % 2 == 1 && stackB.getSize() % 2 == 0) { // odd if remainder 1, even if remainder 0;
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	/*
	 * Evaluate whether string is part of language L6: 
	 * ((A^n B^m)C)^p, for some n,m,p > 0
	 * 
	 * Input: string object
	 * 
	 * Returns boolean value true if string is part of L6, or false otherwise
	 */
	public boolean L6(String input) {
		stackA.emptyLStack();
		stackB.emptyLStack();
		LStack stackA2 = new LStack(); // pattern stack containing A's after original stack
		LStack stackB2 = new LStack(); // pattern stack containing B's
		
		boolean b_char = false; // true when B is encountered
		boolean c_char = false; // true when C is encountered
		boolean compare = false; // true if pattern stack matches original stack
		boolean pattern = false; // true after original A's and B's are processed
		
		LStack stackC = new LStack(); // stack to contain C's
		if (input.length() == 0) { // accounts for empty string
			return false;
		}
		if (input.charAt(input.length() - 1) != 'C') {
			return false;
		}
		else {
			for (int x = 0; x < input.length(); x++) {
				if (input.charAt(x) == 'A' && !b_char && !c_char && !pattern) { 
					stackA.push(input.charAt(x));
				} 
				else if (input.charAt(x) == 'B' && !pattern) {
					b_char = true;
					stackB.push(input.charAt(x));
				} 
				else if(input.charAt(x) == 'C' && b_char) { // exactly one C after B
					b_char = false;
					c_char = true;
					stackC.push(input.charAt(x));
				} 
				else if(input.charAt(x) == 'C' && !b_char) { // if C after C or A or anything other character
					return false;
				} 
				else if (input.charAt(x) == 'A' && c_char && !pattern) { // push pattern A after C
					stackA2.push(input.charAt(x));
					c_char = false;
					pattern = true;
				} 
				else if (input.charAt(x) == 'A' && !b_char && !c_char && pattern) { // push pattern A's after pattern A
					stackA2.push(input.charAt(x));
				} 
				else if (input.charAt(x) == 'B' && pattern) { // push pattern B's after pattern A's
					stackB2.push(input.charAt(x));
					b_char = true;
					if (stackA.getSize() == stackA2.getSize() && stackB.getSize() == stackB2.getSize()) {
						compare = true; // for P = 2
						stackA2.emptyLStack(); // reset pattern stack for P > 2
						stackB2.emptyLStack(); // reset pattern stack for P > 2
					} else {
						compare = false; // pattern A's, B's dont match original A's, B's
					}
				} 
				else if (input.charAt(x) == 'A' && c_char && pattern) { // push pattern A after C if P > 2
					stackA2.push(input.charAt(x));
					b_char = false;
					c_char = true;
				} else {
					return false; // ensures A's, B's, C's or null
				}
			}
		}
		if (stackB.is_Empty()) { // if no original B's
			return false;
		} else if (stackC.is_Empty()) { // if no C's in string
			return false;
		} else if (stackA2.getSize() != 0 && stackB2.getSize() == 0) { // if no pattern stack B's
			return false;
		} else if (compare) { // original stack matches pattern
			return true;
		} else if (stackA2.getSize() == 0 && stackB2.getSize() == 0) { // for P = 1, no pattern
			return true;
		} else { // original stack does not match pattern stack
			return false;
		}
	}

	/*
	 * Appends language name (L1, L2, ..., L6) to string input if language methods returned true
	 *
	 * Input: string object
	 * 
	 * Returns string object, output
	 */
	public String langCheck(String input) {
		String output = input;
		if (L1(input)) {
			output += " L1 ";
		}
		if (L2(input)) {
			output += " L2 ";
		}
		if (L3(input)) {
			output += " L3 ";
		}
		if (L4(input)) {
			output += " L4 ";
		}
		if (L5(input)) {
			output += " L5 ";
		}
		if (L6(input)) {
			output += " L6 ";
		}
		return output;
	}
} // end of Language class
