package day23;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class Program {
	private int pc, id, amt;
	private HashMap<String, Long> registers = new HashMap<>();
	private List<String[]> instructions;
	private boolean terminated;
	
	public Program(int id, List<String> input) {
		this.id = id;
		this.terminated = false;
		
		registers.put("a", 0l);
		registers.put("b", 0l);
		registers.put("c", 0l);
		registers.put("d", 0l);
		registers.put("e", 0l);
		registers.put("f", 0l);
		registers.put("g", 0l);
		registers.put("h", 0l);
		
		instructions = input.stream().map(n -> n.trim().split(" ")).collect(Collectors.toList());
	}
	
	public int getID() { return id; }
	
	public void loop() {
		if(pc >= instructions.size() || pc < 0) {
			terminated = true;
			return;
		}
		
		
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
				amt++;
				break;
			case "sub":
				registers.put(instruction[1], registers.get(instruction[1])-param);
				break;
			case "jnz":
				long x;
				if(StringUtils.isAlpha(instruction[1])) {
					x = registers.get(instruction[1]);
				} else {
					x = Integer.parseInt(instruction[1]);
				}
				
				if(x != 0) {
					pc += param;
					return;
				}
				break;
			default:
		}
		
		pc++;
	}
	
	public boolean isTerminated() { return terminated; }
	
	public int getAmt() { return amt; }
}
