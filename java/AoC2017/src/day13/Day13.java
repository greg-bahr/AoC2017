package day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day13 {
	public static void main(String[] args) throws IOException {
		
		List<String> lines = Files.readAllLines(Paths.get("./src/day13/input"));
		List<int[]> scanners = new ArrayList<>();
		
		for(String line : lines) {
			String[] nums = line.trim().split(": ");
			scanners.add(new int[] { Integer.parseInt(nums[0]), Integer.parseInt(nums[1]) });
		}
		
		int total = 0;
		
		for(int[] scanner : scanners) {
			if(scanner[0] % (2*(scanner[1]-1)) == 0) total += scanner[0]*scanner[1];
		}
		
		System.out.println(total);
		
		int wait = 0;
		
		while(true) {
			boolean caught = false;
			wait++;
			
			for(int[] scanner : scanners) {
				if((wait+scanner[0]) % (2*(scanner[1]-1)) == 0) caught = true;
			}
			
			if(!caught) break;
		}
		
		System.out.println(wait);
	}
}
