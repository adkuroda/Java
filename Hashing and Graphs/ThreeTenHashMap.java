import java.util.Map;
import java.util.Set;

import java.util.Collection; //for returning in the values() function only

//If you uncomment the import to ArrayList below, the grader will manually
//look to make sure you are not using it anywhere else... if you use it
//somewhere else you will get 0pts on the entire project (not a joke).

//uncomment the line below ONLY if you are implementing values().
//import java.util.ArrayList; //for returning in the values() function only
/**
 * Class creates a data structure with key to value relationship. 
 * @param <K> is generic. 
 * @param <V> is generic. 
 */
class ThreeTenHashMap<K,V> implements Map<K,V> {
	//********************************************************************************
	//   DO NOT EDIT ANYTHING IN THIS SECTION (except to add the JavaDocs)
	//********************************************************************************
	
	//you must use this storage for the hash table
	//and you may not alter this variable's name, type, etc.
	/**
	 * This represents the given ThreeTenHashMap for storage. 
	 * @param <K> is generic. 
     * @param <V> is generic. 
	 */
	private Node<K,V>[] storage;
	
	//you must use to track the current number of elements
	//and you may not alter this variable's name, type, etc.
	/**
	 * Keeps track of the element in given map. 
	 */
	private int numElements = 0;
	
	//********************************************************************************
	//   YOUR CODE GOES IN THIS SECTION
	//********************************************************************************
	/**
	 * Variable that keeps track of original size of the map. 
	 */
	private int originalSize;
	/**
	 * Constructor that creates storage.
	 * @param size is the initial size of the given map.
	 */
	@SuppressWarnings("unchecked")
	public ThreeTenHashMap(int size) {
		this.storage = (Node<K,V>[]) new Node[size];
		this.originalSize = size;
	}
	/**
	 * This resets the given table to it's original size. 
	 */
	@SuppressWarnings("unchecked")
	public void clear() {
		this.storage = (Node<K,V>[]) new Node[originalSize];
		numElements = 0;

	}
	/**
	 * Determines if given table is empty or not. 
	 * @return true or false based on status of table content. 
	 */
	public boolean isEmpty() {
		return (numElements == 0);
	}
	/**
	 * Determines how many slots given table has. 
	 * @return number of slot on a given table. 
	 */
	public int getSlots() {
		return storage.length;
	}
	/**
	 * Determines how many element given table has. 
	 * @return number of elements given map has. 
	 */
	public int size() {
		return numElements;
	}
	/**
	 * Finds and returns value for the given key.
	 * @param key value of a map.  
	 * @return value of for the given key. If key does not exit in given table, returns null. 
	 * 
	 */
	public V get(Object key){
		int hashcode = key.hashCode()%storage.length;
		if (hashcode <0){
			hashcode *= -1;
		}
		if (storage[hashcode] == null){
			return null;
		}
		else{
			Node<K,V> temp = storage[hashcode];
			while(temp != null){
				if (temp.entry.key.equals(key)){
					return temp.entry.value;
				}
				temp = temp.next;
			}
		}
		return null;
	}
	/**
	 * Determines and retuns keys of given map. 
	 * @return a collections of keys for given map. 
	 */
	public Set<K> keySet() {
		Set<K> ret = new ThreeTenHashSet<>();
		for (int i = 0; i < storage.length; i++){
			if (storage[i] != null){
				Node<K,V> current = storage[i];
				while (current != null){
					ret.add(current.entry.key);
					current = current.next;	
				}
			}
		}
		
		return ret;
	}
	/**
	 * Removes an key and values based on given key. 
	 * @param key that needs to be removed. 
	 * @return null if removal is unsuccesful. Otherwise returns value of given key. 
	 */
	public V remove(Object key) {
		int hash = key.hashCode()% storage.length;
		if (hash <0){
			hash *= -1;
		}
		if (storage[hash] == null){
			return null;
		}
	
		if(storage[hash].entry.key.equals(key)){
			V ret = storage[hash].entry.value;
			storage[hash] = storage[hash].next;
			numElements--;
			return ret;
		}
		Node<K, V> current = storage[hash];
		while(current.next != null){
			if (current.next.entry.key.equals(key)){
				V ret = current.next.entry.value;
				current.next = current.next.next;
				numElements--;
				return ret;
			}
			current = current.next;
		}	
		return null;
	}
	/**
	 * puts an key value pair into a map. If key does not exist, adds new pair. Otherwise
	 * replaces the value of a key to new value. 
	 * @param key is key for the pair
	 * @param value is value for the pair. 
	 * @return null
	 */
	private V putNoExpand(K key, V value) {
		TableEntry<K,V> temp = new TableEntry<>(key, value);
		int hash = key.hashCode() % storage.length;
		if (hash <0){
			hash *= -1;
		}
		if (storage[hash] == null){
			numElements++;
			storage[hash] =  new Node<K,V>(temp);
			return null;
		}
		Node<K, V> current = storage[hash];
		while (true){
			if (current.entry.key.equals(key)){
				current.entry.value = value;
				return value;
			}
			if (current.next == null){
				break;
			}
			else{
				current = current.next;
			}
		}
		numElements++;
		current.next = new Node<K, V>(temp);	
		return null;
	}
	
	//--------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	//--------------------------------------------------------
	/**
	 * Main Function. for testing purposes only. 
	 * @param args testing purpose only. 
	 */
	public static void main(String[] args) {
		//main method for testing, edit as much as you want
		ThreeTenHashMap<String,String> st1 = new ThreeTenHashMap<>(10);
		ThreeTenHashMap<String,Integer> st2 = new ThreeTenHashMap<>(5);
		
		st1.put("a","apple");
		st1.put("b","banana");
		st1.put("banana","b");
		st1.put("b","butter");
		//System.out.println(st1.toString());
		if(st1.toString().equals("a:apple\nbanana:b\nb:butter") && st1.toStringDebug().equals("[0]: null\n[1]: null\n[2]: null\n[3]: null\n[4]: null\n[5]: null\n[6]: null\n[7]: [a:apple]->[banana:b]->null\n[8]: [b:butter]->null\n[9]: null")) {
			System.out.println("Yay 1");
		}
		//System.out.println(st1.getSlots());
		if(st1.getSlots() == 10 && st1.size() == 3 && st1.get("a").equals("apple")) {
			System.out.println("Yay 2");
		}
		
		st2.rehash(1);
		st2.put("a",1);
		st2.put("b",2);
		// System.out.println(st2.toString());
		// System.out.println("--------------");
		// System.out.println(st2.toStringDebug());
		// System.out.println("--------------");
		//System.out.println(st2.put("e",3));
		if(st2.toString().equals("b:2\na:1") && st2.toStringDebug().equals("[0]: [b:2]->null\n[1]: [a:1]->null")
			&& st2.put("e",3) == null && st2.put("y",4) == null &&
			st2.toString().equals("a:1\ne:3\ny:4\nb:2") && st2.toStringDebug().equals("[0]: null\n[1]: [a:1]->[e:3]->[y:4]->null\n[2]: [b:2]->null\n[3]: null")) {
			System.out.println("Yay 3");
		}
		// System.out.println("--------------" + st2.remove("e"));
		// System.out.println("--------------" + st2.rehash(8));
		// System.out.println("--------------" + st2.size());
		// System.out.println("--------------" + st2.getSlots());
		// System.out.println("--------------" + st2.toStringDebug());
		// System.out.println("--------------" + st2.toString());
		// System.out.println("---------end ");
		if(st2.remove("e").equals(3) && st2.rehash(8) == true &&
			st2.size() == 3 && st2.getSlots() == 8 &&
			st2.toString().equals("a:1\ny:4\nb:2") && st2.toStringDebug().equals("[0]: null\n[1]: [a:1]->[y:4]->null\n[2]: [b:2]->null\n[3]: null\n[4]: null\n[5]: null\n[6]: null\n[7]: null")) {
			System.out.println("Yay 4");
		}
		
		ThreeTenHashMap<String,String> st3 = new ThreeTenHashMap<>(2);
		st3.put("a","a");
		st3.remove("a");
		//System.out.println("--------" +st3.toString());
		if(st3.toString().equals("") && st3.toStringDebug().equals("[0]: null\n[1]: null")) {
			st3.put("a","a");
			if(st3.toString().equals("a:a") && st3.toStringDebug().equals("[0]: null\n[1]: [a:a]->null")) {
				System.out.println("Yay 5");
				//System.out.println(st3.keySet().toString());
				
			}
		}
		
		//This is NOT all the testing you need... several methods are not
		//being tested here! Some method return types aren't being tested
		//either. You need to write your own tests!
		
		//Also, try this and see if it works:
		ThreeTenHashMap<Integer,Integer> st4 = new ThreeTenHashMap<>(10);
		st4.put(Integer.MIN_VALUE, Integer.MIN_VALUE);
		System.out.println(st4);
		st4.clear();
		//System.out.println("Here we are" +st4);

	}
	
	//********************************************************************************
	//   YOU MAY, BUT DON'T NEED TO EDIT THINGS IN THIS SECTION
	// These are some methods we didn't write for you, but you could write,
	// if you need/want them for building your graph. We will not test
	// (or grade) these methods.
	//********************************************************************************
	/**
	 * Unimplemented Method. Do not use. 
	 * @return a Collection.   
	 * @throws UnsupportedOperationException exception. 
	 */
	public Collection<V> values() {
		throw new UnsupportedOperationException();
	}
	/**
	 * Unimplemented Method. Do not use. 
	 * @param m is parameter.
	 * @throws UnsupportedOperationException exception. 
	 */
	public void	putAll(Map<? extends K,? extends V> m) {
		throw new UnsupportedOperationException();
	}
	/**
	 * Unimplemented Method. Do not use. 
	 * @param value is parameter.
	 * @return boolean.  
	 * @throws UnsupportedOperationException exception. 
	 */
	public boolean containsValue(Object value) {
		throw new UnsupportedOperationException();
	}
	/**
	 * Unimplemented Method. Do not use. 
	 * @return set of Maps.   
	 * @throws UnsupportedOperationException exception. 
	 */
	public Set<Map.Entry<K,V>> entrySet() {
		throw new UnsupportedOperationException();
	}
	/**
	 * Unimplemented Method. Do not use. 
	 * @param o is parameter.
	 * @return boolean.  
	 * @throws UnsupportedOperationException exception. 
	 */
	public boolean equals(Object o) {
		throw new UnsupportedOperationException();
	}
	/**
	 * Unimplemented Method. Do not use. 
	 * @return integer.  
	 * @throws UnsupportedOperationException exception. 
	 */
	public int hashCode() {
		throw new UnsupportedOperationException();
	}
	/**
	 * Unimplemented Method. Do not use. 
	 * @param key is parameter.
	 * @return boolean.  
	 * @throws UnsupportedOperationException exception. 
	 */
	public boolean containsKey(Object key) {
		throw new UnsupportedOperationException();
	}
	
	//********************************************************************************
	//   DO NOT EDIT ANYTHING BELOW THIS LINE (except to add the JavaDocs)
	// We will check these things to make sure they still work, so don't break them.
	//********************************************************************************
	
	//THIS CLASS IS PROVIDED, DO NOT CHANGE IT
	/**
	 * This a Node class to build a map. Has key value pair and pointer to next node. 
	 * @param <K> is generic type.
	 * @param <V> is generic type. 
	 */
	public static class Node<K,V> {
		/**
		 * Key value pair of Node class.  
		 */
		public TableEntry<K,V> entry;
		/**
		 * pointer to next node. 
		 */
		public Node<K,V> next;
		/**
		 * Constructor for Node class. 
		 * @param entry is key value pair. 
		 */
		public Node(TableEntry<K,V> entry) {
			this.entry = entry;
		}
		/**
		 * Constructor for node class. 
		 * @param entry is key value pair. 
		 * @param next is pointer for next node.  
		 */
		public Node(TableEntry<K,V> entry, Node<K,V> next) {
			this(entry);
			this.next = next;
		}
		/**
		 * String representation of a node. 
		 * @return string represenation. 	
		 */
		public String toString() {
			return "[" + entry.toString() + "]->";
		}
	}
	
	//THIS CLASS IS PROVIDED, DO NOT CHANGE IT
	/**
	 * Class to implement Node that represents pair of key to value. 
	 * @param <K> is generic. 
	 * @param <V> is generic. 
	 */
	public static class TableEntry<K,V> {
		/**
		 * Key of the pair. 
		 */
		public K key;
		/**
		 * Value of the pair. 
		 */
		public V value;
		/**
		 * Constructor for TableEntry class. 
		 * @param key is key for the given object. 
		 * @param value represents value of the given key. 
		 */
		public TableEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		/**
		 * String represenation of TableEntry. 
		 * @return string repsentation. 
		 */
		public String toString() {
			return key.toString()+":"+value.toString();
		}
	}
	/**
	 * Converts given key value object to an array. 
	 * @return an collection of an object. 
	 */
	public TableEntry[] toArray(){
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		
		TableEntry[] collection = new TableEntry[this.numElements];
		int index = 0;
		for(int i = 0; i < storage.length; i++) {
			if(storage[i] != null) {
				Node<K,V> curr = storage[i];
				while(curr != null) {
					collection[index] = curr.entry;
					index++;
					curr = curr.next;
				}
			}
		}
		return collection;		
	}
	/**
	 * String representation of given map. 
	 * @return string form a map. 
	 */
	public String toString() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < storage.length; i++) {
			Node<K,V> curr = storage[i];
			if(curr == null) continue;
			
			while(curr != null) {
				s.append(curr.entry.toString());
				s.append("\n");
				curr = curr.next;
			}
		}
		return s.toString().trim();
	}
	/**
	 * String representation of given map for testing purpose only. 
	 * @return string form a map. 
	 */
	public String toStringDebug() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < storage.length; i++) {
			Node<K,V> curr = storage[i];
			
			s.append("[" + i + "]: ");
			while(curr != null) {
				s.append(curr.toString());
				curr = curr.next;
			}
			s.append("null\n");
		}
		return s.toString().trim();
	}
	/**
	 * This restructures the given table to new table. 
	 * @param size is new size of given table. 
	 * @return a stutus of a rehash. 
	 */
	@SuppressWarnings("unchecked")
	public boolean rehash(int size) {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		
		if(size < 1) return false;
		
		Node<K,V>[] oldTable = storage;
		storage = (Node<K,V>[]) new Node[size];
		numElements = 0;
		
		for(Node<K,V> node : oldTable) {
			while(node != null) {
				putNoExpand(node.entry.key, node.entry.value);
				node = node.next;
			}
		}
		
		return true;
	}
	/**
	 * puta key-value pair into a map. Doubles the size if reaches certain limit. 
	 * @param key for the pair. 
	 * @param value for the pair. 
	 * @return value associated to given key is succesful. Otherwise null. 
	 */
	public V put(K key, V value) {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		
		V ret = putNoExpand(key, value);
		while((numElements/(double)storage.length) >= 2) {
			rehash(storage.length*2);
		}
		return ret;
	}
}