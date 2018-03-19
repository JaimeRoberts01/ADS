public class CountedElement<E extends Comparable<E>> implements Comparable<CountedElement<E>> {
	private E element;
	private int count;

	public CountedElement(E e, int count){
		/*constructor - to complete*/
		this.element = e;
		this.count = count;
	}

	public CountedElement(E e){
		/*constructor - to complete*/
		this.element = e;
		this.count = 1; // was 1
	}
	
	/*add getters and setters*/
	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/*add toString() method*/
	public String toString() {
		String format = "(" + this.element + "," + this.count + "), ";
		return format;
	}

	public int compareTo(CountedElement<E> sC1) {

		/*to complete*/
		E thisElement;
		E otherElement;
		int thisCount;
		int otherCount;

		thisElement = this.getElement();
		otherElement = sC1.getElement();
		thisCount = this.getCount();
		otherCount = sC1.getCount();

		// If thisElement is greater than the other element...
		if (thisElement.compareTo(otherElement)>0) {
			return 1;
		}
		// If thisElement = otherElement and has the same count...
		if ((thisElement.compareTo(otherElement)==0) && (thisCount==otherCount)) {
			return 0;
		}
		// Otherwise...
		else {
			return -1;
		}
		
		//return thisElement.compareTo(otherElement);
		
	}
}