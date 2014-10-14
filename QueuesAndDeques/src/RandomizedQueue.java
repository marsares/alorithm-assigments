/**
 * <p>
 * this program is to Create a generic data type RandomizedQueue similar to a stack but remove item randomly
 * </p>
 * 
 * @author YuanBo
 * @version 1.0
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] a;
	private int N;

	/**
	 * construct an empty randomized queue
	 */
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		a = (Item[]) new Object[2];
		N = 0;
	}

	/**
	 * <p>
	 * check is the deque empty
	 * </p>
	 * 
	 * @return check result
	 */
	public boolean isEmpty() {
		return N == 0;
	}

	/**
	 * <p>
	 * show the number of items on the deque
	 * </p>
	 * 
	 * @return the number of items on the deque
	 */
	public int size() {
		return N;
	}

	/**
	 * <p>
	 * add the item
	 * </p>
	 * 
	 * @param item
	 */
	public void enqueue(Item item) {
		if (N == a.length)
			resize(2 * a.length);
		a[N++] = item;
	}

	/**
	 * <p>
	 * delete and return a random item
	 * </p>
	 * 
	 * @return random item
	 */
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow.");
		Item temp = a[N - 1];
		int i = StdRandom.uniform(N);
		a[N - 1] = a[i];
		a[i] = temp;
		temp = a[N - 1];
		a[--N] = null;
		if (N > 0 && N == a.length / 4)
			resize(a.length / 2);
		return temp;
	}

	/**
	 * <p>
	 * return (but do not delete) a random item
	 * </p>
	 * 
	 * @return random item
	 */
	public Item sample() {
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow.");
		return a[StdRandom.uniform(N)];
	}

	/**
	 * <p>
	 * return an independent iterator over items in random order
	 * </p>
	 */
	@SuppressWarnings("unchecked")
	public Iterator<Item> iterator() {
		return new MyIterator();
	}

	public static void main(String[] args) {
		RandomizedQueue rq=new RandomizedQueue();
		rq.enqueue("a");
		rq.enqueue("b");
		rq.enqueue("c");
		for(int i=0;i<3;i++){
			System.out.println(rq.dequeue());
		}
	}

	private void resize(int capacity) {
		assert capacity >= N;
		@SuppressWarnings("unchecked")
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			temp[i] = a[i];
		}
		a = temp;
	}

	private class MyIterator implements Iterator {
		private Item[] temp;
		private int length;

		@SuppressWarnings("unchecked")
		public MyIterator() {
			temp = (Item[]) new Object[N];
			for (int i = 0; i < N; i++) {
				temp[i] = a[i];
			}
			length = N;
		}

		public boolean hasNext() {
			return length != 0;
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException("there is no such element.");
			int i = StdRandom.uniform(length);
			Item tempelement = temp[length - 1];
			temp[length-1] = temp[i];
			temp[i] = tempelement;
			tempelement = temp[length - 1];
			temp[--length] = null;
			return tempelement;
		}

		public void remove() {
			throw new UnsupportedOperationException("remove() is not supported");
		}
	}
}
