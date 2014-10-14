/**
 * <p>
 * use a brute force way to find four points lie on the same line segment
 * </p>
 * 
 * @author YuanBo
 * @version 1.0
 */
import java.util.Arrays;

public class Brute {
	public static void main(String[] args) {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		In in = new In(args[0]);
		int N = in.readInt();// number of input points
		Point[] p = new Point[N];
		int t = 0;
		// store all points in array p
		while (!in.isEmpty()) {
			p[t] = new Point(in.readInt(), in.readInt());
			p[t].draw();
			t++;
		}
		// sort points by y and x
		Arrays.sort(p);
		// check whether each combine of 4 points lie on the same line
		for (int i = 0; i < N; i++)
			for (int j = i + 1; j < N; j++)
				for (int k = j + 1; k < N; k++)
					for (int l = k + 1; l < N; l++) {
						if (p[i].slopeTo(p[j]) == p[i].slopeTo(p[k])
								&& p[i].slopeTo(p[k]) == p[i].slopeTo(p[l])) {
							System.out.println(p[i].toString() + " -> "
									+ p[j].toString() + " -> "
									+ p[k].toString() + " -> "
									+ p[l].toString());
							p[i].drawTo(p[l]);
						}
					}
	}
}
