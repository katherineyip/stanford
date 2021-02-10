//
// TetrisGrid encapsulates a tetris board and has
// a clearRows() capability.
package assign1;

public class TetrisGrid {
	private boolean[][] grid;
	private int gridWidth;
	private int gridHeight;
	
	/**
	 * Constructs a new instance with the given grid.
	 * Does not make a copy.
	 * @param grid
	 */
	public TetrisGrid(boolean[][] grid) {
		this.grid = grid;
		this.gridWidth = grid.length;
		this.gridHeight = grid[0].length;
	}
	
	/**
	 * Does row-clearing on the grid (see handout).
	 */
	public void clearRows() {
		for (int y = 0; y < gridHeight; y++) {
			while (isFullRow(y)) {
				shiftRows(y);
			}
		}
	}
	
	private boolean isFullRow(int y) {
		for (int x = 0; x < gridWidth; x++) {
			if (grid[x][y] == false) {
				return false;
			}
		}
		return true;
	}
	
	private void shiftRows(int currentY) {
		for (int y = currentY; y < gridHeight - 1; y++) {
			for (int x = 0; x < gridWidth; x++) {
				grid[x][y] = grid[x][y+1];
			}
		}
		
		// after shifting one row, fill in the top row with all false
		for (int x1 = 0; x1 < gridWidth; x1++) {
			grid[x1][gridHeight-1] = false;
		}
	}
	
	/**
	 * Returns the internal 2d grid array.
	 * @return 2d grid array
	 */
	boolean[][] getGrid() {
		return grid;
	}
}
