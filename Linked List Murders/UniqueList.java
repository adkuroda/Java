import java.util.Iterator;
/**
 * Creates a list that does not contain duplicate items. 
 * @author Adilet Kuroda
 *
 * @param <T> items in the list. 
 */
class UniqueList<T> implements Iterable<T> {
	/**
	 * Keeps track of the size of the list. 
	 */
	private int size;
	/**
	 * Keeps track of the beginning of the list. 
	 */
	private Node head;
	/**
	 * Keeps track of the end of the list. 
	 */
	private Node tail;
	
	/**
	 * Each element of list that contains a value and 
	 * reference to next item on the list. 
	 *
	 */
	private class Node{
		/**
		 * Actual value of the list. 
		 */
		private T value;
		/**
		 * Reference to next list. 
		 */
		private Node next;
		
			
	}
	/**
	 * Ensures the given list is unique. If item provided by parameter exist, 
	 * that it will not add the value. 
	 * @param value is item that will be added to the end of the list. 
	 * @return true if successful adding, otherwise false. 
	 */
	public boolean append(T value) {
		if (this.contains(value)) {
			return false;
		}
		else {
			Node temp = new Node();
			temp.value = value;
			if (size == 0) {
				head = temp;
				tail = temp;
			}
			else {
				tail.next = temp;
				tail = temp;
			}
			size++;
			return true;
		}
	}
	/**
	 * Removed item from the list. If item provided in parameter does not 
	 * exist, it returns false. Otherwise returns true. 
	 * @param value that will be removed. 
	 * @return true or false based on success of the operation. 
	 */
	public boolean remove(T value) {
		Node current = head;
		if (current.value.equals(value)) {
			head = current.next;
			size --;
			return true;
		}
		while(current.next != null) {
			if (current.next.value.equals(value)) {
				current.next = current.next.next;
				size--;
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	/**
	 * Determines if a value exist in given list and return it. 
	 * @param value to be searched in given list. 
	 * @return T value if exist, otherwise returns null. 
	 * 
	 */
	public T get(T value) {
		Node current = head;
		while(current != null) {
			if (current.value.equals(value)) {
				return current.value;
			}
			current = current.next;
		}
		return null;
	}
	
	/**
	 * Determines if a value exist in given list or not. 
	 * @param value is item that is being searched. 
	 * @return true if the value exist. Otherwise false. 
	 */
	public boolean contains(T value) {
		if (this.get(value) ==null) {
			return false;
		}
		return true;
	}
	/**
	 * Identifies the size of the list and returns it. 
	 * @return the size of the list. 
	 */
	public int size() {
		return this.size;
	}
	/**
	 * Copies the list to different location with same content of 
	 * given list. 
	 * @return a copy of given list. 
	 */
	public UniqueList<T> clone() {
		UniqueList<T> temp = new UniqueList<>();
		Node current = head;
		while(current != null) {
			temp.add(current.value);
			current = current.next;
		}
		return temp;
	}
	
	/**
	 * Helper method to implement clone method. Takes 
	 * @param value that is added to the list. 
	 */
	private void add(T value) {
		Node temp = new Node();
		temp.value = value;
		if (size == 0) {
			head = temp;
			tail = temp;
		}
		else {
			tail.next = temp;
			tail = temp;
		}
		size++;	
	}
	/**
	 * Internal use only for testing purposes. 
	 */
	private void toDisplay() {
		Node current = head;
		while (current !=null) {
			System.out.println(current.value + " ");
			current = current.next;
		}
	}

	/**
	 * This returns an object that given ability to iterate over the list from the 
	 * beginning to end of the list. next method identifies if there is next item and 
	 * hasNext returns reference to the given item. 
	 * @return an Iterator to iterate over given list. 
	 */
	public Iterator<T> iterator() {
		return  new Iterator<T>() {
			Node current = head;
			@Override
			public boolean hasNext() {
				return (current != null);
			}
			@Override
			public T next() {
				T temp = current.value;
				current = current.next;
				return temp;
			}	
		};
	}
	
	/**
	 * Testing purposes use only. 
	 * @param args not applicable. 
	 */
	public static void main(String[] args) {
		UniqueList<String> names = new UniqueList<>();
		
		if(names.append("Fred") && names.append("Alex") && !names.append("Fred")) {
			System.out.println("Yay 0");
		}
		
		if(names.size() == 2 && names.contains("Fred") && names.get("Alex").equals("Alex")) {
			System.out.println("Yay 1");
		}
		
		if(names.remove("Alex") && names.size() == 1 && names.get("Alex") == null) {
			System.out.println("Yay 2");
		}
		
		boolean hasEverything = false;
		for(String name : names) {
			if(hasEverything) {
				hasEverything = false;
				break;
			}
			
			hasEverything = name.equals("Fred");
		}
		if(hasEverything) {
			System.out.println("Yay 3");
		}
		
		/**
		 * Implementation testing purpose only. 
		 * @author Instructor 
		 *
		 */
		class Cat {
			String name;
			public Cat(String name) { this.name = name; }
			/**
			 * This method is created for testing purpose only. 
			 * @param o is object compared to Cat object. 
			 * @return boolean representing if the two object are same or not.
			 */
			public boolean equals(Object o) {
				if(o instanceof Cat) {
					Cat c = (Cat)o;
					return c.name.equals(this.name);
				}
				return false;
			}
		}
		
		UniqueList<Cat> catSet1 = new UniqueList<>();
		catSet1.append(new Cat("Sammy"));
		catSet1.append(new Cat("Grouchy"));
		UniqueList<Cat> catSet2 = catSet1.clone();
		if(catSet1 != catSet2 && catSet1.size() == catSet2.size()) {
			int matched = 0;
			for(Cat c : catSet1) {
				if(catSet2.get(c) == c) matched++;
			}
			if(matched == 2) {
				System.out.println("Yay 4");
			}
		}
		UniqueList<Integer> ul = new UniqueList<>();
		ul.append(1);
		ul.append(2);
		ul.append(3);
		ul.append(4);
		
		ul.toDisplay();
		System.out.println("size = "+ ul.size);
		UniqueList<Integer> ulclone = ul.clone();
		System.out.println("size = "+ ul.size);
		System.out.println("size clone = "+ ulclone.size);
		
		
	}
	
}
	