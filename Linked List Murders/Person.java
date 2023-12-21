/**
 * Person class that creates a person and assigns wagon to 
 * that given person. 
 * @author Adilet Kuroda
 *
 */
class Person {
	/**
	 * Name of the person. 
	 */
	private String name;
	/**
	 * Wagon that person occupying. 
	 */
	private Car currentCar;
	/**
	 * General Constructor for creating a person object. 
	 * @param name it is name of the person. 
	 * @param currentCar wagon that person is accommodating. 
	 */
	public Person(String name, Car currentCar) {
		this.name = name;
		this.currentCar = currentCar;
		
	}
	/**
	 * Identifies the name of the person (getter) and returns it. 
	 * @return name of a person. 
	 */
	public String getName() {
	
		return this.name;
	}
	/**
	 * Determines what car the given person is in. 
	 * @return car object that person is in. 
	 */
	public Car getCurrentCar() {
		//returns the current car
		//O(1)
		return this.currentCar;
	}
	/**
	 * Moves the person to next or previous car if the 
	 * given car is adjacent to the car that person is in. 
	 * @param c new car that person suppose to move. 
	 * @return true if person is moved. Otherwise returns false. 
	 */
	public boolean moveToCar(Car c) {
		if (this.currentCar.getNext()!=null && this.currentCar.getNext().equals(c)) {
			this.currentCar = c;
			return true;
		}
		else if((this.currentCar.getPrevious() !=null && this.currentCar.getPrevious().equals(c))) {
			this.currentCar = c;
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Determines if the two people are the same based on 
	 * their name. 
	 * @param o is person given person is compared. 
	 * @return returns true if two person has same name, otherwise false. 
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Person)) {
			return false;
		}
		return (this.name.equals(o.toString()));
	}
	/**
	 * String representation of given person. i.e name. 
	 * @return name of given person. 
	 */
	public String toString() {
		return this.name;
	}
	
	/**
	 * General tester for person class. 
	 * @param args Not applicable. 
	 */
	public static void main(String[] args) {
		Car c1 = new Car("C1");
		Car c2 = new Car("C2");
		Car c3 = new Car("C3");
		
		c1.setNext(c2);
		c2.setPrevious(c1);
		
		Person p1 = new Person("P1", c1);
		
		if(p1.getCurrentCar().equals(c1) && p1.getName().equals("P1")) {
			System.out.println("Yay 1");
		}
		
		if(p1.moveToCar(c2) && p1.getCurrentCar().equals(c2) && p1.moveToCar(c1) && p1.getCurrentCar().equals(c1)) {
			System.out.println("Yay 2");
		}
		
		Person p1b = new Person("P1", c1);
		if(p1.equals(p1b) && !p1.equals(new Person("P2", c1))) {
			System.out.println("Yay 3");
		}
	}
}