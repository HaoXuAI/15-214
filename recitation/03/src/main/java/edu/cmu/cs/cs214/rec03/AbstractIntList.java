package edu.cmu.cs.cs214.rec03;

/**
 * AbstractIntList -- a list of integers.
 * 
 * @author Nora Shoemaker
 *
 */
public abstract class AbstractIntList implements IntegerList {

	/**
	 * Adds the specified int to the list.
	 * 
	 * @param num an integer to be added to the list
	 * @return true if the list is changed as a result of the call
	 */
	public abstract boolean add(int num);
	
	/**
	 * Adds all of the elements of the IntegerList to the list.
	 * Calls add() on each element of list. Can be overwritten 
	 * for more specific behavior.
	 * 
	 * @param list IntegerList containing elements to be added to the list
	 * @return true if the list changed as a result of the call
	 */
	public boolean addAll(IntegerList list) {
		
		boolean success = false;
		
		for (int i = 0; i < list.size(); i++)
		{
			success |= this.add(list.get(i));
		}
		
		return success;
	}
	
	/**
	 * Returns the integer at the specified position in this list.
	 * 
	 * @param index index of the element to return
	 * @return the element at the specified position in this list
	 */
	public abstract int get(int index);
	
	/**
	 * Removes the first occurrence of the specified element from the list, 
	 * if it is present (optional operation).
	 * 
	 * @param num an integer to be removed from the list, if present
	 * @return true if an element was removed as a result of this call
	 */
	public abstract boolean remove(int num);
	
	/**
	 * Removes from the list all of its elements that are contained in the 
	 * specified IntegerList.
	 * 
	 * @param list IntegerList containing elements to be removed from
	 * the list
	 * @return true if the list changed as a result of the call
	 */
	public abstract boolean removeAll(IntegerList list);

	/**
	 * Returns the number of elements in this list. If this list contains 
	 * more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
	 * 
	 * @return number of elements in the list
	 */
	public abstract int size();

}
