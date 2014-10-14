/**
 * <p>
 * this program is a client program using RandomizedQueue.
 * </p>
 * 
 * @author YuanBo
 * @version 1.0
 */
public class Subset {
	public static void main(String[] args) {
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()) {
			rq.enqueue(StdIn.readString());
		}
		for (int i = 0; i < Integer.parseInt(args[0]); i++) {
			System.out.println(rq.dequeue());
		}
	}
}
