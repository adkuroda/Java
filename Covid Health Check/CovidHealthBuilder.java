import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * This class combines all the class to implement Health check for 
 * COVID 19.  
 */
public class CovidHealthBuilder{
	/**
	 * Decision Tree Data Structure. 
	 */
	private DecisionTreeInterface<String> healthTree;
	/**
	 * Used for tokenize the input.
	 */
	private Scanner textInput;
	/**
	 * Used to get user input.
	 */
	private Scanner kb = new Scanner(System.in);
	
	/** This ininializes healthTree using buildTree method. 
	 * @param fileName to construct a healthTree. 
	 */
	public CovidHealthBuilder(String fileName){
		ArrayList<String> content = readData(fileName);
		healthTree =  new DecisionTree<String>();
		BinaryNode<String> node = new BinaryNode<>(content.get(0));
		healthTree.setRootNode(buildTree(content, node, 0));
	}
	/**
	 * Determines and retuns the healhTree field. 
	 * @return a healthTree.  
	 */
	public DecisionTreeInterface<String> getHealthTree() {
		return healthTree;
	}

	/** Takes the file content and creates a list that is binary Tree from the values of the 
	 * file. 
	 * @param fileName is a file that contains the data. 
	 * @return list mimics the binary tree. 
	 */
	public ArrayList<String> readData(String fileName){
		ArrayList<String> content = new ArrayList<>();
		try {
			File file = new File(fileName);
			textInput = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.err.println(e); 
		}
		while (textInput.hasNextLine()) {
			String[] temp = textInput.nextLine().split(",");
			for (int i = 0; i <temp.length; i++) {
				content.add(temp[i].trim());
			}
		}
		return content;	
	}
	/** Bilds a linked binary tree utilzing the list created by readData method. Recursively 
	 * initializes the tree by utilzing values of the array tree. 
	 * @param content is array of binary tree. 
	 * @param root is the root node of the tree. 
	 * @param i is the index in array binary Tree. 
	 * @return a root of the node representing the whole tree. 
	 */
	public BinaryNode<String> buildTree(ArrayList<String> content, BinaryNode<String> root, int i){
		if (i >= content.size()) {
			return null;
		}
		if (content.get(i).trim().equals("null")) {
			root = null;
		}
		else {
			root = new BinaryNode<>(content.get(i));
			root.setLeftChild(buildTree(content, root, 2*i +1));
			root.setRightChild(buildTree(content, root, 2*i +2));
		}
		return root;
	}
	/**
	 * It moves through healthTree based on how user answers given question (yes or no)
	 * and displays what actions should user take. If user has additional 
	 * suggestion, it will utilize the learn method to get user input. 
	 */
	public void decide() {
		healthTree.resetCurrentNode();
		while(!healthTree.isAnswer()){
			System.out.println(healthTree.getCurrentData());
			String input = kb.next();
			if(input.trim().equalsIgnoreCase("yes")) {
				healthTree.moveToYes();
			}
			if (input.trim().equalsIgnoreCase("no")) {
				healthTree.moveToNo();
			}
		}
		System.out.println(healthTree.getCurrentData());
		System.out.println();
		System.out.println("Satisfied by my intelligence?");
		System.out.println();
		String input = kb.next();
		if (input.trim().equalsIgnoreCase("no")) {
			learn();
		}
	}
	/**
	 * This method takes the user input and utilzing updateTree method, it updates 
	 * given healthTree according to user suggestion. 
	 */
	public void learn() {
		System.out.println("What should be the answer?");
		//Scanner kb1  = new Scanner(System.in);
		String input = kb.nextLine();
		input = kb.nextLine();
		System.out.println("Give me a question whose answer is yes for " + input + " but no for "+ healthTree.getCurrentData());
		String question = kb.nextLine();

		updateTree(question, input, healthTree.getCurrentData());
	}
	/**
	 * This update the healthTree based on user suggestion. 
	 * @param question is user suggested question. 
	 * @param noAnswer user suggested answer for the question.
	 * @param yesAnswer desicion tree suggestion based on user input. 
	 */
	public void updateTree(String question, String noAnswer, String yesAnswer) {
		healthTree.getCurrentNode().setData(question);
		BinaryNode<String> no = new BinaryNode<>(noAnswer);
		BinaryNode<String> yes = new BinaryNode<>(yesAnswer);
		healthTree.getCurrentNode().setLeftChild(no);
		healthTree.getCurrentNode().setRightChild(no);

	}

}
