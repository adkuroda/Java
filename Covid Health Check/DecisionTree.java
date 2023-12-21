/**
 * Generic class that will help to make decision. 
 * @author Adilet Kuroda
 * @param <T> generic placeholder. 
 */
public class DecisionTree<T> extends BinaryTree<T> implements DecisionTreeInterface<T> {
	/**
	 * Keeps track of where we are in given tree. 
	 */
	private BinaryNode<T> currentNode;
	/**
	 * Default constructor initialize all attributes to null. 
	 */
	public DecisionTree() {
		super();
		currentNode = getRootNode();
	}
	/**
	 * Constructor that initialize the currentNode's value to given Data.
	 * @param data value for currentNode. 
	 */
	public DecisionTree(T data) {
		super(data);
		currentNode =getRootNode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAnswer() {
		if (currentNode == null){
			throw new EmptyTreeException("This tree is empty");
		}
		return currentNode.isLeaf();  
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void moveToNo() {
		if (currentNode != null && currentNode.hasLeftChild()) {
			currentNode = currentNode.getLeftChild();
		}
		
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void moveToYes() {
		if (currentNode != null && currentNode.hasRightChild()) {
			currentNode = currentNode.getRightChild();
		}
		
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void resetCurrentNode() {
		currentNode = this.getRootNode();
		
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BinaryNode<T> getCurrentNode() {
		return this.currentNode;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getCurrentData() {
		return currentNode.getData();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setResponses(T responseForNo, T responseForYes) {
		currentNode.setLeftChild(new BinaryNode<T>(responseForNo));
		currentNode.setRightChild(new BinaryNode<T> (responseForYes));
		
	}

}
