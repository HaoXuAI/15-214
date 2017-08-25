package edu.cmu.cs.cs214.rec03;

import edu.cmu.cs.cs214.rec03.SortedIntList;

/**
 * InheritanceSortedIntList -- a variant of a SortedIntList that keeps 
 * count of the number of attempted element insertions (not to be confused
 * with the current size, which goes down when an element is removed) 
 * and exports an accessor (totalAdded) for this count.
 * 
 * @author Nora Shoemaker
 *
 */
public class InheritanceSortedIntList extends SortedIntList {
	// the number of attempted element insertions
	private int totalAdded;

	@Override
    public boolean add(int val1) {
	    this.totalAdded++;
        return super.add(val1);
    }

	
	/**
	 * Gets the total number of attempted int insertions to the list since it
	 * was created.
	 * 
	 * @return total number of integers added to the list.
	 */
	public int getTotalAdded()
	{
		return this.totalAdded;
	}

}
