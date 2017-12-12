package day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day11 {
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("./src/day11/input"));
		String[] instr = lines.get(0).trim().split(",");
		int[] coords = new int[2];
		int max = 0;
		
		for(String dir : instr) {
			switch(dir) {
				case "n":
					coords[1]--;
					break;
				case "nw":
					coords[0]--;
					break;
				case "ne":
					coords[0]++;
					coords[1]--;
					break;
				case "s":
					coords[1]++;
					break;
				case "sw":
					coords[0]--;
					coords[1]++;
					break;
				case "se":
					coords[0]++;
					break;
			}
			
			int dist = Arrays.asList(coords[0], -coords[0] -coords[1], coords[1]).stream().mapToInt(Math::abs).max().getAsInt();
			if(dist > max) max = dist;
		}
		
		System.out.println(Arrays.asList(coords[0], -coords[0] -coords[1], coords[1]).stream().mapToInt(Math::abs).max().getAsInt());
		System.out.println(max);
 	}

}
