package day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public class Day7 {
	public static HashMap<String, Pair<Integer, List<String>>> towers;
	static {
		towers = new HashMap<>();
	}

	public static void main(String[] args) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("./src/day7/input"));
		
		for(String line : lines) {
			String[] temp = line.split(" ", 4);
			String name = temp[0];
			int weight = Integer.parseInt(temp[1].replaceAll("[^-?0-9]+", ""));
			List<String> children = new ArrayList<>();
			
			if(temp.length == 4) {
				children = Arrays.asList(temp[3].trim().split(", "));
			}
			
			towers.put(name, Pair.of(weight, children));
		}
		
		// Part 1
		for(String tower : towers.keySet()) {
			String parent = null;
			for(String otherTower : towers.keySet()) {
				if(!tower.equals(otherTower)) {
					if(towers.get(otherTower).getRight().contains(tower)) {
						parent = otherTower;
					}
				}
			}
			if(parent == null) {
				System.out.println(tower);
				HashMap<Integer, String> towerWeights = new HashMap<>();
				String bad = tower;
				int expectedWeight = 0;
				while(true) {
					for(String child : towers.get(bad).getRight()) {
						towerWeights.put(findWeight(child), child);
					}
					if(towerWeights.keySet().size() == 1) {
						System.out.println(bad + " should be " + (expectedWeight-(3*Collections.min(towerWeights.keySet()))));
						break;
					} else {
						expectedWeight = Collections.min(towerWeights.keySet());
						bad = towerWeights.get(Collections.max(towerWeights.keySet()));
						towerWeights.clear();
					}
				}
				
			}
		}
	}
	
	public static int findWeight(String tower) {
		List<String> children = towers.get(tower).getRight();
		int weight = towers.get(tower).getLeft();
		for(String child : children) {
			weight += findWeight(child);
		}
		return weight;
	}

}
