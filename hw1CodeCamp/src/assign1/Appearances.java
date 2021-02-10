package assign1;

import java.util.*;

public class Appearances {
	
	/**
	 * Returns the number of elements that appear the same number
	 * of times in both collections. Static method. (see handout).
	 * @return number of same-appearance elements
	 */
	public static <T> int sameCount(Collection<T> a, Collection<T> b) {
		Hashtable<T, Integer> tableA = createCounts(a);
		Hashtable<T, Integer> tableB = createCounts(b);
		
		int numSameCounts = 0;
		
		for (T key : tableA.keySet()) {
			if (tableB.containsKey(key) && tableA.get(key).equals(tableB.get(key))) {
				numSameCounts++;
			}
		}
		
		return numSameCounts;
	}
	
	private static <T> Hashtable<T,Integer> createCounts(Collection<T> a) {
		Hashtable<T, Integer> tableA = new Hashtable<T, Integer>();
		
		for (T element : a) {
			if (!tableA.containsKey(element)) {
				tableA.put(element, 1);
			} else {
				int num = tableA.get(element);
				num++;
				tableA.put(element, num);
			}
		}
		
		return tableA;
	}
	
}
