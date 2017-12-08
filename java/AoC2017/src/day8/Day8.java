package day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Day8 {
	public static void main(String[] args) throws IOException, ScriptException {
		List<String> lines = Files.readAllLines(Paths.get("./src/day8/input"));
		
		ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
		
		List<List<String>> instructions = new ArrayList<>();
		for(String line : lines) {
			instructions.add(Arrays.asList(line.split(" ")));
		}
		
		int max = 0;
		
		HashMap<String, Integer> registers = new HashMap<>();
		
		for(List<String> instruction : instructions) {
			if(!registers.containsKey(instruction.get(0))) {
				registers.put(instruction.get(0), 0);
			} 
			if(!registers.containsKey(instruction.get(4))) {
				registers.put(instruction.get(4), 0);
			}
			
			switch(instruction.get(1)) {
				case "dec":
					if(engine.eval(registers.get(instruction.get(4))+instruction.get(5)+instruction.get(6)).equals(true)) {
						registers.put(instruction.get(0), registers.get(instruction.get(0))-Integer.parseInt(instruction.get(2)));
					}
					break;
				case "inc":
					if(engine.eval(registers.get(instruction.get(4))+instruction.get(5)+instruction.get(6)).equals(true)) {
						registers.put(instruction.get(0), registers.get(instruction.get(0))+Integer.parseInt(instruction.get(2)));
					}
					break;
				default:
			}
			
			for(String x : registers.keySet()) {
				if(registers.get(x) > max) max = registers.get(x);
			}
		}
		
		System.out.println(Collections.max(registers.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getValue());
		System.out.println(max);
	}

}
