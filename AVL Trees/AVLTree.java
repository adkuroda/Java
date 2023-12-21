
/**
 * AVL Tree class. Balances height is lg(N). N is number
 * of Nodes. 
 */
class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    /**
     * Default Constructor
     */
    public AVLTree(){
        super();
        root = new AVLNode<>();
    }
    /**
     * Constructor. 
     * @param rootEntry Value to initialize the tree. 
     */
    public AVLTree(T rootEntry){
        super();
        root = new AVLNode<T>(rootEntry);
    }

    /**
     * @param entry Value to be removed 
     * @throws EmptyTreeException() parameter is null. 
     * @return null if entry is not in a tree, otherwise the item removed.  
     */
    public T remove(T entry){
        if (getRootNode() == null){
            throw new EmptyTreeException();
        }
        else{
            if (!contains(entry)){
                return null;
            }
            else{
                T ret = getEntry(entry);
                root = (AVLNode<T>)remove(entry,(AVLNode<T>)root);
                return ret;
            }
        }
    }
    /**
     * Helper method to remove. It recursively find the value that is being removed. 
     * Note: I have used algorithm that is implemented in method removeEntry()
     * in BinarySearchTree.java thus there might be similarities. 
     * @param entry value that is being removed 
     * @param node root node of a tree. 
     * @return root node of a tree after removed and balanced. 
     */
    private AVLNode<T> remove(T entry, AVLNode<T> node){
        int diff  = entry.compareTo(node.getData());
        if (diff > 0) {
            node.setRightChild(remove(entry, (AVLNode<T>)node.getRightChild()));
        }
        else if(diff < 0){
            node.setLeftChild(remove(entry, (AVLNode<T>)node.getLeftChild()));
        }
        else{
            node = removeRoot(node);
        } 
        return checkRotate((AVLNode<T>)node);
    } 

    /** Second helper method that actually replaces the node value with 
     * predessessor and removes the predessessor with helper method. 
     * Note: I have used algorithm that is implemented in method removeFromRoot()
     * in BinarySearchTree.java thus there might be similarities.
     * @param node root node of a subtree. 
     * @return root of a subtree after it is removed. 
     */
    private AVLNode<T> removeRoot(AVLNode<T> node){
        if (node.isLeaf()){
            return null;
        }
        else if(node.hasRightChild() && !node.hasLeftChild()){
            node = (AVLNode<T>) node.getRightChild();
        }
        else if(!node.hasRightChild() && node.hasLeftChild()){
            node = (AVLNode<T>) node.getLeftChild();
        }
        else{
            T data = ((AVLNode<T>)getPredecessor((AVLNode<T>)node.getLeftChild())).getData();
            node.setData(data);
            node.setLeftChild(removePrecessosor((AVLNode<T>)node.getLeftChild()));
        }     
        return node;
    }
    /**
     * Helper method that finds the predecessor of a node. 
     * Note: I have used algorithm that is implemented in method findLargest()
     * in BinarySearchTree.java thus there might be similarities.
     * @param node a node from a tree. 
     * @return node that is predecessor of given parameter. 
     */
    private AVLNode<T> getPredecessor(AVLNode<T> node){
        AVLNode<T> current = node;
        while(current.getRightChild() != null){
            current = (AVLNode<T>)current.getRightChild();
        }
        return current;
    } 
   /**
    * Finds and removes the predecessor. 
    * Note: I have used algorithm that is implemented in method removeLargest()
    * in BinarySearchTree.java thus there might be similarities.
    * @param node subtree.
    * @return returns root node of a subtree. 
    */
    private AVLNode<T> removePrecessosor(AVLNode<T> node){
        if (node.getRightChild() == null){
            if (node.getLeftChild() != null){
                return (AVLNode<T>)node.getLeftChild();
            }
            else{
                return null;
            }
        }
        node.setRightChild(removePrecessosor((AVLNode<T>)node.getRightChild()));
        return checkRotate(node);
    } 

    /**
     * Adds entry to a Tree and balances using helper methods. 
     * @return null if it is added. Otherwise returns the object. 
     */
    public T add(T newEntry){
        if(newEntry == null){
            throw new NullPointerException("Not valid input: input type \"null\"");
        }
        else if(this.root.getData() == null){
            root = new AVLNode<T>(newEntry);
            return null;
        }
        else{
            if(contains(newEntry)){
                return getEntry(newEntry);
            }
            else{
                root = add(newEntry, (AVLNode<T>)root);
                return null;
            }
        }
    }
    /**
     * Helper method to add en entry to a tree. 
     * @param entry value that is being added 
     * @param node root node of a tree. 
     * @return root node of a tree that is balanced 
     */
    private AVLNode<T> add(T entry, AVLNode<T> node){
        if (node == null){
            return new AVLNode<T>(entry);
        }
        else{
            int diff = entry.compareTo(node.getData());
            if (diff < 0){
                node.setLeftChild(add(entry,(AVLNode<T>)node.getLeftChild())); 
            }
            else{
                node.setRightChild(add(entry,(AVLNode<T>) node.getRightChild()));
            }
            return checkRotate(node);
        } 
    }
    /**I am referencing Dr. Mengistu's slides (Balanced Search Tree) to implement this method. 
     * check the node and rotates as it needed */
    /**
     * Checks and balances the given node using additional helper methods. 
     * Note: I have read the pseudocode from Dr. Mengistu's slides (Balanced Search Tree) 
     * before implementing this method thus there might be some similarities. 
     * @param node root node of a tree or subtree. 
     * @return root node of a tree. 
     */
    private AVLNode<T> checkRotate(AVLNode<T> node){
        if (node == null){
            return node;
        }
        if (getHeight(node.getRightChild()) > 1+ getHeight(node.getLeftChild())){
            if (getHeight(node.getRightChild().getRightChild()) >= getHeight(node.getRightChild().getLeftChild())){
                return rotateToLeft(node);
            }
            else{
                AVLNode<T> temp = (AVLNode<T>)node.getRightChild();
                temp = rotateToRight(temp);
                node.setRightChild(temp);
                return rotateToLeft(node);
            }
        }
        else if (getHeight(node.getRightChild()) +1 < getHeight(node.getLeftChild())){
            if (getHeight(node.getLeftChild().getLeftChild()) >= getHeight(node.getLeftChild().getRightChild())){
                return rotateToRight(node);
            }
            else{
                AVLNode<T> temp = (AVLNode<T>)node.getLeftChild();
                temp = rotateToLeft(temp);
                node.setLeftChild(temp);
                return rotateToRight(node);
            }
        }
        else{
            return node;
        }
    }
    /**
     * Helper method finds the height of a given node. 
     * @param node root node of a tree or subtree
     * @return height of a tree or subtree
     */
    private int getHeight(BinaryNode<T> node){
        if (node == null){
            return -1;
        }
        return node.getHeight();
    } 
  
    /**
     * Rotates the node to right using left child. 
     *  Note: I have read the pseudocode provided in course textbook, p 710 
     * before implementing this method thus there might be some similarities. 
     * @param node a node of a tree or subtree. 
     * @return root node of a subtree or tree after it is rotated. 
     */
    private AVLNode<T> rotateToRight(AVLNode<T> node) {
        AVLNode<T> ret = (AVLNode<T>)node.getLeftChild();
        AVLNode<T> temp = new AVLNode<>(node.getData());
        temp.setRightChild(node.getRightChild());
        if(ret.hasRightChild()){
            temp.setLeftChild(ret.getRightChild());
        }
        ret.setRightChild(temp);
        return ret;
    }
    
    /**
     * Rotates the node to left using right child. 
     *  Note: I have read the pseudocode provided in course textbook, p 711
     * before implementing this method thus there might be some similarities. 
     * @param node a node of a tree or subtree. 
     * @return root node of a subtree or tree after it is rotated. 
     */
    private AVLNode<T> rotateToLeft(AVLNode<T> node){
        AVLNode<T> ret = (AVLNode<T>)node.getRightChild();
        AVLNode<T> temp = new AVLNode<>(node.getData());
        temp.setLeftChild(node.getLeftChild());
        if(ret.hasLeftChild()){
            temp.setRightChild(ret.getLeftChild());
        }
        ret.setLeftChild(temp);
        return ret;
    }
    /**
     * Testing purpose only. 
     * @param node is a node. 
     */
    // private void print(AVLNode <T> node){
    //     if (node == null){
    //         return;
    //     }
    //     Print((AVLNode<T>)node.getLeftChild());
    //     System.out.print(node.getData());
    //     System.out.print(" Instance of "+ (node instanceof AVLNode));
    //     System.out.println(" Height: "+ node.getHeight());
    //     Print((AVLNode<T>)node.getRightChild());
    // }
}
    