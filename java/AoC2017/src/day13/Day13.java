package day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Day13 {
	
	public static void main(String[] args) throws IOException {
		List<String> input = Files.readAllLines(Paths.get("./src/day13/input"));
		int total = 0;
		int wait = 0;
		int depth = 0;
		
		HashMap<Integer, int[]> firewall = new HashMap<>();
	
		for(String line : input) {
			String[] nums = line.trim().split(": ");
			
			firewall.put(Integer.parseInt(nums[0]), new int[] { Integer.parseInt(nums[1]), 0, 0 });
		}
		
		while(depth < 96) {
			if(firewall.containsKey(depth) && firewall.get(depth)[2] == 0) {
				total += firewall.get(depth)[0]*depth;
			}
			for(Integer layer : firewall.keySet()) {
				int[] scanner = firewall.get(layer);
				if(scanner[2] == scanner[0]-1) {
					scanner[1] = 1;
				} else if(scanner[2] == 0) {
					scanner[1] = 0;
				}
				if(scanner[1] == 1) {
					scanner[2]--;
				} else {
					scanner[2]++;
				}
			}
			
			depth++;
		}
		System.out.println(total);
		
		while(true) {
			boolean caught = false;
			firewall.clear();
			for(String line : input) {
				String[] nums = line.trim().split(": ");
				firewall.put(Integer.parseInt(nums[0]), new int[] { Integer.parseInt(nums[1]), 0, 0 });
			}
			total = 0;
			depth = 0;
			for(int i = 0; i<wait; i++) {
				for(Integer layer : firewall.keySet()) {
					int[] scanner = firewall.get(layer);
					if(scanner[2] == scanner[0]-1) {
						scanner[1] = 1;
					} else if(scanner[2] == 0) {
						scanner[1] = 0;
					}
					if(scanner[1] == 1) {
						scanner[2]--;
					} else {
						scanner[2]++;
					}
				}
			}
			
			while(depth < 96) {
				if(firewall.containsKey(depth) && firewall.get(depth)[2] == 0) {
					caught = true;
					break;
				}
				for(Integer layer : firewall.keySet()) {
					int[] scanner = firewall.get(layer);
					if(scanner[2] == scanner[0]-1) {
						scanner[1] = 1;
					} else if(scanner[2] == 0) {
						scanner[1] = 0;
					}
					if(scanner[1] == 1) {
						scanner[2]--;
					} else {
						scanner[2]++;
					}
				}
				
				depth++;
			}
			if(caught) {
				wait++;
				if(wait % 10000 == 0) {
					System.out.println("Wait: " + wait);
				}
			} else {
				System.out.println(wait);
				break;
			}
		}
 	}
}
