import java.util.Collection;
import java.util.Set;
import java.util.Iterator;

//You need to add javadocs to this class.
//You need to submit this file for grading.
//If you don't submit this for grading we will use
//a vanialla version which doesn't have javadocs.
//This will mean that your code won't pass the style checker.

//Remember that for interface methods with existing documentation
//(such as the java.util.Set interface), the documentation is already
//written, you just need to resuse that (don't copy-and-paste it,
//use inheritdoc).
/**
 * ThreeTenHashSet class. 
 * @param <E> generic type. 
 */
class ThreeTenHashSet<E> implements Set<E> {
	//********************************************************************************
	//   YOU MAY, BUT DON'T NEED TO EDIT THINGS IN THIS SECTION
	// These are some methods we didn't write for you, but you could write.
	// if you need/want them for building your graph. We will not test
	// (or grade) these methods.
	//********************************************************************************
	/**
	 * Unimplemented method. Do not use. 
	 * @param c is parameter.
	 * @return boolean. 
	 * @throws UnsupportedOperationException since unimplemented. 
	 */
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	/**
	 * Unimplemented method. Do not use. 
	 * @param c is parameter.
	 * @return boolean. 
	 * @throws UnsupportedOperationException since unimplemented. 
	 */
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	/**
	 * Unimplemented method. Do not use. 
	 * @param a is parameter.
	 * @param <T> generic type. 
	 * @return a collection.
	 * @throws UnsupportedOperationException since unimplemented. 
	 */
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}
	/**
	 * Unimplemented method. Do not use. 
	 * @param c is parameter.
	 * @return boolean. 
	 * @throws UnsupportedOperationException since it is not implemented. 
	 */
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}
	/**
	 * Unimplemented method. Do not use. 
	 * @param c is parameter.
	 * @return boolean. 
	 * @throws UnsupportedOperationException since it is not implemented. 
	 */
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	/**
	 * Unimplemented method. Do not use. 
	 * @param o is parameter.
	 * @return boolean. 
	 * @throws UnsupportedOperationException since it is not implemented. 
	 */
	public boolean equals(Object o) {
		throw new UnsupportedOperationException();
	}
	/**
	 * Unimplemented method. Do not use. 
	 * @return integer.
	 * @throws UnsupportedOperationException since it is not implemented. 
	 */
	public int hashCode() {
		throw new UnsupportedOperationException();
	}
	
	//********************************************************************************
	//   DO NOT EDIT ANYTHING BELOW THIS LINE (except to add the JavaDocs)
	// We will grade these methods to make sure they still work, so don't break them.
	//********************************************************************************
	/**
	 * This stores the given set. 
	 */
	private ThreeTenHashMap<E,E> storage = new ThreeTenHashMap<>(5);
	/**
	 * Add an element to set. 
	 * @param e is an element. 
	 * @return status of operation. 
	 */
	public boolean add(E e) {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.put(e, e) == null;
	}
	/**
	 * Resets the given set to size of 5. 
	 */
	public void clear() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		storage = new ThreeTenHashMap<>(5);
	}
	/**
	 * Checks if given object exist in a set. 
	 * @param o is an object. 
	 * @return true if exist and false if not. 
	 */
	public boolean contains(Object o) {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.get(o) != null;
	}
	/**
	 * Checks if the given set is empty or not. 
	 * @return true if empty else false. 
	 */
	public boolean isEmpty() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return size() == 0;
	}
	/**
	 * Removes given object from set. 
	 * @param o is an object to be removed. 
	 * @return true if removed, else false. 
	 */
	public boolean remove(Object o) {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.remove(o) != null;
	}
	/**
	 * Determines the size of an storage. 
	 * @return size of an set. 
	 */
	public int size() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.size();
	}
	/**
	 * Turns the set into an array.
	 * @return array form of given set. 
	 */
	public Object[] toArray() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		ThreeTenHashMap.TableEntry[] arr = storage.toArray();
		Object[] ret = new Object[arr.length];
		for(int i = 0; i < arr.length; i++) {
			ret[i] = arr[i].key;
		}
		return ret;
	}
	/**
	 * String representation of a set. 
	 * @return string representation of set. 
	 */
	public String toString(){
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.toString();
	}
	/**
	 * Creates an iterator for the instance of an given set. 
	 * @return an Iterator for set. 
	 */
	public Iterator<E> iterator() {
		//THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return new Iterator<E>() {
			private Object[] values = toArray();
			private int currentLoc = 0;
			
			@SuppressWarnings("unchecked")
			public E next() {
				return (E) values[currentLoc++];
			}
			
			public boolean hasNext() {
				return currentLoc != values.length;
			}
		};
	}
}