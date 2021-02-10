package edu.stanford.cs108.tetris;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.*;

/*
  Unit test for Piece class -- starter shell.
 */
public class PieceTest {
	// You can create data to be used in the your
	// test cases like this. For each run of a test method,
	// a new PieceTest object is created and setUp() is called automatically by JUnit.
	// For example, the code below sets up some pyramid and s pieces in instance variables
	// that can be used in tests.
	
	// All Standard Pieces
	private Piece[] pieces;

	// Stick Piece
	private Piece stick1, stick2, stick3, stick4;
	
	// L2 Piece
	private Piece l2_1, l2_2, l2_3, l2_4;
	
	// S1 Piece
	private Piece s1_1, s1_rotated;
	
	// S2 Piece
	private Piece s2_1, s2_2, s2_3, s2_4;
	
	// Square Piece
	private Piece sq1, sq2, sq3, sq4;
	
	// Pyramid Piece
	private Piece pyr1, pyr2, pyr3, pyr4;
	

	@Before
	public void setUp() throws Exception {
		
		pieces = Piece.getPieces();
		
		// Stick Piece
		stick1 = new Piece(Piece.STICK_STR);
		stick2 = stick1.computeNextRotation();
		stick3 = stick2.computeNextRotation();
		stick4 = stick3.computeNextRotation();
		
		// L2 Piece
		l2_1 = new Piece(Piece.L2_STR);
		l2_2 = l2_1.computeNextRotation();
		l2_3 = l2_2.computeNextRotation();
		l2_4 = l2_3.computeNextRotation();
		
		// S1 Piece
		s1_1 = new Piece(Piece.S1_STR);
		s1_rotated = s1_1.computeNextRotation();
		
		// S2 Piece
		s2_1 = new Piece(Piece.S2_STR);
		s2_2 = s2_1.computeNextRotation();
		s2_3 = s2_2.computeNextRotation();
		s2_4 = s2_3.computeNextRotation();
		
		// Square Piece
		sq1 = new Piece(Piece.SQUARE_STR);
		sq2 = sq1.computeNextRotation();
		sq3 = sq2.computeNextRotation();
		sq4 = sq3.computeNextRotation();
		
		
		// Pyramid Piece
		pyr1 = new Piece(Piece.PYRAMID_STR);
		pyr2 = pyr1.computeNextRotation();
		pyr3 = pyr2.computeNextRotation();
		pyr4 = pyr3.computeNextRotation();
		
	}
	
	// Test width, height, and skirt of initial pieces
	@Test
	public void testInitialWidth() {
		assertEquals(1, stick1.getWidth());
		assertEquals(2, l2_1.getWidth());
		assertEquals(3, s1_1.getWidth());
		assertEquals(3, s2_1.getWidth());
		assertEquals(2, sq1.getWidth());
		assertEquals(3, pyr1.getWidth());
	}
	
	@Test
	public void testInitialHeight() {
		assertEquals(4, stick1.getHeight());
		assertEquals(3, l2_1.getHeight());
		assertEquals(2, s1_1.getHeight());
		assertEquals(2, s2_1.getHeight());
		assertEquals(2, sq1.getHeight());
		assertEquals(2, pyr1.getHeight());
	}
	
	@Test
	public void testInitialSkirt() {
		assertTrue(Arrays.equals(new int[] {0}, stick1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0}, l2_1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0, 1}, s1_1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0}, sq1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyr1.getSkirt()));
		
		Piece l = new Piece(Piece.STICK_STR);
		assertEquals(1, l.getWidth());
		assertEquals(4, l.getHeight());
	}
	
	// Make sure width, height, and skirt are correct after one rotation (90 degree counter-clockwise)
	@Test
	public void testOneRotation() {
		// Stick Piece
		assertEquals(4, stick2.getWidth());
		assertEquals(1, stick2.getHeight());
		assertTrue(Arrays.equals(new int[] {0, 0, 0, 0}, stick2.getSkirt()));
		
		// L2 Piece
		assertEquals(3, l2_2.getWidth());
		assertEquals(2, l2_2.getHeight());
		assertTrue(Arrays.equals(new int[] {1, 1, 0}, l2_2.getSkirt()));
		
		// S2 Piece
		assertEquals(2, s2_2.getWidth());
		assertEquals(3, s2_2.getHeight());
		assertTrue(Arrays.equals(new int[] {0, 1}, s2_2.getSkirt()));
		
		// Square Piece
		assertEquals(2, sq2.getWidth());
		assertEquals(2, sq2.getHeight());
		assertTrue(Arrays.equals(new int[] {0, 0}, sq2.getSkirt()));
		
		// Pyramid Piece
		assertEquals(2, pyr2.getWidth());
		assertEquals(3, pyr2.getHeight());
		assertTrue(Arrays.equals(new int[] {1, 0}, pyr2.getSkirt()));
	}
	
	// Make sure width, height, and skirt are correct after one rotation (90 degree counter-clockwise)
	@Test
	public void testTwoRotation() {
		// Stick Piece
		assertEquals(1, stick3.getWidth());
		assertEquals(4, stick3.getHeight());
		assertTrue(Arrays.equals(new int[] {0}, stick3.getSkirt()));
		
		// L2 Piece
		assertEquals(2, l2_3.getWidth());
		assertEquals(3, l2_3.getHeight());
		assertTrue(Arrays.equals(new int[] {0, 2}, l2_3.getSkirt()));
		
		// S2 Piece
		assertEquals(3, s2_3.getWidth());
		assertEquals(2, s2_3.getHeight());
		assertTrue(Arrays.equals(new int[] {1, 0, 0}, s2_3.getSkirt()));
		
		// Square Piece
		assertEquals(2, sq3.getWidth());
		assertEquals(2, sq3.getHeight());
		assertTrue(Arrays.equals(new int[] {0, 0}, sq3.getSkirt()));
		
		// Pyramid Piece
		assertEquals(3, pyr3.getWidth());
		assertEquals(2, pyr3.getHeight());
		assertTrue(Arrays.equals(new int[] {1, 0, 1}, pyr3.getSkirt()));
	}
	
	// Make sure the piece rotates back to the starting piece
	@Test
	public void testFullRotation() {
		// ==== Stick Piece ====
		assertEquals(stick1.getWidth(), stick4.computeNextRotation().getWidth());
		assertEquals(stick1.getHeight(), stick4.computeNextRotation().getHeight());
		assertTrue(Arrays.equals(stick1.getSkirt(), stick4.computeNextRotation().getSkirt()));
		
		// ==== L2 Piece ====
		assertEquals(l2_1.getWidth(), l2_4.computeNextRotation().getWidth());
		assertEquals(l2_1.getHeight(), l2_4.computeNextRotation().getHeight());
		assertTrue(Arrays.equals(l2_1.getSkirt(), l2_4.computeNextRotation().getSkirt()));
		
		// ==== S2 Piece ====
		assertEquals(s2_1.getWidth(), s2_4.computeNextRotation().getWidth());
		assertEquals(s2_1.getHeight(), s2_4.computeNextRotation().getHeight());
		assertTrue(Arrays.equals(s2_1.getSkirt(), s2_4.computeNextRotation().getSkirt()));
		
		// ==== Square Piece ====
		assertEquals(sq1.getWidth(), sq4.computeNextRotation().getWidth());
		assertEquals(sq1.getHeight(), sq4.computeNextRotation().getHeight());
		assertTrue(Arrays.equals(sq1.getSkirt(), sq4.computeNextRotation().getSkirt()));

		// ==== Pyramid Piece ====
		assertEquals(pyr1.getWidth(), pyr4.computeNextRotation().getWidth());
		assertEquals(pyr1.getHeight(), pyr4.computeNextRotation().getHeight());
		assertTrue(Arrays.equals(pyr1.getSkirt(), pyr4.computeNextRotation().getSkirt()));
	}
	
	// Sample tests from starter files
	@Test
	public void testSampleSize() {
		// Check size of pyr piece
		assertEquals(3, pyr1.getWidth());
		assertEquals(2, pyr1.getHeight());
		
		// Now try after rotation
		// Effectively we're testing size and rotation code here
		assertEquals(2, pyr2.getWidth());
		assertEquals(3, pyr2.getHeight());
		
		// Now try with some other piece, made a different way
		Piece l = new Piece(Piece.STICK_STR);
		assertEquals(1, l.getWidth());
		assertEquals(4, l.getHeight());
	}
	
	// Test the skirt returned by pieces
	@Test
	public void testSampleSkirt() {
		// ==== STICK piece ====
		assertTrue(Arrays.equals(new int[] {0}, stick1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0, 0, 0}, stick2.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0}, stick3.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0, 0, 0}, stick4.getSkirt()));
		
		// ==== L2 piece ====
		assertTrue(Arrays.equals(new int[] {0, 0}, l2_1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 1, 0}, l2_2.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 2}, l2_3.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, l2_4.getSkirt()));
		
		// ==== S1 piece ====
		assertTrue(Arrays.equals(new int[] {0, 0, 1}, s1_1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0}, s1_rotated.getSkirt()));
		
		// ==== SQUARE piece ====
		assertTrue(Arrays.equals(new int[] {0, 0}, sq1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0}, sq2.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0}, sq3.getSkirt()));
		assertTrue(Arrays.equals(new int[] {0, 0}, sq4.getSkirt()));
	
		// ==== PYRAMID piece ====
		assertTrue(Arrays.equals(new int[] {0, 0, 0}, pyr1.getSkirt()));
		assertTrue(Arrays.equals(new int[] {1, 0, 1}, pyr3.getSkirt()));
	}
	
	// Test equals method
	@Test
	public void testEquals() {
		assertEquals(s1_rotated, s1_rotated);
		assertEquals(stick1, new Piece(Piece.STICK_STR));
		assertEquals(sq1, sq1);
		assertNotEquals(pieces[Piece.SQUARE], pieces[Piece.STICK]);
		assertNotEquals(pieces[Piece.L1], pieces[Piece.L2]);
		assertNotEquals(pieces[Piece.S1], pieces[Piece.S2]);
	}
	
	// Test if getPieces() structure looks correct
	@Test
	public void testGetPiece() {
		assertEquals(7, pieces.length);
	}
	
	@Test
	public void testOneFastRotation() {
		// Compare width of computeNextRotation and fastRotation after ONE rotation
		assertEquals(stick2.getWidth(), pieces[Piece.STICK].fastRotation().getWidth());
		assertEquals(l2_2.getWidth(), pieces[Piece.L2].fastRotation().getWidth());
		assertEquals(s1_rotated.getWidth(), pieces[Piece.S1].fastRotation().getWidth());
		assertEquals(s2_2.getWidth(), pieces[Piece.S2].fastRotation().getWidth());
		assertEquals(sq2.getWidth(), pieces[Piece.SQUARE].fastRotation().getWidth());
		assertEquals(pyr2.getWidth(), pieces[Piece.PYRAMID].fastRotation().getWidth());
		
		// Compare height of computeNextRotation and fastRotation after ONE rotation
		assertEquals(stick2.getHeight(), pieces[Piece.STICK].fastRotation().getHeight());
		assertEquals(l2_2.getHeight(), pieces[Piece.L2].fastRotation().getHeight());
		assertEquals(s1_rotated.getHeight(), pieces[Piece.S1].fastRotation().getHeight());
		assertEquals(s2_2.getHeight(), pieces[Piece.S2].fastRotation().getHeight());
		assertEquals(sq2.getHeight(), pieces[Piece.SQUARE].fastRotation().getHeight());
		assertEquals(pyr2.getHeight(), pieces[Piece.PYRAMID].fastRotation().getHeight());
		
		// Compare skirt of computeNextRotation and fastRotation after ONE rotation
		assertTrue(stick2.equals((pieces[Piece.STICK].fastRotation())));
		assertTrue(s1_rotated.equals((pieces[Piece.S1].fastRotation())));
		assertTrue(s2_2.equals((pieces[Piece.S2].fastRotation())));
		assertTrue(sq2.equals((pieces[Piece.SQUARE].fastRotation())));
		assertTrue(pyr2.equals((pieces[Piece.PYRAMID].fastRotation())));
		assertEquals(stick2, pieces[Piece.STICK].fastRotation());
		assertEquals(s1_rotated, pieces[Piece.S1].fastRotation());
	}
	
	@Test
	public void testTwoFastRotation() {
		// Compare computeNextRotation and fastRotation after TWO rotation
		assertEquals(stick3, pieces[Piece.STICK].fastRotation().fastRotation());
		assertEquals(l2_3, pieces[Piece.L2].fastRotation().fastRotation());
		assertEquals(s2_3, pieces[Piece.S2].fastRotation().fastRotation());
		assertEquals(sq3, pieces[Piece.SQUARE].fastRotation().fastRotation());
		assertEquals(pyr3, pieces[Piece.PYRAMID].fastRotation().fastRotation());
	}
	
	@Test
	public void testThreeFastRotation() {
		// Compare computeNextRotation and fastRotation after THREE rotation
		assertEquals(stick4, pieces[Piece.STICK].fastRotation().fastRotation().fastRotation());
		assertEquals(l2_4, pieces[Piece.L2].fastRotation().fastRotation().fastRotation());
		assertEquals(s2_4, pieces[Piece.S2].fastRotation().fastRotation().fastRotation());
		assertEquals(sq4, pieces[Piece.SQUARE].fastRotation().fastRotation().fastRotation());
		assertEquals(pyr4, pieces[Piece.PYRAMID].fastRotation().fastRotation().fastRotation());
	}
	
	@Test
	public void testFourFastRotation() {
		// Compare computeNextRotation and fastRotation after FOUR rotation
		assertEquals(stick1, pieces[Piece.STICK].fastRotation().fastRotation().fastRotation().fastRotation());
		assertEquals(l2_1, pieces[Piece.L2].fastRotation().fastRotation().fastRotation().fastRotation());
		assertEquals(s2_1, pieces[Piece.S2].fastRotation().fastRotation().fastRotation().fastRotation());
		assertEquals(sq1, pieces[Piece.SQUARE].fastRotation().fastRotation().fastRotation().fastRotation());
		assertEquals(pyr1, pieces[Piece.PYRAMID].fastRotation().fastRotation().fastRotation().fastRotation());
	}

	
}
