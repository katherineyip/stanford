// Test cases for CharGrid -- a few basic tests are provided.
package assign1;

import static org.junit.Assert.*;
import org.junit.Test;

public class CharGridTest {
	
	@Test
	public void testCharArea1() {
		char[][] grid = new char[][] {
				{'a', 'y', ' '},
				{'x', 'a', 'z'},
			};
		
		
		CharGrid cg = new CharGrid(grid);
				
		assertEquals(4, cg.charArea('a'));
		assertEquals(1, cg.charArea('z'));
		assertEquals(1, cg.charArea('y'));
	}
	
	
	@Test
	public void testCharArea2() {
		char[][] grid = new char[][] {
				{'c', 'a', ' '},
				{'b', ' ', 'b'},
				{' ', ' ', 'a'}
			};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(6, cg.charArea('a'));
		assertEquals(3, cg.charArea('b'));
		assertEquals(1, cg.charArea('c'));
		assertEquals(9, cg.charArea(' '));
	}
	
	@Test
	public void testCharAreaSpaceGrid() {
		char[][] grid = new char[][] {
				{' ', ' ', ' '},
				{' ', ' ', ' '},
				{' ', ' ', ' '}
			};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(0, cg.charArea('a'));
		assertEquals(9, cg.charArea(' '));
	}
	
	@Test
	public void testCharAreaSingleCharacterGrid() {
		char[][] grid = new char[][] {
			{' '}
		};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(0, cg.charArea('a'));
		assertEquals(1, cg.charArea(' '));
	}
	
	@Test
	public void testCharAreaSingleRowGrid() {
		char[][] grid = new char[][] {
			{'a', 'a', 'b'}
		};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(2, cg.charArea('a'));
		assertEquals(1, cg.charArea('b'));
	}
	
	@Test
	public void testCharAreaSingleColGrid() {
		char[][] grid = new char[][] {
			{'a'},
			{'b'},
			{'a'}
		};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(3, cg.charArea('a'));
		assertEquals(1, cg.charArea('b'));
		assertEquals(0, cg.charArea('B'));
	}

	@Test
	public void testCharAreaEmptyGrid() {
		char[][] grid = new char[][] {};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(0, cg.charArea('a'));
	}
	
	@Test
	public void testCharAreaEmptyColGrid() {
		char[][] grid = new char[][] {
			{},
			{},
			{}
		};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(0, cg.charArea('a'));
		assertEquals(0, cg.charArea('b'));
		assertEquals(0, cg.charArea(' '));
	}
	
	// Tests for countPlus
	@Test
	public void testcountPlusEmptyGrid() {
		char[][] grid = new char[][] {};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(0, cg.countPlus());
	}
	
	@Test
	public void testcountPlusSingleCellGrid() {
		char[][] grid = new char[][] {
			{' '}
		};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(0, cg.countPlus());
	}
	
	@Test
	public void testcountPlusSingleRowGrid() {
		char[][] grid = new char[][] {
			{' ', ' '}
		};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(0, cg.countPlus());
	}
	
	@Test
	public void testcountPlusSingleColGrid() {
		char[][] grid = new char[][] {
			{' '},
			{' '},
			{' '},
			{' '}
		};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(0, cg.countPlus());
	}
	
	@Test
	public void testcountPlus2x2() {
		char[][] grid = new char[][] {
			{'x', 'x'},
			{'x', 'x'},
			{'x', 'x'},
			{'x', 'x'}
		};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(0, cg.countPlus());
	}
	
	@Test
	public void testcountPlus3x3full() {
		char[][] grid = new char[][] {
			{'x', 'x', 'x'},
			{'x', 'x', 'x'},
			{'x', 'x', 'x'}
		};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(1, cg.countPlus());
	}
	
	@Test
	public void testcountPlus3x4full() {
		char[][] grid = new char[][] {
			{'x', 'x', 'x'},
			{'x', 'x', 'x'},
			{'x', 'x', 'x'},
			{'x', 'x', 'x'}
		};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(0, cg.countPlus());
	}
	
	@Test
	public void testcountPlus3x4_noPlus() {
		char[][] grid = new char[][] {
			{' ', 'x' , 'x'},
			{' ', 'x' , ' '},
			{'x', 'x' , 'x'},
			{' ', 'x' , 'x'}
		};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(0, cg.countPlus());
	}
	
	@Test
	public void testcount3x4_withPlus() {
		char[][] grid = new char[][] {
			{' ', 'y' , 'x'},
			{' ', 'x' , ' '},
			{'x', 'x' , 'x'},
			{' ', 'x' , 'x'}
		};
		
		CharGrid cg = new CharGrid(grid);
		
		assertEquals(1, cg.countPlus());
	}
	
	@Test
	public void testcountPlus4x6_withPlus() {
		char[][] grid1 = new char[][] {
			{' ', 'x' , 'x' , 'x'},
			{' ', 'y' , 'x' , 'x'},
			{'y', 'y' , 'y' , 'x'},
			{' ', 'y' , 'b' , 'x'},
			{' ', 'b' , 'b' , 'b'},
			{' ', 'x' , 'b' , '0'}
		};
		
		CharGrid cg1 = new CharGrid(grid1);
		assertEquals(2, cg1.countPlus());
		
		char[][] grid2 = new char[][] {
			{' ', 'x' , 'x' , 'x'},
			{' ', 'y' , 'x' , 'x'},
			{'y', 'y' , 'y' , 'y'},
			{' ', 'y' , 'b' , 'x'},
			{' ', 'b' , 'b' , 'b'},
			{' ', 'x' , 'b' , '0'}
		};
		CharGrid cg2 = new CharGrid(grid2);
		assertEquals(1, cg2.countPlus());
		
		char[][] grid3 = new char[][] {
			{' ', 'x' , 'x' , 'x'},
			{' ', 'x' , 'x' , 'x'},
			{'y', 'y' , 'x' , 'y'},
			{' ', 'b' , 'b' , 'x'},
			{'b', 'b' , 'b' , 'b'},
			{' ', 'b' , 'b' , '0'}
		};
		CharGrid cg3 = new CharGrid(grid3);
		assertEquals(1, cg3.countPlus());
	}
	
	@Test
	public void testcountPlusLargeGrid() {
		char[][] grid = new char[][] {
			{' ', ' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', 'p', ' ', ' ', ' ', ' ', 'x', ' '},
			{'p', 'p', 'p', 'p', 'p', ' ', 'x', 'x', 'x'},
			{' ', ' ', 'p', ' ', ' ', 'y', ' ', 'x', ' '},
			{' ', ' ', 'p', ' ', 'y', 'y', 'y', ' ', ' '},
			{'z', 'z', 'z', 'z', 'z', 'y', 'z', 'z', 'z'},
			{' ', ' ', 'x', 'x', ' ', 'y', ' ', ' ', ' '}
		};
		
		CharGrid cg = new CharGrid(grid);

		assertEquals(2, cg.countPlus());
	}
	
}
