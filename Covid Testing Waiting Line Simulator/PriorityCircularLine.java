/**
 * This class creates a platform to track the 
 * people who came to get tested for Covid-19. It can prioritize 
 * people based on defined criteria that groups them into categories.  
 * @author Adilet Kuroda
 * @param <E> set of people that will come for testing. 
 */
public class PriorityCircularLine<E extends Comparable<E>>  extends CircularLine<E> {
	/**
	 * This will create a new line with the capacity of 49 people. 
	 */
	@SuppressWarnings("unchecked")
	public PriorityCircularLine() {
		super();
		super.line = (E[])(new Comparable[capacity]);
	}
	/**
	 * Creates a new circular line based on the provided input. It 
	 * will hold one number less than the provided input. 
	 * @param capacity is number of person line should hold. 
	 */
	@SuppressWarnings("unchecked")
	public PriorityCircularLine(int capacity) {
		super(capacity);
		super.line = (E[])(new Comparable[capacity]);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override 
	public void doubleCapacity() {
		if (isFull()) {
			@SuppressWarnings("unchecked")
			E[] tempLine = (E[]) (new Comparable[(capacity + 1) * 2]);
			int temp = this.start;
			for (int i = 0; i < size; i++) {
				if (temp > this.capacity) {
					temp = 0;
				}
				tempLine[i] = line[temp];
				line[temp++] = null;
			}
			this.capacity = (capacity + 1) * 2 - 1;
			start = 0;
			end = size-1;
			this.line = tempLine;
			tempLine = null;
		}
	}
	/**
	 * This prioritizes individuals based on different criteria and
	 * advances individuals to the front of the line based on that criteria. 
	 * If multiple individuals meet the same criteria, then it works on a first 
	 * come, first served basis. 
	 * @param newData is a element to be inserted. 
	 */
	@Override
	public void insert(E newData) {
		super.insert(newData);{
			int temp = end-1;
			boolean status = true;
			for(int i = 0; i< size-1; i++) {
				if (temp >= 0 && status) {
					if (compare(temp+1, temp)) {
						break;
					};
					temp--;
				}
				else if (temp ==-1) {
					if (compare(0, capacity)) {
						break;
					};
					temp =capacity-1;
					status = false;
				}
				else {
					if (compare(temp+1, temp)) {
						break;
					};
					temp --;
				}	
			}	
		}
	}
	/**
	 * This is a helper method that swaps two elements. 
	 * @param index1 index of an element on given collection. 
	 * @param index2 index of element on given collection. 
	 */
	void swap(int index1, int index2) {
		E value = line[index1];
		line[index1] = line[index2];
		line[index2] = value;
	}
	/**
	 * This is a helper method that compares two elements based on object's
	 * compareTo method definition and swaps them using the swap method if one 
	 * is less than the other. The smaller ones will come first.  
	 * @param index1 index of an element on given collection. 
	 * @param index2 index of element on given collection. 
	 * @return false if two elements compared and swapped and true if it did not swapped. 
	 */
	private boolean compare(int index1, int index2) {
		if(line[index1].compareTo(line[index2]) < 0) {
			swap(index1, index2);
			return false;
		}
		else {
			return true;
		}
	}
}

