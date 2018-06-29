public class Language {
	LStack stackA = new LStack(); // hold A's
	LStack stackB = new LStack(); // hold B's

	public Language() { 

	}
	public boolean L1(String input) {
		stackA.emptyLStack();
		stackB.emptyLStack();
		for(int x = 0; x < input.length(); x++) {
			if (input.charAt(x) == 'A' ) {
				stackA.push(input.charAt(x));
			}
			else if (input.charAt(x) == 'B') {
				stackB.push(input.charAt(x));
			}
			else {
				return false; // ensures only A's, B's, or null
			}
		}
		while (!stackA.is_Empty() && !stackB.is_Empty()) {
			stackA.pop();
			stackB.pop();
		}
		if (stackA.is_Empty() && stackB.is_Empty()) {
			return true;
		}
		else {
			return false;
		}
	}
		
	public boolean L2(String input) {
		stackA.emptyLStack();
		stackB.emptyLStack();
		boolean b_char = false; // true when B is encountered
		if (input.length() == 0) { // accounts for empty string
			return false;
		}
		else {
			for(int x = 0; x < input.length(); x++) {
				if (input.charAt(x) == 'A' && !b_char) {
					stackA.push(input.charAt(x));
				}
				else if (input.charAt(x) == 'B') {
					b_char = true;
					stackB.push(input.charAt(x));
				}
				else {
					return false; // ensures A's, B's (in order) or null 
				}
			}
			while (!stackA.is_Empty() && !stackB.is_Empty()) {
				stackA.pop();
				stackB.pop();
				}
			
			if (stackA.is_Empty() && stackB.is_Empty()) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	public boolean L3(String input) {
		stackA.emptyLStack();
		stackB.emptyLStack();
		
		boolean b_char = false; // true when B is encountered
		boolean less_b = true; // false when less than 2 B's for each A
		if (input.length() == 0) { // accounts for empty string
			return false;
		}
		else {
			for(int x = 0; x < input.length(); x++) {
				if (input.charAt(x) == 'A' && !b_char) {
					stackA.push(input.charAt(x));
				}
				else if (input.charAt(x) == 'B') {
					b_char = true;
					stackB.push(input.charAt(x));
				}
				else {
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
			if (less_b){ 
				if (stackA.is_Empty() && stackB.is_Empty()) {
					return true;
				}
				else { // more than 2 B's for each A
					return false;
				}
			}
			else { // less than 2 B's for each A 
				return false;
			}
		}
	}
	
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
		}
		else {
			for(int x = 0; x < input.length(); x++) {
				if (input.charAt(x) == 'A' && !b_char && !pattern) { // push original A's
					stackA.push(input.charAt(x));
				}
				else if (input.charAt(x) == 'B' && !pattern) { // push original B's
					b_char = true;
					stackB.push(input.charAt(x));
				}
				else if (input.charAt(x) == 'A' && b_char && !pattern){ // push pattern A after original B
					stackA2.push(input.charAt(x));
					
					b_char = false;
					pattern = true;
				}
				else if (input.charAt(x) == 'A' && !b_char && pattern){ // push pattern A's after pattern A
					stackA2.push(input.charAt(x));
					
				}
				else if (input.charAt(x) == 'B' && pattern) { // push pattern B's after pattern A's
					stackB2.push(input.charAt(x));
					b_char = true;
					if (stackA.getSize() == stackA2.getSize() && stackB.getSize() == stackB2.getSize()){
						compare = true; // for P = 2
						stackA2.emptyLStack(); // reset pattern stack for P > 2
						stackB2.emptyLStack(); // reset pattern stack for P > 2
					}
					else {
						compare = false;  // pattern A's, B's dont match original A's, B's
					}
				}
				else if (input.charAt(x) == 'A' && b_char && pattern){  // push pattern A if P > 2
					stackA2.push(input.charAt(x));
					b_char = false;
				}
				else {
					return false; // character other than A, B, or null was encountered
				}
			}
		}
		
		if (stackB.is_Empty()){ // if no original B's 
			return false;
		}
		
		else if (stackA2.getSize() != 0 && stackB2.getSize() == 0) { // if no pattern stack B's
			return false;
		}
		else if (compare){ // original stack matches pattern
			return true;
		}
		else if (stackA2.getSize() == 0 && stackB2.getSize() == 0){ // for P = 1
			return true;
		}
		else {
			return false;
		}
	}
	public String langCheck(String input) {
		String output = input;
		if (L1(input) == true) {
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
		return output;
	}
} // end of Language class
