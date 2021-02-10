package edu.stanford.cs108.tetris;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
	
	private Board emptyBoard, board1, board2, board3;
	private Piece pyramid, stick, square, s1, s2;
	
	@Before
	public void setUp() throws Exception {
		board1 = new Board(3, 6);
		board2 = new Board(3, 6);
		board3 = new Board(6, 6);
		
		pyramid = new Piece(Piece.PYRAMID_STR);
		stick = new Piece(Piece.STICK_STR);
		square = new Piece(Piece.SQUARE_STR);
		s1 = new Piece(Piece.S1_STR);
		s2 = new Piece(Piece.S2_STR);
	}
	
	@Test
	public void testEmptyBoardMaxHeight() {
		emptyBoard = new Board(3, 6);
		assertEquals(0, emptyBoard.getMaxHeight());
	}
	
	@Test
	public void testPlace() {
		/* Placing a pyramid. Expected board:
		5|   |
		4|   |
		3|   |
		2|   |
		1| + |
		0|+++|
		 -----
		  012
		*/
		assertEquals(Board.PLACE_ROW_FILLED, board1.place(pyramid, 0, 0));
		System.out.println(board1.toString());
		assertTrue(board1.getGrid(0, 0));
		assertTrue(board1.getGrid(1, 0));
		assertTrue(board1.getGrid(2, 0));
		assertEquals(2, board1.getMaxHeight());
		assertEquals(1, board1.getColumnHeight(0));
		assertEquals(2, board1.getColumnHeight(1));
		assertEquals(1, board1.getColumnHeight(2));
		assertEquals(3, board1.getRowWidth(0));
		assertEquals(1, board1.getRowWidth(1));		
		
		/* Placing another pyramid. Expected board:
		5|   |
		4|   |
		3| + |
		2|+++|
		1| + |
		0|+++|
		 -----
		  012
		*/
		assertEquals(2, board1.dropHeight(pyramid, 0));
		board1.commit();
		board1.place(pyramid, 0, board1.dropHeight(pyramid, 0));
		System.out.println(board1.toString());
		assertTrue(board1.getGrid(0, 0));
		assertTrue(board1.getGrid(1, 0));
		assertTrue(board1.getGrid(2, 0));
		assertTrue(board1.getGrid(1, 1));
		assertTrue(board1.getGrid(0, 2));
		assertTrue(board1.getGrid(1, 2));
		assertTrue(board1.getGrid(2, 2));
		assertTrue(board1.getGrid(1, 3));
		assertFalse(board1.getGrid(0, 1));
		assertFalse(board1.getGrid(2, 1));
		assertFalse(board1.getGrid(0, 3));
		assertFalse(board1.getGrid(2, 3));
		assertEquals(4, board1.getMaxHeight());
		assertEquals(3, board1.getColumnHeight(0));
		assertEquals(4, board1.getColumnHeight(1));
		assertEquals(3, board1.getColumnHeight(2));
		assertEquals(3, board1.getRowWidth(0));
		assertEquals(1, board1.getRowWidth(1));
		assertEquals(3, board1.getRowWidth(2));
		assertEquals(1, board1.getRowWidth(3));
		
		// Test invalid place()
		board1.commit();
		System.out.println(board1.toString());
		assertEquals(Board.PLACE_BAD, board1.place(stick, 0, 2));
		assertEquals(Board.PLACE_OUT_BOUNDS, board1.place(stick, 1, 4));
		assertEquals(4, board1.getMaxHeight());
	}
	
	// Test error when placing before committing
	@Test(expected = RuntimeException.class)
	public void testCommitProblem() {
		try {
			// Placing a pyramid
			board1.place(pyramid, 0, 0);

			// Placing another pyramid before committing
			board1.place(pyramid, 0, board1.dropHeight(pyramid, 0));
		}
		catch(RuntimeException error) {
			String message = "place commit problem";
		    assertEquals(message, error.getMessage());
		    throw error;
		}
	}
	
	@Test
	public void testClear() {
		/* Placing a pyramid. Expected board:
		5|   |
		4|   |
		3|   |
		2|   |
		1| + |
		0|+++|
		 -----
		  012
		*/
		assertEquals(Board.PLACE_ROW_FILLED, board2.place(pyramid, 0, 0));
		board2.commit();
		System.out.println(board2.toString());
		
		/* Cleared full row
		5|   |
		4|   |
		3|   |
		2|   |
		1|   |
		0| + |
		 -----
		  012
		*/
		assertEquals(1, board2.clearRows());
		System.out.println(board2.toString());
		assertTrue(board2.getGrid(1, 0));
		assertFalse(board2.getGrid(0, 0));
		assertFalse(board2.getGrid(2, 0));
		assertEquals(1, board2.getColumnHeight(1));
		assertEquals(1, board2.getMaxHeight());
		assertEquals(1, board2.getRowWidth(0));
		
		/* Undo last change (clearRow)
		5|   |
		4|   |
		3|   |
		2|   |
		1| + |
		0|+++|
		 -----
		  012
		*/
		board2.undo();
		System.out.println(board2.toString());
		assertEquals(1, board2.getColumnHeight(0));
		assertEquals(2, board2.getColumnHeight(1));
		assertEquals(1, board2.getColumnHeight(2));
		assertEquals(2, board2.getMaxHeight());
		assertEquals(3, board2.getRowWidth(0));
		assertEquals(1, board2.getRowWidth(1));
	}
	
	@Test
	public void testBiggerBoard() {
		/* Placing a pyramid. Expected board:
		5|      |
		4|      |
		3|      |
		2|      |
		1|    + |
		0|   +++|
		 --------
		  012345
		*/
		assertEquals(Board.PLACE_OK, board3.place(pyramid, 3, 0));
		System.out.println(board3.toString());
		board3.commit();
		assertEquals(Board.PLACE_BAD, board3.place(stick, 4, 1));
		assertEquals(Board.PLACE_OUT_BOUNDS, board3.place(stick, 6, 1));
		assertEquals(Board.PLACE_OUT_BOUNDS, board3.place(stick, 0, 4));
		
		/* Placing a stick piece. Expected board:
		5|      |
		4|     +|
		3|     +|
		2|     +|
		1|    ++|
		0|   +++|
		 --------
		  012345
		*/
		assertEquals(Board.PLACE_OK, board3.place(stick, 5, 1));
		assertEquals(5, board3.getMaxHeight());
		System.out.println(board3.toString());
		
		/* Remove the stick piece. Expected board:
		5|      |
		4|      |
		3|      |
		2|      |
		1|    + |
		0|   +++|
		 --------
		  012345
		*/
		board3.undo();
		board3.undo(); // Make sure nothing happens when undo() is called twice in a row.
		System.out.println(board3.toString());
		
		/* Place a square piece. Expected board:
		5|      |
		4|      |
		3|      |
		2|      |
		1|++  + |
		0|++ +++|
		 --------
		  012345
		*/
		assertEquals(Board.PLACE_OK, board3.place(square, 0, 0));
		assertEquals(2, board3.getMaxHeight());
		System.out.println(board3.toString());
		board3.commit();
		
		/* Place a stick piece. Expected board:
		5|      |
		4|      |
		3|  +   |
		2|  +   |
		1|+++ + |
		0|++++++|
		 --------
		  012345
		*/
		assertEquals(Board.PLACE_ROW_FILLED, board3.place(stick, 2, 0));
		System.out.println(board3.toString());
		board3.commit();
		
		/* Clear full row. Expected board:
		5|      |
		4|      |
		3|      |
		2|  +   |
		1|  +   |
		0|+++ + |
		 --------
		  012345
		*/
		board3.clearRows();
		board3.commit();
		System.out.println(board3.toString());
		
		/* Place a stick piece. Expected board:
		5|      |
		4|      |
		3|      |
		2|  +++ |
		1|  +++ |
		0|+++ + |
		 --------
		  012345
		*/
		assertEquals(Board.PLACE_OK, board3.place(square, 3, 1));
		assertEquals(3, board3.getMaxHeight());
		System.out.println(board3.toString());
		board3.commit();
		
		/* Place a stick piece. Expected board:
		5|      |
		4|      |
		3|     +|
		2|  ++++|
		1|  ++++|
		0|+++ ++|
		 --------
		  012345
		*/
		assertEquals(Board.PLACE_OK, board3.place(stick, 5, 0));
		assertEquals(4, board3.getMaxHeight());
		System.out.println(board3.toString());
		board3.commit();
		
		/* Place a square piece. Expected board:
		5|      |
		4|      |
		3|     +|
		2|++++++|
		1|++++++|
		0|+++ ++|
		 --------
		  012345
		*/
		assertEquals(Board.PLACE_ROW_FILLED, board3.place(square, 0, 1));
		assertEquals(3, board3.getColumnHeight(3));
		assertEquals(3, board3.getColumnHeight(4));
		assertEquals(4, board3.getColumnHeight(5));
		assertEquals(4, board3.getMaxHeight());
		System.out.println(board3.toString());
		board3.commit();
		
		/* Test clearing multiple filled rows. Expected board:
		5|      |
		4|      |
		3|      |
		2|      |
		1|     +|
		0|+++ ++|
		 --------
		  012345
		*/
		board3.clearRows();
		assertEquals(0, board3.getColumnHeight(3));
		assertEquals(2, board3.getColumnHeight(5));
		System.out.println(board3.toString());
		
		/* Undo clear row. Expected board:
		5|      |
		4|      |
		3|     +|
		2|++++++|
		1|++++++|
		0|+++ ++|
		 --------
		  012345
		*/
		board3.undo();
		assertEquals(3, board3.getColumnHeight(3));
		System.out.println(board3.toString());
	}
	
	@Test
	public void testDropHeight() {
		/* Placing a pyramid. Expected board:
		5|      |
		4|      |
		3|      |
		2|      |
		1|    + |
		0|   +++|
		 --------
		  012345
		*/
		assertEquals(Board.PLACE_OK, board3.place(pyramid, 3, 0));
		assertEquals(2, board3.getColumnHeight(4));
		assertEquals(1, board3.getColumnHeight(3));
		assertEquals(2, board3.dropHeight(pyramid, 2));
		assertEquals(0, board3.dropHeight(pyramid, 0));
		assertEquals(0, board3.dropHeight(s1, 1));
		assertEquals(0, board3.dropHeight(s1, 0));
		
		/* Placing a s2 at x = 0. Expected board:
		5|      |
		4|      |
		3|      |
		2|      |
		1|ss  + |
		0| ss+++|
		 --------
		  012345
		*/
		assertEquals(0, board3.dropHeight(s2, 0));
		
		/* Placing a s2 at x = 2. Expected board:
		5|      |
		4|      |
		3|   ss |
		2|    ss|
		1|    + |
		0|   +++|
		 --------
		  012345
		*/
		assertEquals(2, board3.dropHeight(s2, 2));
		
		/* Placing a s2 at x = 3. Expected board:
		5|      |
		4|      |
		3|   ss |
		2|    ss|
		1|    + |
		0|   +++|
		 --------
		  012345
		*/
		assertEquals(2, board3.dropHeight(s2, 3));
	}

}
