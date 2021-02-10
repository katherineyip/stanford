package assign1;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

public class TetrisGridTest {
	
	// Provided simple clearRows() test
	// width 2, height 3 grid
	@Test
	public void testClear1() {
		boolean[][] before =
		{	
			{true, true, false, },
			{false, true, true, }
		};
		
		boolean[][] after =
		{	
			{true, false, false},
			{false, true, false}
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClearRows_SingleCellTrue() {
		boolean[][] before =
		{	
			{true}
		};
		
		boolean[][] after =
		{	
			{false}
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClearRows_SingleCellFalse() {
		boolean[][] before =
		{	
			{false}
		};
		
		boolean[][] after =
		{	
			{false}
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClearRows_singleColumnGrid() {
		boolean[][] before =
		{	
			{true, true, false, }
		};
		
		boolean[][] after =
		{	
			{false, false, false, },
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClearRows_singleRowGrid() {
		boolean[][] before =
		{	
			{false, },
			{true, },
			{true, }
		};
		
		boolean[][] after =
		{	
			{false, },
			{true, },
			{true, }
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClearRows_singleRowGridAllTrue() {
		boolean[][] before =
		{	
			{true, },
			{true, },
			{true, }
		};
		
		boolean[][] after =
		{	
			{false, },
			{false, },
			{false, }
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClearRows_singleRowGridAllFalse() {
		boolean[][] before =
		{	
			{false, },
			{false, },
			{false, }
		};
		
		boolean[][] after =
		{	
			{false, },
			{false, },
			{false, }
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	// width 4, height 3 grid
	@Test
	public void testClearRows_AllTrueGrid() {
		boolean[][] before =
		{	
			{true, true, true, },
			{true, true, true, },
			{true, true, true, },
			{true, true, true, },
		};
		
		boolean[][] after =
		{	
			{false, false, false, },
			{false, false, false, },
			{false, false, false, },
			{false, false, false, },
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClearRows_TrueOnTop() {
		boolean[][] before =
		{	
			{false, true, true, },
			{false, true, true, },
			{true, false, true, },
			{true, true, true, },
		};
		
		boolean[][] after =
		{	
			{false, true, false, },
			{false, true, false, },
			{true, false, false, },
			{true, true, false, },
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	// width 4, height 3 grid
	@Test
	public void testClearRows_TrueOnBottom() {
		boolean[][] before =
		{	
			{true, true, false, },
			{true, true, true, },
			{true, false, true, },
			{true, true, true, },
		};
		
		boolean[][] after =
		{	
			{true, false, false, },
			{true, true, false, },
			{false, true, false, },
			{true, true, false, },
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
	@Test
	public void testClearRows_NoChange() {
		boolean[][] before =
		{	
			{true, true, false, },
			{false, true, true, },
			{true, false, true, },
			{true, true, true, },
		};
		
		boolean[][] after =
		{	
			{true, true, false, },
			{false, true, true, },
			{true, false, true, },
			{true, true, true, },
		};
		
		TetrisGrid tetris = new TetrisGrid(before);
		tetris.clearRows();

		assertTrue( Arrays.deepEquals(after, tetris.getGrid()) );
	}
	
}
