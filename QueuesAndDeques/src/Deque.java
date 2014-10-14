/**
 * <p>
 * this program is to support inserting and removing items from either the front or the back of the data structure
 * </p>
 * 
 * @author YuanBo
 * @version 1.0
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node first;
	private Node last;
	private int size;

	/**
	 * <p>
	 * construct an empty deque
	 * </p>
	 */
	public Deque() {
		last = null;
		first = null;
		size = 0;
	}

	/**
	 * <p>
	 * check is the deque empty
	 * </p>
	 * 
	 * @return check result
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * <p>
	 * show the number of items on the deque
	 * </p>
	 * 
	 * @return the number of items on the deque
	 */
	public int size() {
		return size;
	}

	/**
	 * <p>
	 * insert the item at the front
	 * </p>
	 * 
	 * @param item
	 */
	public void addFirst(Item item) {
		checkNullPointer(item);
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		if (isEmpty())
			last = first;
		else
			oldfirst.previous = first;
		size++;
	}

	/**
	 * <p>
	 * insert the item at the end
	 * </p>
	 * 
	 * @param item
	 */
	public void addLast(Item item) {
		checkNullPointer(item);
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else {
			oldlast.next = last;
			last.previous = oldlast;
		}
		size++;
	}

	/**
	 * <p>
	 * delete and return the item at the front
	 * </p>
	 * 
	 * @return the item at the front
	 */
	public Item removeFirst() {
		checkEmpty();
		Item item = first.item;
		first = first.next;
		size--;
		if (isEmpty())
			last = null;
		else
			first.previous = null;
		return item;
	}

	/**
	 * <p>
	 * delete and return the item at the end
	 * </p>
	 * 
	 * @return the item at the end
	 */
	public Item removeLast() {
		checkEmpty();
		Item item = last.item;
		last = last.previous;
		size--;
		if (isEmpty())
			first = null;
		else
			last.next = null;
		return item;
	}

	/**
	 * <p>
	 * return an iterator over items in order from front to end
	 * </p>
	 */
	public Iterator<Item> iterator() {
		return new MyIterator();
	}

	public static void main(String[] args) {
		Deque deq = new Deque();
		String element;
		element = StdIn.readString();
		while (!element.equals("first")) {
			deq.addFirst(element);
			element = StdIn.readString();
		}

		element = StdIn.readString();
		while (!element.equals("last")) {
			deq.addLast(element);
			element = StdIn.readString();
		}
		deq.removeFirst();
		deq.removeLast();
		int n = deq.size();
		Iterator iterator = deq.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			System.out.println(iterator.next() + " " + i);
			i++;
		}
	}

	private class Node {
		Item item;
		Node next;
		Node previous;
	}

	private class MyIterator implements Iterator {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			String msg = "remove() is not supported.";
			UnsupportedOperationException uop = new UnsupportedOperationException(
					msg);
			throw uop;
		}

		public Item next() {
			if (current == null) {
				String msg = "there is no such element";
				NoSuchElementException nse = new NoSuchElementException(msg);
				throw nse;
			}
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	private void checkNullPointer(Item item) {
		if (item == null) {
			String msg = "item is null.";
			NullPointerException npe = new NullPointerException(msg);
			throw npe;
		}
	}

	private void checkEmpty() {
		if (isEmpty()) {
			String msg = "there is no such element";
			NoSuchElementException nse = new NoSuchElementException(msg);
			throw nse;
		}
	}

}
