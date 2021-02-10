/*
 HW1 Taboo problem class.
 Taboo encapsulates some rules about what objects
 may not follow other objects.
 (See handout).
*/
package assign1;

import java.util.*;

public class Taboo<T> {
	private Hashtable<T, Set<T>> ruleTable;
	
	/**
	 * Constructs a new Taboo using the given rules (see handout.)
	 * @param rules rules for new Taboo
	 */
	public Taboo(List<T> rules) {
		this.ruleTable = new Hashtable<T, Set<T>>();
		
		T previousItem = null;
		
		for (T item : rules) {
			if (item != null && !ruleTable.containsKey(item)) {
				ruleTable.put(item, new HashSet<T>());
			}
			
			if (previousItem != null) {
				Set<T> set = ruleTable.get(previousItem);
				set.add(item);
			}
			previousItem = item;
		}
	}
	
	/**
	 * Returns the set of elements which should not follow
	 * the given element.
	 * @param elem
	 * @return elements which should not follow the given element
	 */
	public Set<T> noFollow(T elem) {
		if (elem != null && this.ruleTable.containsKey(elem)) {
			return this.ruleTable.get(elem);
		} else {
			return Collections.emptySet();
		}
	}
	
	/**
	 * Removes elements from the given list that
	 * violate the rules (see handout).
	 * @param list collection to reduce
	 */
	public void reduce(List<T> list) {
		Iterator<T> iter = list.iterator();
		
		T previousItem = null;
		
		while (iter.hasNext()) {
			T currentItem = iter.next();
			
			if (noFollow(previousItem).contains(currentItem)) {
				iter.remove();
			} else {
				previousItem = currentItem;
			}
		}
	}
}
