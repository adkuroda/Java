/**
 * This class creates a tracking system platform for the 
 * people came to get tested for Covid-19. It works on the basis
 * of first come first serve. 
 * @author Adilet Kuroda
 * @param <E> set of people that will come for testing. 
 */
public class CircularLine<E> implements CircularLineInterface<E> {
	/**
	 * Keeps track of the person who will get tested next. 
	 */
	protected int start;
	/**
	 * Keeps track of the location of the person at the end of 
	 * the line. 
	 */
	protected int end;
	/**
	 * Keeps track of how many people a given line can hold. 
	 */
	protected int capacity;
	/**
	 * Keeps track of how many people are in the line. 
	 */
	protected int size;
	/**
	 * Actual line. 
	 */
	protected E[] line;
	/**
	 * Creates a new circular line that can hold 49 people. 
	 */
	public CircularLine() {
		this(50);
	}
	/**
	 * Creates a new circular line based the provided input. It 
	 * will hold one number less than a given input. 
	 * @param capacity is the number of people a line should hold. 
	 */
	@SuppressWarnings("unchecked")
	public CircularLine(int capacity) {
		this.line = (E[]) (new Object[capacity]);
		this.capacity = capacity - 1;
		this.size = 0;
		this.start = 0;
		this.end = this.capacity;
	}
	/**
	 * This doubles the size of the line if line is full and there are 
	 * more people who need to be tested. 
	 */

	public void doubleCapacity() {
		if (isFull()) {
			@SuppressWarnings("unchecked")
			E[] tempLine = (E[]) (new Object[(capacity + 1) * 2]);
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
	 * Adjusts the end of the line.
	 * @return the end which is last person in line.
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * Adjusts the front of the line. 
	 * @return the start; it is a first person in line.
	 */
	public int getStart() {
		return start;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override 
	public void insert(E newData) {
		if (isFull()) {
			doubleCapacity();
		}

		if (isEmpty()) {
			end = start;
			line[end] = newData;
			size++;
		} else {
			if (end >= capacity) {
				end = 0;
				line[end] = newData;
				size++;
			} else {
				line[++end] = newData;
				size++;
			}

		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E remove() {
		if (isEmpty() == true) {
			throw new NoElementException();
		}
		E element = line[start++];
		if (start > capacity) {
			start = 0;
		}
		size--;
		return element;
			
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeAll() {
		if (isEmpty()) {
			throw new NoElementException();
		}
		for (int i = 0; i < size; i++) {
			line[i] = null;
		}
		this.end = capacity;
		this.start = 0;
		this.size = 0;

	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getFront() {
		if (size == 0) {
			throw new NoElementException();
		} else {
			return line[start];
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getBack() {
		if (size == 0) {
			throw new NoElementException();

		}
		return line[end];
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCapacity() {
		return this.capacity;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return this.size;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isFull() {
		if (capacity <= size) {
			return true;
		} else {
			return false;
		}

	}
	/**
	 * This creates String representation of the line from 
	 * the front to the end. 
	 * @return String representation of on object 
	 */
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder("[");
		if (isEmpty() == false) {
			int temp = start;
			for (int i = 0; i < size; i++) {
				if (temp > capacity) {
					temp = 0;
					output.append(line[temp++]);
				}
				else 
					output.append(line[temp++]);

				if (i < size - 1) {
					output.append(",");
				}
			}
		}
		output.append("]");
		return output.toString();
	}

}
