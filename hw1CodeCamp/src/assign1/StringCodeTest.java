// StringCodeTest
// Some test code is provided for the early HW1 problems,
// and much is left for you to add.

package assign1;

import static org.junit.Assert.*;
import org.junit.Test;

public class StringCodeTest {
	//
	// blowup
	//
	@Test
	public void testBlowup1() {
		// basic cases
		assertEquals("xxaaaabb", StringCode.blowup("xx3abb"));
		assertEquals("xxxZZZZ", StringCode.blowup("2x3Z"));
	}
	
	@Test
	public void testBlowup2() {
		// digit at end
		assertEquals("axxx", StringCode.blowup("a2x3"));
		
		// digits next to each other
		assertEquals("a33111", StringCode.blowup("a231"));
		
		// try a 0
		assertEquals("aabb", StringCode.blowup("aa0bb"));
	}
	
	@Test
	public void testBlowup3() {
		// weird chars, empty string
		assertEquals("AB&&,- ab", StringCode.blowup("AB&&,- ab"));
		assertEquals("", StringCode.blowup(""));
		
		// string with only digits
		assertEquals("", StringCode.blowup("2"));
		assertEquals("33", StringCode.blowup("23"));
	}
	
	@Test
	public void testBlowupCharOnly() {
		assertEquals("icecream", StringCode.blowup("icecream"));
		assertEquals("ice cream", StringCode.blowup("ice cream"));
		assertEquals(" ice cream", StringCode.blowup(" ice cream"));
		assertEquals(" ", StringCode.blowup(" "));
	}
	
	@Test
	public void testBlowupEmptyString() {
		assertEquals("", StringCode.blowup(""));
	}
	
	@Test
	public void testBlowupDigiOnly() {
		assertEquals("233", StringCode.blowup("123"));
		assertEquals("", StringCode.blowup("1"));
		assertEquals("999999999", StringCode.blowup("99"));
		assertEquals("", StringCode.blowup("000"));
	}
	
	@Test
	public void testBlowupSpacing() {
		assertEquals("     22", StringCode.blowup("4 22"));
		assertEquals("           ", StringCode.blowup("2 4 2 "));
		assertEquals("   2222   ", StringCode.blowup("2 42 "));
		assertEquals(" afdafadfs", StringCode.blowup(" afdafadfs"));
	}
	
	@Test
	public void testBlowupMixString() {
		assertEquals("skuf33jjjjafffff", StringCode.blowup("skuf23ja4f"));
		assertEquals("****", StringCode.blowup("3*"));
		assertEquals("****", StringCode.blowup("3*4"));
	}
	
	@Test
	public void testBlowupAlternativeNumerals() {
		assertEquals("", StringCode.blowup("४"));
		assertEquals("aaaaa", StringCode.blowup("४a"));
	}
	
	//
	// maxRun
	//
	@Test
	public void testRun1() {
		assertEquals(2, StringCode.maxRun("hoopla"));
		assertEquals(3, StringCode.maxRun("hoopllla"));
	}
	
	@Test
	public void testRun2() {
		assertEquals(3, StringCode.maxRun("abbcccddbbbxx"));
		assertEquals(0, StringCode.maxRun(""));
		assertEquals(3, StringCode.maxRun("hhhooppoo"));
	}
	
	@Test
	public void testRun3() {
		// "evolve" technique -- make a series of test cases
		// where each is change from the one above.
		assertEquals(1, StringCode.maxRun("123"));
		assertEquals(2, StringCode.maxRun("1223"));
		assertEquals(2, StringCode.maxRun("112233"));
		assertEquals(3, StringCode.maxRun("1112233"));
	}
	
	@Test
	public void testMaxRunEmptyString() {
		assertEquals(0, StringCode.maxRun(""));
	}
	
	@Test
	public void testMaxRunCharactersOnly() {
		assertEquals(1, StringCode.maxRun("A"));
		assertEquals(1, StringCode.maxRun("Aa"));
		assertEquals(1, StringCode.maxRun("AaA"));
		assertEquals(2, StringCode.maxRun("AAaaAA"));
		assertEquals(2, StringCode.maxRun("AAaaZZ"));
		assertEquals(3, StringCode.maxRun("aabbCCC"));
		assertEquals(4, StringCode.maxRun("aabbCCCC"));
		assertEquals(5, StringCode.maxRun("aabbCCCCC"));
		assertEquals(1, StringCode.maxRun("_"));
		assertEquals(1, StringCode.maxRun("*_@"));
		assertEquals(2, StringCode.maxRun("*@!)@$_@@"));
	}
	
	@Test
	public void testMaxRunMixString() {
		assertEquals(1, StringCode.maxRun("ABCcCcC123"));
	}
	
	@Test
	public void testMaxRunSpace() {
		assertEquals(1, StringCode.maxRun(" "));
		assertEquals(3, StringCode.maxRun("   "));
		assertEquals(2, StringCode.maxRun(" fsd  "));
	}
	
}
