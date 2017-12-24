package day22;

public class Point {
	public int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	 @Override
	    public int hashCode() {
	        int result = 17;
	        result = 31 * result + x;
	        result = 31 * result + y;
	        return result;
	    }
	
	@Override
	public boolean equals(Object obj) {
	    if (!(obj instanceof Point)) {
            return false;
        }

        Point other = (Point) obj;
        return this.x == other.x && this.y == other.y;
	}
	
	@Override
	public String toString() {
		return "Point ["+ x + ", " + y + "]";
	}

	public Point clone() {
		return new Point(x, y);
	}
}
