package day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day9 {
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("./src/day9/input"));
		
		String input = lines.get(0);
		
		int total = 0;
		int garbage = 0;
		boolean inGarbage = false;
		int groupValue = 0;
		int pointer = 0;
		
		while(pointer < input.length()) {
			if(inGarbage) {
				if(input.charAt(pointer) == '>') {
					inGarbage = false;
				} else if(input.charAt(pointer) == '!') {
					pointer++;
				} else {
					garbage++;
				}
			} else if(input.charAt(pointer) == '<') {
				inGarbage = true;
			} else if(input.charAt(pointer) == '{') {
				groupValue++;
			} else if(input.charAt(pointer) == '}' && groupValue > 0) {
				total += groupValue--;
			}
			
			pointer++;
		}
		System.out.println(total);
		System.out.println(garbage);
	}
}
