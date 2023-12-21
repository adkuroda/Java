/**
 * Runtime Exception thrown when there is no element in given container. 
 * @author Adilet Kuroda 
 *
 */


public class NoElementException extends RuntimeException {
	/**
	 * Creates NoElementException that produces messages of "No element to process". 
	 */
	public NoElementException() {
		super("No element to process");
	}

}
