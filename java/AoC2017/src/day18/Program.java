package day18;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class Program {
	private int pc, id, sent;
	private HashMap<String, Long> registers = new HashMap<>();
	private ArrayList<Long> messages;
	private List<String[]> instructions;
	private boolean terminated, waiting;
	private Program otherProgram;
	
	public Program(int id, List<String> input) {
		this.id = id;
		this.terminated = false;
		
		registers.put("a", 0l);
		registers.put("p", (long)id);
		registers.put("i", 0l);
		registers.put("b", 0l);
		registers.put("f", 0l);
		
		messages = new ArrayList<>();
		instructions = input.stream().map(n -> n.trim().split(" ")).collect(Collectors.toList());
	}
	
	public int getID() { return id; }
	
	public void loop() {
		if((pc > instructions.size() && pc >= 0) || (waiting && otherProgram.isWaiting())) {
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
				break;
			case "snd":
				otherProgram.addMessage(param);
				sent++;
				break;
			case "add":
				registers.put(instruction[1], registers.get(instruction[1])+param);
				break;
			case "mod":
				registers.put(instruction[1], registers.get(instruction[1])%param);
				break;
			case "rcv":
				if(messages.size() == 0) {
					waiting = true;
					return;
				} else {
					registers.put(instruction[1], messages.get(0));
					messages.remove(0);
					waiting = false;
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
					return;
				}
				break;
			default:
		}
		
		pc++;
	}
	
	public boolean isTerminated() { return terminated; }
	
	public int getSent() { return sent; }
	
	public void setOtherProgram(Program program) { this.otherProgram = program; }
	
	public boolean isWaiting() { return waiting; }
	
	public void addMessage(long message) { messages.add(message); }
}
