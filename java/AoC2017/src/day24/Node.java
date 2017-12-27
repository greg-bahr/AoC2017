package day24;

public class Node {
	public int x, y;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean contains(int n) { return x == n || y == n; }
	
	public int get(int i) {
		if(i==0) {
			return x;
		} else if(i == 1) {
			return y;
		}
		return x;
	}
	
	@Override
	public String toString() {
		return "["+x+", "+y+"]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
}
