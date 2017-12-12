package day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day12 {
	
	public static Set<Integer> group = new HashSet<>();
	public static HashMap<Integer, List<Integer>> programs = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("./src/day12/input"));
		
		for(String line : lines) {
			String[] split = line.trim().split(" <-> ");
			programs.put(Integer.parseInt(split[0].trim()), 
					Arrays.stream(split[1].trim().split(", "))
						.mapToInt(Integer::parseInt)
						.boxed()
						.collect(Collectors.toList()));
		}
		// part 1
		System.out.println(findGroup(0).size());
		
		//part 2
		List<Set<Integer>> groups = new ArrayList<>();
		for(int num : programs.keySet()) {
			group.clear();
			Set<Integer> set = new HashSet<Integer>(findGroup(num));
			if(!groups.contains(set))
				groups.add(set);
		}
		
		System.out.println(groups.size());
 	}
	
	public static Set<Integer> findGroup(int program) {
		
		for(int num : programs.get(program)) {
			if(!group.contains(num)) {
				group.add(num);
				group.addAll(findGroup(num));
			}
		}
		
		return group;
	}
}
