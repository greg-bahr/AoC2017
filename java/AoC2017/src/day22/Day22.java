package day22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Day22 {
	public static void main(String[] args) throws IOException {
		List<String> input = Files.readAllLines(Paths.get("./src/day22/input"));
		
		HashMap<Point, Character> grid = new HashMap<>();
		
		for(int i = 0; i < input.size(); i++) {
			for(int j = 0; j < input.get(i).length(); j++) {
				Point p = new Point(j, i);
				char c = input.get(i).charAt(j);
				
				grid.put(p, c);
			}
		}
		
		Point carrier = new Point(12, 12);
		int direction = 0; // 0up, 1right, 2down, 3left
		int num = 0;

		for(int i = 0; i < 10000; i++) {
			if(!grid.containsKey(carrier.clone())) {
				grid.put(carrier.clone(), '.');
			}
			
			if(grid.get(carrier) == '.') {
				direction = (direction-1)%4;
				if(direction < 0) direction += 4;
				grid.put(carrier.clone(), '#');
				num++;
			} else {
				direction = (direction+1)%4;
				
				grid.put(carrier.clone(), '.');
			}
			switch(direction) {
				case 0:
					carrier.y--;
					break;
				case 1:
					carrier.x++;
					break;
				case 2:
					carrier.y++;
					break;
				case 3:
					carrier.x--;
					break;
			}
		}
		
		System.out.println(num);
		
		HashMap<Point, Integer> grid2 = new HashMap<>();
		
		for(int i = 0; i < input.size(); i++) {
			for(int j = 0; j < input.get(i).length(); j++) {
				Point p = new Point(j, i);
				int f = 0;
				if(input.get(i).charAt(j) != '.') {
					f = 2;
				}
				
				grid2.put(p, f);
			}
		}
		
		carrier = new Point(12, 12);
		direction = 0; // 0up, 1right, 2down, 3left
		num = 0;
		
		for(int i = 0; i < 10000000; i++) {
			if(!grid2.containsKey(carrier.clone())) {
				grid2.put(carrier.clone(), 0);
			}
			// clean0, weak1, inf2, flag3
			if(grid2.get(carrier) == 0) {
				direction = (direction-1)%4;
				if(direction < 0) direction += 4;
				grid2.put(carrier.clone(), 1);
			} else if(grid2.get(carrier) == 1) {
				grid2.put(carrier.clone(), 2);
				num++;
			} else if(grid2.get(carrier) == 2) {
				direction = (direction+1)%4;
				grid2.put(carrier.clone(), 3);
			} else if(grid2.get(carrier) == 3) {
				direction = (direction+2)%4;
				grid2.put(carrier.clone(), 0);
			}
			
			switch(direction) {
				case 0:
					carrier.y--;
					break;
				case 1:
					carrier.x++;
					break;
				case 2:
					carrier.y++;
					break;
				case 3:
					carrier.x--;
					break;
			}
		}
		
		System.out.println(num);
	}
}