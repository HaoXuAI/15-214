package edu.cmu.cs.cs214.rec03;

/**
 * IntegerList -- a list of integers.
 *  
 * @author Nora Shoemaker
 *
 */
public interface IntegerList {
	
	/**
	 * Adds the specified int to the end of the list.
	 * 
	 * @param num an integer to be added to the list
	 * @return true if the list is changed as a result of the call
	 */
	public boolean add(int num);
	
	/**
	 * Adds all of the elements of the IntegerList to the end of the list.
	 * 
	 * @param list IntegerList containing elements to be added to the list
	 * @return true if the list changed as a result of the call
	 */
	public boolean addAll(IntegerList list);
	
	/**
	 * Returns the integer at the specified position in this list.
	 * 
	 * @param index index of the element to return
	 * @return the element at the specified position in this list
	 */
	public int get(int index);
	
	/**
	 * Removes the first occurrence of the specified element from the list, 
	 * if it is present (optional operation).
	 * 
	 * @param num an integer to be removed from the list, if present
	 * @return true if an element was removed as a result of this call
	 */
	public boolean remove(int num);
	
	/**
	 * Removes from the list all of its elements that are contained in the 
	 * specified IntegerList.
	 * 
	 * @param list IntegerList containing elements to be removed from
	 * the list
	 * @return true if the list changed as a result of the call
	 */
	public boolean removeAll(IntegerList list);
	
	/**
	 * Returns the number of elements in this list. If this list contains 
	 * more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
	 * 
	 * @return number of elements in the list
	 */
	public int size();
	

}
