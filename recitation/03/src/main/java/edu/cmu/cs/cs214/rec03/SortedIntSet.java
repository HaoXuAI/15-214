package edu.cmu.cs.cs214.rec03;

/**
 * SortedIntSet -- a variant of a SortedIntList that contains only unique
 * numbers
 * 
 * @author Evans Hauser
 * 
 */
public class SortedIntSet extends SortedIntList {

	/**
	 * Constructor for the SortedIntSet class.
	 */
	public SortedIntSet() {
		super();
	}

	/**
	 * Adds the specified int to the set if not already contained in the set
	 * 
	 * @param num
	 *            an integer to be added to the set
	 * @return true if the set is changed as a result of the call
	 */
	@Override
	public boolean add(int num) {
		for(int i = 0; i < super.size(); i++){
			if(num == super.get(i))
				return false;
		}
		return super.add(num);
	}

	/**
	 * Adds all of the elements of the IntegerList to the set that are not
	 * already contained in the set
	 * 
	 * @param list
	 *            IntegerList containing elements to be added to the set
	 * @return true if the set changed as a result of the call
	 */
	@Override
	public boolean addAll(IntegerList list) {
		for(int i = 0; i < list.size(); i++){
			for(int j = 0; j < super.size(); j++){
				if(list.remove(list.get(i)))
					i--;
			}
		}
		return super.addAll(list);
	}
}
