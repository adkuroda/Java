/**
 * AVLNode class. 
 */
class AVLNode<T> extends BinaryNode<T> {
    private int height;
    /**
     * Default constructor. 
     */
    public AVLNode(){
        super();
        height = -1;
    }
    /**
     * Constructor
     * @param data value of node. 
     */
    public AVLNode(T data){
        super(data);
        this.height = 0;
    }
    /**
     * Constructor 
     * @param data value of a node. 
     * @param leftNode left child of a node 
     * @param rightNode right child of a node 
     */
    public AVLNode(T data, AVLNode<T> leftNode, AVLNode<T> rightNode){
        super(data, leftNode, rightNode);
        this.height = Math.max(leftNode.getHeight(), rightNode.getHeight()) + 1;

    }
    /**
     * Sets the left child of a node and update the height of node. 
     * @param node that is being attached as left child. 
     */
    public void  setLeftChild(AVLNode<T> node){
        super.setLeftChild(node);
        height = super.getHeight();
      

    }
    /**
     * Sets the right child of a node and update the height of node. 
     * @param node that is being attached as right child. 
     */
    public void setRightChild(AVLNode<T> node){
        super.setRightChild(node);
        height = super.getHeight();

    }
    /**
     * Ruturns a height of given node. 
     * @return a height of tree. 
     */
    public int getHeight(){
        return height;
    }

}