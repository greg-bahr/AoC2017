package day25;

import java.io.IOException;
import java.util.HashMap;

public class Day25 {
	public static void main(String[] args) throws IOException {
		int steps = 12425180;
		int step = 0;
		char state = 'A';
		int pos = 0;
		
		HashMap<Character, int[]> states = new HashMap<>();
		states.put('A', new int[] { 1, 1, 'B', 0, 1, 'F' });
		states.put('B', new int[] { 0, -1, 'B', 1, -1, 'C' });
		states.put('C', new int[] { 1, -1, 'D', 0, 1, 'C' });
		states.put('D', new int[] { 1, -1, 'E', 1, 1, 'A' });
		states.put('E', new int[] { 1, -1, 'F', 0, -1, 'D' });
		states.put('F', new int[] { 1, 1, 'A', 0, -1, 'E' });
		
		HashMap<Integer, Integer> tape = new HashMap<>();
		tape.put(0, 0);
		
		while(step < steps) {
			int[] currState = states.get(state);
			int val = tape.containsKey(pos) ? tape.get(pos) : 0;
			
			tape.put(pos, currState[0+(3*val)]);
			pos += currState[1+(3*val)];
			state = (char) currState[2+(3*val)];
			
			step++;
		}
		
		int diag = 0;
		
		for(int num : tape.keySet()) {
			diag += tape.get(num);
		}
		
		System.out.println(diag);
	}
}