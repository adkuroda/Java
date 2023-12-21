/**
 * This class is Generic BinaryTree. 
 * @author Adilet Kuroda
 * @param <T> generic data type.
 */
public class BinaryTree<T> implements BinaryTreeInterface<T> {
	/**
	 * This signifies root Node of a tree. 
	 */
	protected BinaryNode<T> root; // clarify for sure...
	
	/**
	 * Default constructor initializes the root node to null. 
	 */
	public BinaryTree() {
		this.root = null;
	}
	/**
	 * Constructor sets the roots nodes value to given parameter. 
	 * @param data is value for root node. 
	 */
	public BinaryTree(T data){
		this.root = new BinaryNode<>(data);
	}
	/**
	 * Constructor sets the value of root node and identifies it's left 
	 * and right node. 
	 * @param data value for the root node. 
	 * @param leftTree left node of the root. 
	 * @param rightTree right node of the root. 
	 */
	public BinaryTree(T data, BinaryTree<T> leftTree, BinaryTree<T> rightTree){
		this.root = new BinaryNode<>(data);
		if (leftTree != null){
			root.setLeftChild(leftTree.getRootNode());
		}
		if (rightTree != null){
			root.setRightChild(rightTree.getRootNode());
		}
	}
	/**
	 * Sets the given left and right tree to the root Node and initializes the root Node's value. 
	 * @param data value to initialize the root node data field. 
	 * @param leftTree attached to the left node of the root. 
	 * @param rightTree attached to the right node of the root. 
	 */
	public void setTree(T data, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
		this.root = new BinaryNode<T>(data);
		if (leftTree != null) {
			this.root.setLeftChild(leftTree.getRootNode());
			leftTree.setRootNode(null);
		}
		if (rightTree != null) {
			this.root.setRightChild(rightTree.getRootNode());
			rightTree.setRootNode(null);
		}
	}
	/**
	 * Utilizing helper method, this method prints out
	 * given binary tree depth inorder form. 
	 */
	public void inorderTraversal() {
		if (this.root == null){
			throw new EmptyTreeException("Can not traverse empty tree.");
		}
		System.out.print("\"");
		inorderTraversal(this.root);
		System.out.print("\b\"");
		System.out.println();
	}
	/** Helper method to print in orderTraversal recursivly. 
	 * Below code is similar to what we learned in class(Prof. Russell:  Tree Traversals)
	 * @param  node is root node of a given tree or subtree. 
	 */
	private void inorderTraversal(BinaryNode<T> node) {
		if (node == null) {
			return;
		}
		inorderTraversal(node.getLeftChild());
		System.out.print(node.getData());
		System.out.print(" ");
		inorderTraversal(node.getRightChild());	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getRootData() {
		if(this.getRootNode() == null) {
			throw new EmptyTreeException("Tree is empty");
		}
		return root.getData();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public BinaryNode<T> getRootNode() {
		return this.root;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRootNode(BinaryNode<T> rootNode) {
		this.root = rootNode;
		
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHeight() {
		if (this.root == null) {
			return 0;
		}
		return root.getHeight();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getNumberOfNodes() {
		if (this.root == null) {
			return 0;
		}
		return this.root.getNumberOfNodes();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return (!(root.hasLeftChild() || root.hasRightChild()));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		this.root = null;		
	}

}
