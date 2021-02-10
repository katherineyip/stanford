// Board.java
package edu.stanford.cs108.tetris;

import java.util.Arrays;

/**
 CS108 Tetris Board.
 Represents a Tetris board -- essentially a 2-d grid
 of booleans. Supports tetris pieces and row clearing.
 Has an "undo" feature that allows clients to add and remove pieces efficiently.
 Does not do any drawing or have any idea of pixels. Instead,
 just represents the abstract 2-d board.
 */
public class Board	{
	// Some ivars are stubbed out for you:
	private int width;
	private int height;
	private boolean[][] grid;
	private boolean DEBUG = true;
	boolean committed;

	// Arrays to store board state
	private int[] rowWidth;
	private int[] colHeight;
	private int maxHeight;

	// Back up board and data
	private boolean[][] gridCopy;
	private int[] rowWidthCopy; // for back up purpose
	private int[] colHeightCopy;
	private int maxHeightCopy;

	/**
	 Creates an empty board of the given width and height
	 measured in blocks.
	 */
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new boolean[width][height];
		committed = true;

		rowWidth = new int[height]; // The widths array stores how many filled spots there are in each row.
		colHeight = new int[width]; // The heights array stores the height to which each column has been filled.
		maxHeight = 0;

		gridCopy = new boolean[width][height];
		rowWidthCopy = new int[height];
		colHeightCopy = new int[width];
	}


	/**
	 Returns the width of the board in blocks.
	 */
	public int getWidth() {
		return width;
	}


	/**
	 Returns the height of the board in blocks.
	 */
	public int getHeight() {
		return height;
	}


	/**
	 Returns the max column height present in the board.
	 For an empty board this is 0.
	 */
	public int getMaxHeight() {
		return maxHeight;
	}


	/**
	 Checks the board for internal consistency -- used
	 for debugging.
	 */
	public void sanityCheck() {
		if (DEBUG) {
			// Check Row Width
			int[] checkRowWidth = new int[height];
			for (int row = 0; row < height; row++) {
				int numBlockFilled = 0;
				for (int col = 0; col < width; col++) {
					if (grid[col][row]) numBlockFilled++;
				}
				checkRowWidth[row] = numBlockFilled;
			}

			if (!Arrays.equals(checkRowWidth, rowWidth)) {
				throw new RuntimeException("Sanity Check Failed: Inconsistent rowWidth. Expected: "
						+ Arrays.toString(checkRowWidth) + ". Actual: " + Arrays.toString(rowWidth));
			}

			// Check Col Height
			int[] checkColHeight = computeColHeight();
			System.out.println(Arrays.toString(checkColHeight));
			if (!Arrays.equals(checkColHeight, colHeight)) {
				throw new RuntimeException("Sanity Check Failed: Inconsistent colHeight. Expected: "
						+ Arrays.toString(checkColHeight) + ". Actual: " + Arrays.toString(colHeight));
			}

			// Check Max Height
			int checkMaxHeight = 0;
			for (int x = 0; x < width; x++) {
				if (checkColHeight[x] > checkMaxHeight) {
					checkMaxHeight = checkColHeight[x];
				}
			}

			if (checkMaxHeight != maxHeight) {
				throw new RuntimeException("Sanity Check Failed: Inconsistent maxHeight. Expected: "
						+ checkMaxHeight + ". Actual: " + maxHeight);
			}
		}
	}

	/**
	 Return the highest y value for each x.
	 */
	private int[] computeColHeight() {
		int[] colHeight = new int[width];
		for (int c = 0; c < width; c++) {
			for (int r = height - 1; r >= 0; r--) {
				if (grid[c][r]) {
					colHeight[c] = r+1;
					break;
				}
			}
		}
		return colHeight;
	}


	/**
	 Given a piece and an x, returns the y value where the piece would come to rest
	 if it were dropped straight down at that x.

	 <p>
	 Implementation: use the skirt and the col heights to compute this fast -- O(skirt length).
	 */
	public int dropHeight(Piece piece, int x) {
		int[] skirt = piece.getSkirt();
		int finalY = 0;

		if (x < 0 || x + skirt.length > width) {
			throw new RuntimeException("Error: Out of bound");
		}

		for(int i = x; i < x + skirt.length; i++) {
			int currentY = getColumnHeight(i) - skirt[i-x];
			if (currentY > finalY) finalY = currentY;
		}
		return finalY;
	}


	/**
	 Returns the height of the given column --
	 i.e. the y value of the highest block + 1.
	 The height is 0 if the column contains no blocks.
	 */
	public int getColumnHeight(int x) {
		return colHeight[x];
	}


	/**
	 Returns the number of filled blocks in
	 the given row.
	 */
	public int getRowWidth(int y) {
		return rowWidth[y];
	}


	/**
	 Returns true if the given block is filled in the board.
	 Blocks outside of the valid width/height area
	 always return true.
	 */
	public boolean getGrid(int x, int y) {
		if (x < 0 || y < 0 || x > width || y > height) return true;
		return grid[x][y];
	}

	public static final int PLACE_OK = 0;
	public static final int PLACE_ROW_FILLED = 1;
	public static final int PLACE_OUT_BOUNDS = 2;
	public static final int PLACE_BAD = 3;


	/**
	 Attempts to add the body of a piece to the board.
	 Copies the piece blocks into the board grid.
	 Returns PLACE_OK for a regular placement, or PLACE_ROW_FILLED
	 for a regular placement that causes at least one row to be filled.

	 <p>Error cases:
	 A placement may fail in two ways. First, if part of the piece may falls out
	 of bounds of the board, PLACE_OUT_BOUNDS is returned.
	 Or the placement may collide with existing blocks in the grid in which case PLACE_BAD is returned.
	 In both error cases, the board may be left in an invalid state.
	 The client can use undo(), to recover the valid, pre-place state.
	 */
	public int place(Piece piece, int x, int y) {
		// flag !committed problem
		if (!committed) throw new RuntimeException("place commit problem");

		if (!isInBound(piece, x, y)) return PLACE_OUT_BOUNDS;

		if (!isValidPlacement(piece, x, y)) return PLACE_BAD;

		// Back up before modifying the original grid.
		//updateBackup();
		boolean hasFullRow = setPiece(piece, x, y);
		sanityCheck();
		committed = false;

		if (hasFullRow) return PLACE_ROW_FILLED;
		return PLACE_OK;
	}

	/**
	 Deletes rows that are filled all the way across, moving
	 things above down. Returns the number of rows cleared.
	 */
	public int clearRows() {
		//updateBackup(); // Back up before modifying the original grid.

		int rowsCleared = 0;
		for (int y = 0; y < maxHeight; y++) {
			while (isFullRow(y)) {
				System.out.println("JASON; this y is gonna get killed: " + y);
				shiftRows(y);
				rowsCleared++;
			}
		}

		sanityCheck();
		committed = false;
		return rowsCleared;
	}

	private boolean isFullRow(int row) {
		if (rowWidth[row] == width) return true;
		return false;
	}

	private void shiftRows(int currentY) {
		for (int y = currentY; y < maxHeight; y++) {
			for (int x = 0; x < width; x++) {
				grid[x][y] = grid[x][y+1];
				rowWidth[y] = rowWidth[y+1];
			}
		}

		// after shifting one row, fill in the top row with all false
		for (int x1 = 0; x1 < width; x1++) {
			grid[x1][height-1] = false;
			rowWidth[height-1] = 0;
		}
		colHeight = computeColHeight(); // Need to compute colHeight from scratch in case there is a "hole" below the shifted rows.
		maxHeight--;
	}



	/**
	 Reverts the board to its state before up to one place
	 and one clearRows();
	 If the conditions for undo() are not met, such as
	 calling undo() twice in a row, then the second undo() does nothing.
	 See the overview docs.
	 */
	public void undo() {
		if (!committed) {
			for (int i = 0; i < gridCopy.length; i++) {
				System.arraycopy(gridCopy[i], 0, grid[i], 0, gridCopy[i].length);
			}

			System.arraycopy(rowWidthCopy, 0, rowWidth, 0, rowWidthCopy.length);
			System.arraycopy(colHeightCopy, 0, colHeight, 0, colHeightCopy.length);
		}
		maxHeight = maxHeightCopy;

		sanityCheck();
		committed = true;
	}


	/**
	 Puts the board in the committed state.
	 */
	public void commit() {
		committed = true;
		updateBackup();
	}


	/*
	 Renders the board state as a big String, suitable for printing.
	 This is the sort of print-obj-state utility that can help see complex
	 state change over time.
	 (provided debugging utility)
	 */
	public String toString() {
		StringBuilder buff = new StringBuilder();
		for (int y = height-1; y>=0; y--) {
			buff.append('|');
			for (int x=0; x<width; x++) {
				if (getGrid(x,y)) buff.append('+');
				else buff.append(' ');
			}
			buff.append("|\n");
		}
		for (int x=0; x<width+2; x++) buff.append('-');
		return(buff.toString());
	}

	/**
	 Check if (x,y) is within the constraint of the board's width and height,
	 and if placing a piece at (x,y) will cause any part of the piece to go out of bound
	 */
	private boolean isInBound(Piece piece, int x, int y) {
		if (x < 0 || y < 0 || x > width || y > height) return false;;

		int pieceWidth = piece.getWidth();
		int pieceHeight = piece.getHeight();

		if (pieceWidth + x > width || pieceHeight + y > height) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 Check if placing a piece at (x,y) collides with any pre-occupied cells
	 */
	private boolean isValidPlacement(Piece piece, int x, int y) {
		for (TPoint point : piece.getBody()) {
			if (grid[x + point.x][y + point.y]) return false;
		}
		return true;
	}

	/**
	 * Place piece into the grid with its origin at (x,y).
	 * Also update colHeight, rowWidth arrays and maxHeight accordingly.
	 * Modify hasFullRow boolean if a row is now filled.
	 * Returns true if setting this piece results in a full row.
	 * False if setting this piece does not result in a full row.
	 */
	private boolean setPiece(Piece piece, int x, int y) {
		boolean hasFullRow = false;

		for (TPoint point : piece.getBody()) {
			int xPosition = x + point.x;
			int yPosition = y + point.y;

			grid[xPosition][yPosition] = true;

			// Update rowWidth array and check if row is full
			rowWidth[yPosition]++;
			if (rowWidth[yPosition] == width) {
				hasFullRow = true;
			}

			// Update colHeight array
			if (yPosition + 1 > colHeight[xPosition]) colHeight[xPosition] = yPosition + 1;

			// Update maxHeight
			if (colHeight[xPosition] > maxHeight) {
				maxHeight = colHeight[xPosition];
			}
		}
		return hasFullRow;
	}

	private void updateBackup() {
		for (int i = 0; i < grid.length; i++) {
			System.arraycopy(grid[i], 0, gridCopy[i], 0, grid[i].length);
		}

		System.arraycopy(rowWidth, 0, rowWidthCopy, 0, rowWidth.length);
		System.arraycopy(colHeight, 0, colHeightCopy, 0, colHeight.length);
		maxHeightCopy = maxHeight;
	}
}