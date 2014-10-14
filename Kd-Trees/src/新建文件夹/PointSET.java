/**
 * <p>
 * represent a set of points in the unit square using brute force to support
 * efficient range search and nearest neighbor search.
 * </p>
 * 
 * @author YuanBo
 * @version 1.0
 */
public class PointSET {
	// a red-black BST contains all nodes
	private SET<Point2D> pointset;

	/**
	 * <p>
	 * construct an empty set of points
	 * </p>
	 */
	public PointSET() {
		pointset = new SET<Point2D>();
	}

	/**
	 * <p>
	 * is the set empty?
	 * </p>
	 * 
	 * @return whether the set is empty
	 */
	public boolean isEmpty() {
		return pointset.isEmpty();
	}

	/**
	 * <p>
	 * number of points in the set
	 * </p>
	 * 
	 * @return number of points in the set
	 */
	public int size() {
		return pointset.size();
	}

	/**
	 * <p>
	 * add the point to the set (if it is not already in the set)
	 * </p>
	 * 
	 * @param p
	 *            the point to be inserted
	 */
	public void insert(Point2D p) {
		pointset.add(p);
	}

	/**
	 * <p>
	 * does the set contain point p?
	 * </p>
	 * 
	 * @param p
	 *            point p
	 * @return whether the set contains point p
	 */
	public boolean contains(Point2D p) {
		return pointset.contains(p);
	}

	/**
	 * <p>
	 * draw all points to standard draw
	 * </p>
	 */
	public void draw() {
		for (Point2D point : pointset) {
			point.draw();
		}
	}

	/**
	 * <p>
	 * all points that are inside the rectangle
	 * </p>
	 * 
	 * @param rect
	 *            the rectangle
	 * @return all points that are inside the rectangle
	 */
	public Iterable<Point2D> range(RectHV rect) {
		Stack<Point2D> points = new Stack<Point2D>();
		for (Point2D p : pointset) {
			if (rect.contains(p)) {
				points.push(p);
			}
		}
		return points;
	}

	/**
	 * <p>
	 * all points that are inside the rectangle
	 * </p>
	 * 
	 * @param rect
	 *            the rectangle
	 * @return all points that are inside the rectangle
	 */
	public Point2D nearest(Point2D p) {
		double distance = Double.MAX_VALUE;
		Point2D nearestpoint = null;
		for (Point2D point : pointset) {
			if (point.distanceTo(p) < distance) {
				distance = point.distanceTo(p);
				nearestpoint = point;
			}
		}
		return nearestpoint;
	}

	public static void main(String[] args) {

	}
}
