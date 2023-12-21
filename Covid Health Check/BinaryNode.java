/**
 * This class represents overall structure of a Node. 
 * @author Adilet Kuroda
 * @param <T> that node will take. 
 */
public class BinaryNode<T> {
	/**
	 * Represents the value in a node. 
	 */
	private T data;
	/**
	 * References to the left side of given Node. 
	 */
	private BinaryNode<T> leftChild;
	/**
	 * References to the right side of given Node. 
	 */
	private BinaryNode<T> rightChild;
	/**
	 * Default constructor, initializes everything to null. 
	 */
	public BinaryNode() {
		this(null, null, null);
	}
	/**
	 * Constructor initialized the data only. 
	 * @param data value inside the node. 
	 */
	public BinaryNode(T data) {
		this(data, null, null);
	}
	/**
	 * Constructor initialized all fields to provided parameters. 
	 * @param data value inside the node.
	 * @param leftNode left child of a node. 
	 * @param rightNode right child of a node. 
	 */
	public BinaryNode(T data, BinaryNode<T> leftNode, BinaryNode<T> rightNode){
    	this.setData(data);
		this.setLeftChild(leftNode);
		this.setRightChild(rightNode);
    }
	/**
	 * Determines and returns the data in given node. 
	 * @return the data inside the node. 
	 */
	public T getData() {
		return data;
	}
	/**
	 * Alters the data in given Node. 
	 * @param data that will replace or set the value of node. 
	 */
	public void setData(T data) {
		this.data = data;
	}
	/**
	 * Determines the left child of a node and returns it. 
	 * @return the leftChild is a node of left of given Node. 
	 */
	public BinaryNode<T> getLeftChild() {
		return leftChild;
	}
	/**
	 * Alters the left child given Node. 
	 * @param leftChild Node that replaces left child. 
	 */
	public void setLeftChild(BinaryNode<T> leftChild) {
		this.leftChild = leftChild;
	}
	/**
	 *  Determines the right child of a node and returns it. 
	 * @return the rightChild
	 */
	public BinaryNode<T> getRightChild() {
		return rightChild;
	}
	/**
	 * Alters the right child given Node.
	 * @param rightChild Node that replaces right child. 
	 */
	public void setRightChild(BinaryNode<T> rightChild) {
		this.rightChild = rightChild;
	}
	/**
	 * Determines if the given Node has left child and return 
	 * true. Otherwise returns false. 
	 * @return true or false. 
	 */
	public boolean hasLeftChild() {
		return getLeftChild() != null;
	}
	/**
	 * Determines if the given Node has right child and return 
	 * true. Otherwise returns false. 
	 * @return true or false. 
	 */
	public boolean hasRightChild() {
		return getRightChild() != null;
	}
	/**
	 * Determines if the given node is a leaf or not. If it is,
	 * return true, otherwise false. 
	 * @return true or false. 
	 */
	public boolean isLeaf() {
		return !(hasRightChild() || hasLeftChild());
	}
	/**
	 * Determines the height of the tree rooted at the node. 
	 * @return height of tree. 
	 */
	public int getHeight() {
		if (isLeaf()){
			return 0;
		}
		if (leftChild == null && hasRightChild()){
			return 1;
		}
		if (rightChild == null && hasLeftChild()){
			return 1;
		}
		
		int left = leftChild.getHeight();
		int right = rightChild.getHeight();
		return 1 + maxValue(left, right);
		
	}
	/**
	 * Helper method determine max value of two integers. 
	 * @param a first value. 
	 * @param b second value.
	 * @return maximum value between a and b. 
	 */
	private int maxValue(int a, int b) {
		return a>b ? a:b;
	}
	/**
	 * Determines number of nodes in given tree. 
	 * @return number of nodes in given tree. 
	 */
	public int getNumberOfNodes() {
		if (isLeaf()){
			return 1;
		}
		if (leftChild == null && hasRightChild()){
			return 2;
		}
		if (rightChild == null && hasLeftChild()){
			return 2;
		}
		int left = leftChild.getNumberOfNodes();
		int right = rightChild.getNumberOfNodes();
		return 1 + left + right;
		
	}	
	/**
	 * Copies the subtree rooted at a node. 
	 * @return root copied subtree. 
	 */
	public BinaryNode<T> copy(){
		BinaryNode<T> rootNode = new BinaryNode<>(this.getData());
		if (isLeaf())
			return rootNode;
		
		if (!hasLeftChild()) {
			rootNode.setRightChild(rightChild.copy());
		}
		else if (!hasRightChild()) {
			rootNode.setLeftChild(leftChild.copy());
		}
		else {
			rootNode.setRightChild(rightChild.copy());
			rootNode.setLeftChild(leftChild.copy());
		}
		return rootNode;	
	}

}
