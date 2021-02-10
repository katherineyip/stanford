package assign1;

import java.util.HashSet;
import java.util.Set;

// CS108 HW1 -- String static methods

public class StringCode {

	/**
	 * Given a string, returns the length of the largest run.
	 * A a run is a series of adjacent chars that are the same.
	 * @param str
	 * @return max run length
	 */
	public static int maxRun(String str) {
		Character previousChar = null;
		int maxCount = 0;
		int currentCount = 0;
		
		for (int i = 0; i < str.length(); i++) {
			char currentChar = str.charAt(i);
			if (previousChar != null && currentChar == previousChar) {
				currentCount++;
			} else {
				currentCount = 1;
				previousChar = currentChar;
			}
			maxCount = maxValue(currentCount, maxCount);
		}
		
		return maxCount;
	}

	private static int maxValue (int a ,int b) {
		if (a > b) {
			return a;
		} else {
			return b;
		}
	}
	
	/**
	 * Given a string, for each digit in the original string,
	 * replaces the digit with that many occurrences of the character
	 * following. So the string "a3tx2z" yields "attttxzzz".
	 * @param str
	 * @return blown up string
	 */
	public static String blowup(String str) {
		String output = "";
		
		for (int i = 0; i < str.length(); i++) {
			char currentChar = str.charAt(i);
			if (Character.isDigit(currentChar)) { // currentChar is a digit
				int nextIndex = i+1;
				
				if (nextIndex < str.length()) { // currentChar is a digit but not at the last character
					int currentDigit = Character.getNumericValue(currentChar);
					char nextChar = str.charAt(nextIndex);
					for (int j = 0; j < currentDigit; j++) {
						output += nextChar;
					}
				} else { // currentChar is a digit and at the last character
					continue;
				}
			} else { // currentChar is not a digit
				output += currentChar;
			}
		}
		return output;
	}
	
}
