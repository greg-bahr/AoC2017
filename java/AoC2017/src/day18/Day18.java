package day18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class Day18 {

	public static void main(String[] args) throws IOException {
		List<String> input = Files.readAllLines(Paths.get("./src/day18/input"));
		
		List<String[]> instructions = input.stream().map(n -> n.trim().split(" ")).collect(Collectors.toList());
		
		int pc = 0;
		HashMap<String, Long> registers = new HashMap<>();
		registers.put("a", 0l);
		registers.put("p", 0l);
		registers.put("i", 0l);
		registers.put("b", 0l);
		registers.put("f", 0l);
		
		long lastSound = 0;
		long rcv = 0;
		
		main:
		while(pc < instructions.size() && pc >= 0) {
			String[] instruction = instructions.get(pc);
			long param = 0;
			
			if(instruction.length == 3) {
				param = StringUtils.isAlpha(instruction[2]) ? registers.get(instruction[2]) : Integer.parseInt(instruction[2]);
			} else {
				param = StringUtils.isAlpha(instruction[1]) ? registers.get(instruction[1]) : Integer.parseInt(instruction[1]);
			}
			
			switch(instruction[0]) {
				case "set":
					registers.put(instruction[1], param);
					break;
				case "mul":
					registers.put(instruction[1], registers.get(instruction[1])*param);
					break;
				case "snd":
					lastSound = param;
					break;
				case "add":
					registers.put(instruction[1], registers.get(instruction[1])+param);
					break;
				case "mod":
					registers.put(instruction[1], registers.get(instruction[1])%param);
					break;
				case "rcv":
					if(param != 0) {
						rcv = lastSound;
						System.out.println(rcv);
						break main;
					}
					break;
				case "jgz":
					long x;
					if(StringUtils.isAlpha(instruction[1])) {
						x = registers.get(instruction[1]);
					} else {
						x = Integer.parseInt(instruction[1]);
					}
					
					if(x > 0) {
						pc += param;
						continue;
					}
					break;
				default:
			}
			
			pc++;
		}
		
		
		Program p0, p1;
		p0 = new Program(0, input);
		p1 = new Program(1, input);
		p1.setOtherProgram(p0);
		p0.setOtherProgram(p1);
		
		while(!p1.isTerminated() || !p0.isTerminated()) {
			p0.loop();
			p1.loop();
		}
		
		System.out.println(p1.getSent());
	}
}

