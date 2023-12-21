import java.util.Iterator;
/**
 * Class represents a series of Car attached together. 
 * @author Adilet Kuroda
 *
 */
class Train implements Iterable<Car> {
	/**
	 * Keeps track of the beginning of the given train. 
	 */
	private Car head;
	/**
	 * Keeps track of the end of the given train. 
	 */
	private Car tail;
	/**
	 * Name of the given train.  
	 */
	private String name;
	/**
	 * Constructor for the train. 
	 * @param name is identification for given train. 
	 */
	public Train(String name) {
		this.name = name;
	}
	/** Identifies train's name and returns it. 
	 * @return returns the name of the given train. 
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Creates new Iterator that has next and hasNext Methods. This
	 * given an ability to implement traverse the given train link. 
	 * @return Iterator specific to given train. 
	 */
	public Iterator<Car> iterator() {
		return new Iterator<Car>() {
			/**
			 * This keeps track of where the given iterator is located. 
			 */
			Car current = head;
			
			/** Determines if given Car is exist or not. 
			 * @return true if exist, otherwise false. 
			 */
			@Override
			public boolean hasNext() {
				return (current != null);
			}
			/**
			 * Returns the Car and advances it to next Car. 
			 * @return car from the list. 
			 */
			@Override
			public Car next() {
				Car temp = current;
				current = current.getNext();
				return temp;
			}
		};
	}
	/**
	 * Determines if two trains are the same based on it given name.
	 * @param o is an instance of a train.  
	 * @return boolean they are same train. 
	 */
	public boolean equals(Object o) {
		if (!(o instanceof Train)) {
			return false;
		}
		return this.toString().equals(o.toString());
	}
	
	
	/**
	 * Connect a given wagon to the end of the train. Given wagon  
	 * can have more wagons attached to it. 
	 * @param c is a Car(wagon).
	 */
	public void connectCar(Car c) {
		Car current = c;
		while(current.getNext() !=null) {
			current = current.getNext();
		}
		if (head == null) {
			head = c;
			tail = current;
		}
		else {
			tail.setNext(c);
			c.setPrevious(tail);
			//tail = null;
			tail = current;
		}
		
		
	}
	/**
	 * Removes given wagon and any wagon following the given wagon. If there is
	 * no wagon as given parameter it throws RuntimeException.
	 * @param c wagon that should be removed.
	 * @return all the wagon from wagon c to the end. 
	 * @throws RunTimeException when such c (Car) does not exist in given train link.  
	 */
	public Car disconnectCar(Car c) {
		Car current = head;
		if (current.equals(c)) {
			head = null;
			tail = null;
			return current;
		}
		current = current.getNext();
		while(current != null) {
			if (current.equals(c)) {
				Car prev = current.getPrevious();
				prev.setNext(null);
				tail = prev;
				current.setPrevious(null);
				return current;
				
			}
			current = current.getNext();	
		}
		throw new RuntimeException("Can not disconnect a car that doesn't exist");
	}
		

	/**
	 * Reverses order of the train. Thus last train becomes first and so on. 
	 * The first train becomes last train in order. 
	 */
	public void reverseTrain() {
		Car current = tail;
		Car newLine = new Car(tail.getName());
		head = newLine;

		while (current.getPrevious() != null) {
			current = current.getPrevious();
			Car temp = new Car(current.getName());
			temp.setPrevious(newLine);
			newLine.setNext(temp);
			newLine = newLine.getNext();
		}
		
	}
	
	/**
	 * General tester for given class. 
	 * @param args not applicable. 
	 */
	public static void main(String[] args) {
		Car c1 = new Car("C1");
		Car c2 = new Car("C2");
		
		c1.setNext(c2);
		c2.setPrevious(c1);
		
		Train t1 = new Train("T1");
		Train t1b = new Train("T1");
		
		if(t1.getName().equals("T1") && t1.equals(t1b)) {
			System.out.println("Yay 1");
		}
		
		t1.printAscii();
		
		t1.connectCar(c1);
		t1.printAscii();
		
		Car c3 = new Car("C3");
		Car c4 = new Car("C4");
		
		c3.setNext(c4);
		c4.setPrevious(c3);
		
		t1.connectCar(c3);
		t1.printAscii();
		
		t1.reverseTrain();
		t1.printAscii();
	}
	//*****************************************************************/
	//****************** DO NOT EDIT BELOW THIS LINE ******************/
	//*****************************************************************/
	
	/**
	 * String representation of train system. This displays name of the 
	 * train and all the cars' name that is attached to train. 
	 * @return string representation of given train link. 
	 */
	public String toString() {
		String s = getName();
		for(Car c : this) {
			s += " " + c;
		}
		return s;
	}
	/**
	 * Visualizes(displays) the given train system. 
	 */
	public void printAscii() {
		/*
		From: http://www.ascii-art.de/ascii/t/train.txt
		    o O___ _________
		  _][__|o| |O O O O|
		 <_______|-|_______|
		  /O-O-O     o   o  
		*/
		
		System.out.print(String.format("%-4s",getName())+"o O___");
		for(Car c : this) {
			System.out.print(" _________");
		}
		System.out.println();
		
		System.out.print("  _][__|o|");
		for(Car c : this) {
			System.out.print(" | "+String.format("%-5s",c.getName())+" |");
		}
		System.out.println();
		
		System.out.print(" |_______|");
		for(Car c : this) {
			System.out.print("-|_______|");
		}
		System.out.println();
		
		System.out.print("  /O-O-O  ");
		for(Car c : this) {
			System.out.print("   o   o  ");
		}
		System.out.println();
	}
}