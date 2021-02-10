// HW1 2-d array Problems
// CharGrid encapsulates a 2-d grid of chars and supports
// a few operations on the grid.

package assign1;

public class CharGrid {
	private char[][] grid;

	/**
	 * Constructs a new CharGrid with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public CharGrid(char[][] grid) {
		this.grid = grid;
	}
	
	/**
	 * Returns the area for the given char in the grid. (see handout).
	 * @param ch char to look for
	 * @return area for given char
	 */
	public int charArea(char ch) {
		int numRow = this.grid.length;
		
		if (numRow == 0) {
			return 0;
		}

		int numCol = this.grid[0].length;
		
		int minX = -1;
		int maxX = -1;
		int minY = -1;
		int maxY = -1;
		boolean foundMatch = false;
		
		for (int row = 0; row < numRow; row++) {
            for (int col = 0; col < numCol; col++) {
                if (this.grid[row][col] != ch) {
                	continue;
                }
                
                if (!foundMatch) { // initiate minX maxX minY maxY with the first cell that matches ch
            		minX = row;
            		maxX = row;
            		minY = col;
            		maxY = col;
            		foundMatch = true;
            	} else {
            		if (row < minX) { // code coverage said this never runs because we visit row in the order of (0,1,...n), so this condition technically never got visited. Keeping it in here for clarity
                		minX = row;
                	}
                	if (row > maxX) {
                		maxX = row;
                	}
                	if (col < minY) {
                		minY = col;
                	}
                	if (col > maxY) {
                		maxY = col;
                	}
            	}
            }
		}

		if (!foundMatch){ // if no character matches, the area will actually be 1 because of the default values of Xs and Ys.
			return 0;
		} else {
			return (maxY - minY + 1) * (maxX - minX + 1);
		}
		
	}
	
	/**
	 * Returns the count of '+' figures in the grid (see handout).
	 * @return number of + in grid
	 */
	public int countPlus() {
		int numRow = this.grid.length; 
		
		if (numRow < 3) { // cannot possibly have an "arm" with two or less rows
			return 0;
		}
		
		int numCol = this.grid[0].length;
		
		if (numCol < 3) { // cannot possibly have an "arm" with two or less columns
			return 0;
		}
		
		int numPlus = 0;
		
		for (int row = 1; row < numRow - 1; row++) { // loop through all the potential mid-points
            for (int col = 1; col < numCol - 1; col++) {
            	char midPoint = this.grid[row][col];
            	
            	int occurence_upper = 0;
            	int occurence_lower = 0;
            	int occurence_left = 0;
            	int occurence_right = 0;
            	
            	// Check upper arm
            	for (int i = row -1 ; i >= 0; i--) {
            		if (this.grid[i][col] != midPoint) {
            			break;
            		}
            		occurence_upper++;
            	}
            	
            	// Check lower arm
            	for (int i = row + 1; i < numRow; i++) {
            		if (this.grid[i][col] != midPoint) {
            			break;
            		}
            		occurence_lower++;
            	}
            	
            	// Check left arm
            	for (int j = col - 1; j >= 0; j--) {
            		if (this.grid[row][j] != midPoint) {
            			break;
            		}
            		occurence_left++;
            	}
            	
            	// Check right arm
            	for (int j = col +1 ; j < numCol; j++) {
            		if (this.grid[row][j] != midPoint) {
            			break;
            		}
            		occurence_right++;
            	}

            	if (checkAllEqual(occurence_upper, occurence_lower, occurence_left, occurence_right) && occurence_upper != 0) {
            		numPlus++;
            	}
            }
		}
		return numPlus;
	}
	
	private static boolean checkAllEqual(int a, int b, int c, int d) {
		if (a == b && a == c && a == d) {
			return true;
		}
		return false;
	}
	
}
