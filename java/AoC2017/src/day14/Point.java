package day14;

import java.util.ArrayList;
import java.util.List;

public class Point {
	public int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public List<Point> getNeighbors() {
		List<Point> points = new ArrayList<>();
		
		if(x > 0) {
			points.add(new Point(x-1, y));
		}
		if(y > 0) {
			points.add(new Point(x, y-1));
		}
		if(x < 127) {
			points.add(new Point(x+1, y));
		}
		if(y < 127) {
			points.add(new Point(x, y+1));
		}
		return points;
	}
}
