package day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day19 {
	public static void main(String[] args) throws IOException {
		List<String> input = Files.readAllLines(Paths.get("./src/day19/input"));
		
		char[][] grid = input.stream().map(s -> s.replace("\n", "").replace("\r", "").toCharArray()).toArray(char[][]::new);
		
		Direction currDirection = Direction.DOWN;
		int[] coords = new int[] { input.get(0).indexOf("|"), 0 };
		String answer = "";
		int steps = 0;
		
		while(true) {
			steps++;
			coords = next(coords, currDirection);
			char c = grid[coords[1]][coords[0]];
			
			if(c == ' ') {
				break;
			} else if(c == '+') {
				currDirection = switchDirection(grid, coords, currDirection);
			} else if(Character.isLetter(c)) {
				answer += Character.toString(c);
			}
		}
		
		System.out.println(answer);
		System.out.println(steps);
	}
	
	enum Direction {
		UP, DOWN, LEFT, RIGHT
	}
	
	public static int[] next(int[] coords, Direction direction) {
		switch(direction) {
			case UP:
				coords[1]--;
				break;
			case DOWN:
				coords[1]++;
				break;
			case LEFT:
				coords[0]--;
				break;
			case RIGHT:
				coords[0]++;
				break;
		}
		
		return coords;
	}
	
	public static Direction switchDirection(char[][] grid, int[] coords, Direction currentDirection) {
		if((!currentDirection.equals(Direction.DOWN) && !currentDirection.equals(Direction.UP))) {
			if(grid[coords[1]+1][coords[0]] == '|') {
				return Direction.DOWN;
			} else {
				return Direction.UP;
			}
		}

		if((!currentDirection.equals(Direction.LEFT) && !currentDirection.equals(Direction.RIGHT))) {
			if(grid[coords[1]][coords[0]-1] == '-') {
				return Direction.LEFT;
			} else {
				return Direction.RIGHT;
			}
		}
		
		return null;
	}
}