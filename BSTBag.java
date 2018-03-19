import java.util.Iterator;
import java.util.NoSuchElementException;

public class BSTBag<E extends Comparable<E>> implements Bag<E> {

	private Node<E> root;
	private int size = 0;

	public BSTBag () {
		root = null;
	}

	
	private static class Node<E extends Comparable<E>> {
		protected CountedElement<E> element;
		protected Node<E> left, right;

		protected Node (CountedElement<E> elem) {
			element = elem;
			left = null;
			right = null;
		}

		public Node<E> deleteTopmost () {
			if (this.left == null) {
				return this.right;
			}
			else if (this.right==null) {
				return this.left;
			}
			else {
				this.element = this.right.getLeftmost();
				this.right = this.right.deleteLeftmost();
				return this;
			}			
		}

		private CountedElement <E> getLeftmost () {
			Node<E> curr = this;
			while (curr.left != null) {
				curr = curr.left;
			}
			return curr.element;
		}

		public Node<E> deleteLeftmost () {
			if (this.left ==null) {
				return this.right;
			}
			else {
				Node<E> parent = this;
				Node<E> curr = this.left;
				while (curr.left != null) {
					parent = curr;
					curr = curr.left;
				}
				parent.left = curr.right;
				return this;
			}
		}
	}



	/*Auto generated methods from Bag*/

	public Node<E> getRoot() {
		return root;
	}

	public void setRoot(Node<E> root) {
		this.root = root;
	}

	@Override
	public boolean isEmpty() {
		if (root == null) { 		// Return true only if the stack is empty.
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int size() {		// Bag size.
		return size;
	}

	@Override
	public boolean contains(E element) { 	// Search method from BSTs2018 notes.

		int direction = 0;
		Node<E> curr = root;

		for (;;) {
			if (curr == null) {		// If empty, Bag doesn't contain anything.
				return false;
			}
			direction = element.compareTo (curr.element.getElement());

			if (direction == 0) {
				return true;
			}
			else if (direction < 0) {
				curr = curr.left;
			}
			else {
				curr = curr.right;
			}
			//return false;
		}
	}

	/* Return true if this list and that list are the same 
	length and each element corresponds to the other*/
	
	@Override
	public boolean equals(Bag<E> that) { 
		if (this.size != that.size()) {
			return false;
		}
		
		else {
			Iterator <E> thisIterator = this.iterator();
			Iterator <E> thatIterator = that.iterator();

			while(thisIterator.hasNext() && thatIterator.hasNext()) {
				if (thisIterator.next().equals(thatIterator.next())) {
					return true;
				}
			}
			return false;
		}
	}


	@Override
	public void clear() {
		root = null;

	}

	@Override
	public void add(E element) {

		CountedElement<E> elem = new CountedElement<E>(element);
		int direction = 0;
		Node<E> parent = null;
		Node<E> curr = root;
		size++;

		for (;;) {
			if (curr == null) {
				Node<E> ins = new Node<E> (elem);
				if (root == null) {
					root = ins;
				}
				else if (direction <0) {
					parent.left = ins;
				}
				else {
					parent.right = ins;
				}
				return;
			}
			direction = elem.compareTo(curr.element);
			if (direction == 0) {
				return;
			}
			parent = curr;
			if (direction < 0) {
				curr = curr.left;
			}
			else {
				curr = curr.right;
			}
			//size++;
		}
	}


	@Override
	public void remove(E element) {

		CountedElement<E> elem = new CountedElement<E>(element);
		int direction = 0;
		Node<E> parent = null;
		Node<E> curr = root;
		size--;

		for (;;) {
			if (curr == null) {
				return;
			}
			direction = elem.compareTo(curr.element);
			if (direction == 0) {
				Node<E> del = curr.deleteTopmost();
				if (curr == root) {
					root = del;
				}
				else if (curr == parent.left) {
					parent.left = del;
				}
				else {
					parent.right = del;
				}
				return;
			}
			parent = curr;
			if (direction < 0) {
				curr = parent.left;
			}
			else {
				curr = parent.right;
			}
			//size--;
		}
	}


	@Override
	public Iterator<E> iterator() {
		return new InOrderIterator ();
	}

	private class InOrderIterator implements Iterator<E> {
		private Stack<Node<E>> track;

		private InOrderIterator() {
			track = new LinkedStack<Node<E>>();
			for (Node<E> curr = root; curr!=null; curr=curr.left) {
				track.push(curr);
			}
		}

		@Override
		public boolean hasNext() {
			return (!track.empty());
		}

		@Override
		public E next() {
			if (track.empty()) {
				throw new NoSuchElementException();
			}
			Node<E> place = track.pop();

			for (Node<E> curr = place.right; curr !=null; curr = curr.left) {
				track.push(curr);
			}
			return place.element.getElement();
		}
	}

}