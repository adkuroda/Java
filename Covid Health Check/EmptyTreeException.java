/**
 * This exception is thrown when empty tree is accessed. 
 * @author Adilet Kuroda
 */
public class EmptyTreeException extends RuntimeException {
	/**
	 * Constructor that taylors the message based on given circumstances. 
	 * @param msg message displayed for the user. 
	 */
	public EmptyTreeException(String msg) {
		super(msg);
	}
}
