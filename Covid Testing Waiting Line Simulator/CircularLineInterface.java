/**
 * This is the main interface for circular line data structure. This interface 
 * is implemented by two circular data structures (CircularLine and 
 * PriorityCircularLine) that will be used to create a waiting line 
 * for Covid-19 testing. 
 * @author Adilet Kuroda
 * @param <E> Data type of the element in data structure.  
 */
public interface CircularLineInterface<E> {
	/**
	 * Insert person that arrives at the end of the waiting line. 
	 * @param newData is a element to be inserted. 
	 */
	public void insert(E newData);
	/**
	 * Removes the person in front of the line that is called for testing or 
	 * leaves the line. Alerts user if there is nobody in the 
	 * line. 
	 * @return an element that is removed. 
	 * @throws NoElementException when there is nobody on the line. 
	 */
	public E remove();
	/**
	 * Removes everyone from the waiting line. Alerts
	 * if nobody is waiting in the given line. 
	 * @throws NoElementException when there is nobody on the line. 
	 */
	public void removeAll();
	/**
	 * Identifies the person in front of the line to receive testing. 
	 * @return the first person in the beginning of the line. 
	 * @throws NoElementException when there is nobody on the line. 
	 */
	public E getFront();
	/**
	 * Identifies the person that is at the end of the line.
	 * @return person that is at the end of the line.  
	 * @throws NoElementException when there is nobody on the line. 
	 */
	public E getBack();
	/**
	 * This determines the maximum number of people that the given line can hold. 
	 * @return how many persons can get in the line. 
	 */
	public int getCapacity();
	/**
	 * Determines how many people are waiting in the line to be tested. 
	 * @return how many people waiting in the line. 
	 */
	public int size();
	/**
	 * Determines if the given line is empty or not. 
	 * @return true if empty and false if it is not empty. 
	 */
	public boolean isEmpty();
	/**
	 * Determines if the given line is full or not.  
	 * @return true if the line is full and false if it is not full. 
	 */
	public boolean isFull();
}
