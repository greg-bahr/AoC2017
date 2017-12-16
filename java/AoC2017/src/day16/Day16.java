package day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day16 {
	public static void main(String[] args) throws IOException {
	
		List<String> input = Files.readAllLines(Paths.get("./src/day16/input"));
		List<String> instructions = Arrays.asList(input.get(0).trim().split(","));
		
		List<String> programs = Arrays.asList("a", "b", "c", "d","e","f","g","h","i","j","k","l","m","n","o","p");
		int pos1 = 0, pos2 = 0;
		String temp;
		String original = programs.stream().reduce((p, n) -> p+n).get();
		int ran = 0;
		
		while(true) {
			for(String instr : instructions) {
				String[] params = instr.substring(1, instr.length()).split("/");
				
				switch(instr.charAt(0)) {
					case 's':
						Collections.rotate(programs, Integer.parseInt(instr.substring(1)));
						break;
					case 'x':
						pos1 = Integer.parseInt(params[0]);
						pos2 = Integer.parseInt(params[1]);
						
						temp = programs.get(pos1);
						programs.set(pos1, programs.get(pos2));
						programs.set(pos2, temp);
						break;
					case 'p':
						pos1 = programs.indexOf(params[0]);
						pos2 = programs.indexOf(params[1]);
						
						temp = programs.get(pos1);
						
						programs.set(pos1, programs.get(pos2));
						programs.set(pos2, temp);
						break;
				}
			}
			
			if(ran == 0) System.out.println(programs.stream().reduce((p, n) -> p+n).get());
			ran++;
			
			if(programs.stream().reduce((p, n) -> p+n).get().equals(original)) break;
		}
		
		//System.out.println(ran);
		
		programs = Arrays.asList("a", "b", "c", "d","e","f","g","h","i","j","k","l","m","n","o","p");
		
		for(int i = 0; i<1000000000%ran; i++) {
			for(String instr : instructions) {
				String[] params = instr.substring(1, instr.length()).split("/");
				
				switch(instr.charAt(0)) {
					case 's':
						Collections.rotate(programs, Integer.parseInt(instr.substring(1)));
						break;
					case 'x':
						pos1 = Integer.parseInt(params[0]);
						pos2 = Integer.parseInt(params[1]);
						
						temp = programs.get(pos1);
						programs.set(pos1, programs.get(pos2));
						programs.set(pos2, temp);
						break;
					case 'p':
						pos1 = programs.indexOf(params[0]);
						pos2 = programs.indexOf(params[1]);
						
						temp = programs.get(pos1);
						
						programs.set(pos1, programs.get(pos2));
						programs.set(pos2, temp);
						break;
				}
			}
		}
		System.out.println(programs.stream().reduce((p, n) -> p+n).get());
	}
}
