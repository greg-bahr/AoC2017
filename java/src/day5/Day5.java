package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 {

	public static void main(String[] args) throws IOException{
		List<Integer> nums = Files.lines(Paths.get("./src/day5/input"))
					.mapToInt(Integer::parseInt)
					.boxed().collect(Collectors.toList());
		
		int pc = 0;
		int steps = 0;
		
		while(pc < nums.size()) {
			int temp = pc;
			pc += nums.get(pc);
			if(nums.get(temp) > 2) {
				nums.set(temp, nums.get(temp)-1);
			} else {
				nums.set(temp, nums.get(temp)+1);
			}
			
			steps++;
		}
		System.out.println(steps);
	}

}
