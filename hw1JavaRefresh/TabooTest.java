// TabooTest.java
// Taboo class tests -- nothing provided.
package assign1;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class TabooTest {

	@Test
	public void testTaboo1() {
		Taboo<String> taboo = new Taboo<String>(Arrays.asList("a", "c", "a", "b"));
		List<String> list = new ArrayList<String>(Arrays.asList("a", "c", "b", "x", "c", "a"));
		taboo.reduce(list);
		
		assertEquals(Arrays.asList("a", "x", "c"), list);
	}
	
	@Test
	public void testTaboo2() {
		Taboo<String> taboo = new Taboo<String>(Arrays.asList("a", null, "c", "a", "b"));
		List<String> list = new ArrayList<String>(Arrays.asList("a", "c", "b", "x", "c", "a"));
		taboo.reduce(list);
		
		assertEquals(Arrays.asList("a", "c", "b", "x", "c"), list);
	}
	
	@Test
	public void testTaboo3() {
		Taboo<String> taboo = new Taboo<String>(Arrays.asList("a", null, "c", null, "a", null, "b"));
		List<String> list = new ArrayList<String>(Arrays.asList("b", "a", "c", "z"));
		taboo.reduce(list);
		
		assertEquals(Arrays.asList("b", "a", "c", "z"), list);
	}
	
	@Test
	public void testTaboo4() {
		Taboo<String> taboo = new Taboo<String>(Arrays.asList("a", "b", null, "c", "d"));
		List<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
		taboo.reduce(list);
		
		assertEquals(Arrays.asList("a", "c"), list);
	}
	
	@Test
	public void testTaboo5() {
		Taboo<String> taboo = new Taboo<String>(Arrays.asList("a", "b", "c", "d"));
		List<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
		taboo.reduce(list);
		
		assertEquals(Arrays.asList("a", "c"), list);
	}
	
	@Test
	public void testTaboo6() {
		Taboo<String> taboo = new Taboo<String>(Arrays.asList("a", "b", null, "a", "c", null, "a", "d"));
		List<String> list = new ArrayList<String>(Arrays.asList("a", "b", "c", "d"));
		taboo.reduce(list);
		
		assertEquals(Arrays.asList("a"), list);
	}
	
	@Test
	public void testTaboo7() {
		Taboo<String> taboo = new Taboo<String>(Arrays.asList("a", "b"));
		List<String> list = new ArrayList<String>(Arrays.asList("b", "a", "c", "z"));
		taboo.reduce(list);
		
		assertEquals(Arrays.asList("b", "a", "c", "z"), list);
	}
	
	@Test
	public void testTabooNoRule() {
		Taboo<String> taboo = new Taboo<String>(Arrays.asList(""));
		List<String> list = new ArrayList<String>(Arrays.asList("b", "a", "c", "z"));
		taboo.reduce(list);
		
		assertEquals(Arrays.asList("b", "a", "c", "z"), list);
	}
}
