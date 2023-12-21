/**
 * This Interface represents overall functionality of 
 * decision making tree that during the COVID-19 health check.
 * @author Adilet Kuroda
 * @param <T> type of data. 
 */
public interface DecisionTreeInterface<T> extends BinaryTreeInterface<T> {
	/**
	 * Determines if the decision is at the end of the tree or 
	 * not. If it is, then it returns true. Otherwise false. 
	 * @return true or false. 
	 */
	public boolean isAnswer();
	
	/**
	 * Shifts the given node to its left child. Given node 
	 * should not be null;
	 */
	public void moveToNo();
	/**
	 * Shifts the given node to its right child. Given node 
	 * should not be null;
	 */
	public void moveToYes();
	
	/**
	 * Shifts the given node to the root node. 
	 */
	public void resetCurrentNode();
	
	/**
	 * Determines the reference to the current node. 
	 * @return a reference to given node. 
	 */
	public BinaryNode<T> getCurrentNode();
	
	/**
	 * Determines the data in a node and returns it. 
	 * @return data in a node. 
	 */
	public T getCurrentData();
	
	/**
	 * Resets the data of both children of a node. 
	 * @param responseForNo new data for left child. 
	 * @param responseForYes new data for right child. 
	 */
	public void setResponses(T responseForNo, T responseForYes);

}
