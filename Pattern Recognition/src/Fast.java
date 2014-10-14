/**
 * <p>
 * find more than four points lie on the same line segment
 * </p>
 * 
 * @author YuanBo
 * @version 1.0
 */
import java.util.Arrays;

public class Fast {

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
		int duplicateflag;
		double slopeflag;
		boolean flag;
		// p[i] as the origin
		for (int i = N - 1; i >= 3; i--) {
			Arrays.sort(p, 0, i + 1);
			// sort the points according to the slopes they makes with p
			Arrays.sort(p, 0, i, p[i].SLOPE_ORDER);
			duplicateflag = 0;
			slopeflag = p[i].slopeTo(p[0]);
			// check if any 3 (or more) adjacent points in the sorted order have
			// equal slopes with respect to p. If so, these points, together
			// with p, are collinear.
			for (int j = 0; j < i; j++) {
				if (slopeflag == p[i].slopeTo(p[j]) && j != 0) {
					duplicateflag++;
				} else {
					if (duplicateflag >= 2) {
						flag = true;
						if (i == N - 1)
							flag = true;
						else {
							for (int l = i + 1; l < N; l++) {
								if (p[i].slopeTo(p[l]) == slopeflag) {
									flag = false;
									break;
								}
							}
						}
						if (flag) {
							for (int k = j - duplicateflag - 1; k < j; k++) {
								System.out.print(p[k].toString() + " -> ");
							}
							System.out.println(p[i].toString());
							p[i].drawTo(p[j - duplicateflag - 1]);
						}
					}
					duplicateflag = 0;
				}
				slopeflag = p[i].slopeTo(p[j]);
				if (j == i - 1 && duplicateflag >= 2) {
					flag = true;
					if (i == N - 1)
						flag = true;
					else {
						for (int l = i + 1; l < N; l++) {
							if (p[i].slopeTo(p[l]) == slopeflag) {
								flag = false;
								break;
							}
						}
					}
					if (flag) {
						for (int k = j - duplicateflag; k <= j; k++) {
							System.out.print(p[k].toString() + " -> ");
						}
						System.out.println(p[i].toString());
						p[i].drawTo(p[j - duplicateflag]);
					}
				}
			}
		}
	}
}
