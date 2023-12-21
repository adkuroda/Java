/**
 * Creates an object that has key and value related to key. 
 * @author Adilet Kuroda
 *
 * @param <K> keys of the given object. 
 * @param <V> values for given object. 
 */
class UniquePairList<K,V> {
	/**
	 * Unit that has key and value. 
	 * @author Adilet Kuroda
	 * @param <K> represents a key for the Node. 
	 * @param <V> represents a value for the Node. 
	 */
	private static class Pair<K,V> {
		/**
		 * Represents a key for the object. 
		 */
		protected K key;
		/**
		 * represents value of an object. 
		 */
		protected V value;
		/**
		 * Constructor to create a Pair object. 
		 * @param key value initializes the key of the object. 
		 * @param value initializes the value of an object.  
		 */
		public Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}
		/**
		 * Determines if two object have the same key. 
		 * @param o is an object that is being compared to based on its key value. 
		 * @return true if they have same key and they are same object, otherwise it returns false. 
		 */
		@SuppressWarnings("unchecked")
		public boolean equals(Object o) {
			if (!(o instanceof Pair)) {
				return false;
			}
			else {
				Pair<K,V> temp = (Pair<K, V>) o;
				if (this.key.equals(temp.key)){
					return true;
				}
				return false;
			}
		}
		/**
		 * Creates a string representation of based on key and value of an object.
		 * @return string representation of object. 
		 */
		public String toString() {
			//this method is done for you
			return "<" + getKey() + "," + getValue() + ">";
		}
		/**
		 * Identifies and returns the key of an object. 
		 * @return key of the given object. 
		 */
		public K getKey() {
			return key;
		}
		/**
		 * Identifies and returns the value of an object. 
		 * @return value of the given object. 
		 */
		public V getValue() {
			return value;
		}
	}
	
	/**
	 * Testing purpose use only. 
	 * @param args not applicable. 
	 */
	public static void main(String[] args) {
		Pair<String,Integer> p1 = new Pair<>("Fred", 1);
		Pair<String,Integer> p2 = new Pair<>("Alex", 1);
		Pair<String,Integer> p3 = new Pair<>("Fred", 2);
		
		if(p1.getKey().equals("Fred") && p1.getValue() == 1 && p1.equals(p3)) {
			System.out.println("Yay 1");
		}
		
		if(!p1.equals(p2)) {
			System.out.println("Yay 2");
		}
		
		//this is actually a test of UniqueList, not UniquePairList
		UniqueList<Pair<String,Integer>> set = new UniqueList<>();
		set.append(p1);
		
		//get the value from the set that is _equal to_ p3 (in this case, p1)
		Pair<String,Integer> p1fromSet = set.get(p3);
		//	System.out.println(p1fromSet.getValue());
		if(p1fromSet.getValue() == 1) {
			System.out.println("Yay 3");
		}
	}
	
	//*****************************************************************/
	//****************** DO NOT EDIT BELOW THIS LINE ******************/
	//********************* EXCEPT TO ADD JAVADOCS ********************/
	//*****************************************************************/
	
	/**
	 * List containing key value pair objects that are unique. 
	 */
	private UniqueList<Pair<K,V>> set = new UniqueList<>();

	/**
	 * Adds new item to  end of the list that has key and value. If such 
	 * item exist, it will not add the items to the list. 
	 * @param key for given value. 
	 * @param value that is associated with the given key. 
	 * @return true if appends, otherwise false. 
	 */
	public boolean append(K key, V value) {
		Pair<K,V> pair = new Pair<>(key, value);
		return set.append(pair);
	}
	/**
	 * If item does not exist in given list returns false. Otherwise, 
	 * it removes the item and adds it to the end of the list. 
	 * @param key of the pair. 
	 * @param value of the pair. 
	 * @return if the key does not exist i
	 */
	public boolean update(K key, V value) {
		Pair<K,V> pair = new Pair<>(key, value);
		if(!remove(key)) {
			return false;
		}
		return set.append(pair);
	}
	
	/**
	 * Removed the item based on it's key value. 
	 * @param key value of the item being removed. 
	 * @return true if removed, otherwise false. 
	 */
	@SuppressWarnings("unchecked")
	public boolean remove(K key) {
		Pair<K,V> pair = new Pair<>(key, null);
		return set.remove(pair);
	}
	/**
	 * Based on the key, it will return the value corresponds to the given key. 
	 * @param key for the object. 
	 * @return value of if given key exist in list, otherwise return null.  
	 */
	@SuppressWarnings("unchecked")
	public V getValue(K key) {
		Pair<K,V> pair = new Pair<>(key, null);
		return set.get(pair).getValue();
	}
	/**
	 * Determines all the keys in the given list and returns it. 
	 * @return copy of keys to given list. 
	 */
	public UniqueList<K> getKeys() {
		UniqueList<K> keySet = new UniqueList<>();
		for(Pair<K,V> p : set) {
			keySet.append(p.getKey());
		}
		return keySet.clone();
	}
	/**
	 * Determines actual size of the list and returns it. 
	 * @return size of the list. 
	 */
	public int size() {
		return set.size();
	}
}