/**
 * Car class represents single train car. 
 * @author Adilet Kuroda 
 *
 */
class Car {
	/**
	 * Name of the train. 
	 */
	private String name;
	/**
	 * Reference to next train.
	 */
	private Car next;
	/**
	 * reference to previous train. 
	 */
	private Car previous;
	
	/**
	 * Create a train car with the name. Will not have 
	 * reference to next and previous train. 
	 * @param name name of the given train. 
	 */
	public Car(String name) {
		this.name = name;	
	}
	
	/**
	 * This determines what is next train wagon (getter).
	 * @return train car after given train.
	 */
	public Car getNext() {
		return this.next;
	}
	
	/**
	 * This determines what is previous train car (getter).
	 * @return train car after given train.
	 */
	public Car getPrevious() {
		return this.previous;
	}
	
	/**
	 * Established next train after this train.
	 * @param next is a train comes after this train. 
	 */
	public void setNext(Car next) {
		this.next = next;
	}
	/**
	 * Established previous train before this train.
	 * @param previous is a train comes before this train. 
	 */
	public void setPrevious(Car previous) {
		this.previous = previous;
	}
	/**
	 * Determines the name of the given train car. 
	 * @return a name of the given train car. 
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Determines if two Cars are same based on their name.
	 * @param o suppose to be train wagon. 
	 * @return true if two car has same name. Otherwise false. 
	 */
	public boolean equals(Object o) {
		//two cars are equal if they have the same name
		//O(1)
		if (!(o instanceof Car)) {
			return false;
		}
		return (this.name.equals(o.toString()));
	}
	/**
	 * String representation of given train car. 
	 * @return name of the car. 
	 */
	public String toString() {
		return this.getName();
	}
	
	/**
	 * General tester for Car class. 
	 * @param args not applicable. 
	 */
	public static void main(String[] args) {
		Car c1 = new Car("C1");
		Car c2 = new Car("C2");
		
		c1.setNext(c2);
		c2.setPrevious(c1);
		
		if(c1.getName().equals("C1")) {
			System.out.println("Yay 1");
		}
		
		if(c1.getNext().equals(c2) && c2.getPrevious().equals(c1)) {
			System.out.println("Yay 2");
		}
		
		Car c1b = new Car("C1");
		if(c1.equals(c1b)) {
			System.out.println("Yay 3");
		}
		
		c1.printAscii();
	}
	
	//*****************************************************************/
	//****************** DO NOT EDIT BELOW THIS LINE ******************/
	//*****************************************************************/

	/**
	 * Visualizes(displays) the given Cars.  
	 */
	public void printAscii() {
		/*
		From: http://www.ascii-art.de/ascii/t/train.txt
		 _________
		 |O O O O|
		-|_______|
		   o   o  
		*/
		
		Car current = this;
		while(current != null) {
			System.out.print(" _________");
			current = current.getNext();
		}
		System.out.println();
		
		current = this;
		while(current != null) {
			System.out.print(" | "+String.format("%-5s",current.getName())+" |");
			current = current.getNext();
		}
		System.out.println();
		
		current = this;
		while(current != null) {
			System.out.print("-|_______|");
			current = current.getNext();
		}
		System.out.println();
		
		current = this;
		while(current != null) {
			System.out.print("   o   o  ");
			current = current.getNext();
		}
		System.out.println();
	}
}