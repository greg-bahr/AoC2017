package day21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day21 {
	public static void main(String[] args) throws IOException {
		List<String> input = Files.readAllLines(Paths.get("./src/day21/input"));
		
		HashMap<List<List<Character>>, List<List<Character>>> rules = new HashMap<>();
		
		List<List<Character>> grid = Arrays.asList(Arrays.asList('.', '#', '.'),
												Arrays.asList('.', '.', '#'),
												Arrays.asList('#', '#', '#'));
		
		for(String line : input) {
			String[] temp = line.trim().split(" => ");
			rules.put(Arrays.stream(temp[0].trim().split("/"))
					.map(a -> a.chars().mapToObj(c -> (char) c).collect(Collectors.toList()))
					.collect(Collectors.toList()), 
					Arrays.stream(temp[1].trim().split("/"))
					.map(a -> a.chars().mapToObj(c -> (char) c).collect(Collectors.toList()))
					.collect(Collectors.toList()));
		}
		
		for(int y = 0; y < 5; y++) {
			int subSize = grid.size() % 2 == 0 ? 2 : 3;
			int splitAmt = (int)Math.pow(grid.size()/subSize, 2);
			int size = (grid.size()/subSize)*(subSize+1);
			List<List<Character>> temp = new ArrayList<>();
			for(int x = 0; x < size; x++) {
				ArrayList<Character> row = new ArrayList<>();
				for(int z = 0; z < size; z++) {
					row.add('F');
				}
				temp.add(row);
			}
			
			for(int i = 0; i < splitAmt; i++) {
				List<List<Character>> section = split(grid, i);
				List<List<Character>> replacement = new ArrayList<>();
		
				for(int x = 0; x < 4; x++) {
					List<List<Character>> rot = section;
					for(int z = 0; z < x; z++) {
						rot = rotate(rot);
					}
					
					if(rules.containsKey(rot)) {
						replacement = rules.get(rot);
						break;
					} else if(rules.containsKey(hFlip(rot))) {
						replacement = rules.get(hFlip(rot));
						break;
					} else if(rules.containsKey(vFlip(rot))) {
						replacement = rules.get(vFlip(rot));
						break;
					}
				}

				
				int subSections = temp.size() / replacement.size();
				for(int r = 0; r < replacement.size(); r++) {
					for(int c = 0; c < replacement.size(); c++) {
						int newX = c + ((i/subSections)*replacement.size());
						int newY = r + ((i%subSections)*replacement.size());
						
						temp.get(newY).set(newX, replacement.get(r).get(c));
					}
				}
				
			}
			grid = temp;
			
		}
		
		int sum = 0;
		
		for(List<Character> row : grid) {
			for(char c : row) {
				if(c == '#') sum++;
			}
		}
		
		System.out.println(sum);
		
	}
	
	public static List<List<Character>> hFlip(List<List<Character>> m) {
		List<List<Character>> ret = new ArrayList<>();
		for(List<Character> row : m) {
			ret.add(new ArrayList<>(row));
		}
		
		for(int i = 0; i < ret.size(); i++) {
			char temp = ret.get(i).get(0);
			ret.get(i).set(0, ret.get(i).get(ret.get(i).size()-1));
			ret.get(i).set(ret.get(i).size()-1, temp);
		}
		return ret;
	}
	
	public static List<List<Character>> vFlip(List<List<Character>> m) {
		List<List<Character>> ret = new ArrayList<>();
		for(List<Character> row : m) {
			ret.add(new ArrayList<>(row));
		}
		Collections.reverse(ret);
		return ret;
	}
	
	public static List<List<Character>> rotate(List<List<Character>> m) {
		List<List<Character>> ret = new ArrayList<>();
		for(List<Character> row : m) {
			ret.add(new ArrayList<>(row));
		}
		
		for(int r = 0; r < m.size(); r++) {
			for(int c = 0; c < m.get(r).size(); c++) {
				ret.get(c).set(m.size()-1-r, m.get(r).get(c));
			}
		}
		return ret;
	}
	
	public static List<List<Character>> split(List<List<Character>> section, int index) {
		if(section.size() <= 3) {
			return section;
		}
		int subSize = section.size()%2==0 ? 2 : 3;
		int splits = section.size()/subSize;
		List<List<Character>> ret = new ArrayList<>();
		for(int x = 0; x < subSize; x++) {
			ArrayList<Character> row = new ArrayList<>();
			for(int y = 0; y < subSize; y++) {
				row.add('S');
			}
			ret.add(row);
		}
		
		for(int i = 0; i<subSize; i++) {
			for(int j = 0; j < subSize; j++) {
				ret.get(i).set(j, section.get(i + ((index%splits)*subSize)).get(j + ((index/splits)*subSize)));
			}
		}
	
		return ret;
	}
}