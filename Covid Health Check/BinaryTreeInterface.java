
/**
 * This Interface represent overall functionality of 
 * generic binary tree. 
 * @author Adilet Kuroda
 * @param <T> type of data.
 *
 */
public interface BinaryTreeInterface<T> {
	/**
	 * Determines data stored at root Node and returns it. 
	 * @return data stored at root node. 
	 */
	public T getRootData();
	/**
	 * Determines and returns the reference to the root node. 
	 * @return reference to a root node. 
	 */
	public BinaryNode<T> getRootNode();
	/**
	 * Setter for the root node. 
	 * @param rootNode a reference to a root node. 
	 */
	public void setRootNode(BinaryNode<T> rootNode);  // Clarify root node//
	/**
	 * Determines and returns height of the tree. 
	 * @return height of the tree. 
	 */
	public int getHeight();
	/**
	 * Determines and returns number of nodes in given tree. 
	 * @return number of nodes in a tree. 
	 */
	public int getNumberOfNodes();
	/**
	 * Determines if the given tree is empty or not. 
	 * @return true if empty, otherwise false. 
	 */
	public boolean isEmpty();
	/**
	 * Clear or emties the given tree. 
	 */
	public void clear();
}
