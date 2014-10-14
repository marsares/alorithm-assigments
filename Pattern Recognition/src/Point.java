/**
 * <p>
 * an immutable data type Point that represents a point in the plane
 * </p>
 * 
 * @author YuanBo
 * @version 1.0
 */
import java.util.Arrays;
import java.util.Comparator;

public class Point implements Comparable<Point> {
	// compare points by slope
	public final Comparator<Point> SLOPE_ORDER = new SLOPE_ORDER(this); // YOUR
	// DEFINITION
	// HERE

	private final int x; // x coordinate
	private final int y; // y coordinate

	// create the point (x, y)
	public Point(int x, int y) {
		/* DO NOT MODIFY */
		this.x = x;
		this.y = y;
	}

	// plot this point to standard drawing
	public void draw() {
		/* DO NOT MODIFY */
		StdDraw.point(x, y);
	}

	// draw line between this point and that point to standard drawing
	public void drawTo(Point that) {
		/* DO NOT MODIFY */
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	// slope between this point and that point
	public double slopeTo(Point that) {
		if (this.x == that.x && this.y == that.y)
			return Double.NEGATIVE_INFINITY;
		else if (this.x == that.x)
			return Double.POSITIVE_INFINITY;
		else if (this.y == that.y)
			return 0.0;
		else {
			double y1 = this.y;
			double y0 = that.y;
			double x1 = this.x;
			double x0 = that.x;
			return (y1 - y0) / (x1 - x0);
		}
	}

	// is this point lexicographically smaller than that one?
	// comparing y-coordinates and breaking ties by x-coordinates
	public int compareTo(Point that) {
		if (this.y < that.y || (this.y == that.y && this.x < that.x))
			return -1;
		else if (this.y == that.y && this.x == that.x)
			return 0;
		else
			return 1;
	}

	// return string representation of this point
	public String toString() {
		/* DO NOT MODIFY */
		return "(" + x + ", " + y + ")";
	}

	private class SLOPE_ORDER implements Comparator<Point> {
		Point p;

		public SLOPE_ORDER(Point p) {
			this.p = p;
		}

		public int compare(Point v, Point w) {
			if (p.slopeTo(v) < p.slopeTo(w)) {
				return -1;
			} else if (p.slopeTo(v) == p.slopeTo(w)) {
				return 0;
			} else {
				return 1;
			}

		}
	}

	// unit test
	public static void main(String[] args) {
		In in = new In(args[0]);
		int N = in.readInt();// number of input points
		Point[] p = new Point[N];
		int t = 0;
		// store all points in array p
		while (!in.isEmpty()) {
			p[t] = new Point(in.readInt(), in.readInt());
			t++;
		}
		Arrays.sort(p);
		for (int k = 0; k <= 7; k++) {
			System.out.println(p[k].toString());
		}
		System.out.println("****");
		Comparator<Point> c = p[6].SLOPE_ORDER;
		Arrays.sort(p, 0, 6, c);
		for (int k = 0; k <= 6; k++) {
			System.out.println(p[k].toString() + "slope:" + p[k].slopeTo(p[6]));
		}
	}
}
