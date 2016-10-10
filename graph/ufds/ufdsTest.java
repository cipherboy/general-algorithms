import static org.junit.Assert.*;

import org.junit.Test;

public class ufdsTest {

	@Test
	public void test() {
		ufds u = new ufds(6);
		u.union(1, 3);
		assertEquals(u.number(), 5);
		assertEquals(u.isSame(1, 3), true);
		assertEquals(u.isSame(1, 2), false);
		u.union(2, 4);
		assertEquals(u.number(), 4);
		assertEquals(u.isSame(1, 3), true);
		assertEquals(u.isSame(1, 2), false);
		assertEquals(u.isSame(2, 4), true);
		assertEquals(u.isSame(1, 4), false);
		u.union(2, 3);
		assertEquals(u.number(), 3);
		assertEquals(u.isSame(1, 3), true);
		assertEquals(u.isSame(1, 2), true);
		assertEquals(u.isSame(2, 4), true);
		assertEquals(u.isSame(1, 4), true);
		u.union(2, 1);
		assertEquals(u.number(), 3);
		assertEquals(u.isSame(1, 3), true);
		assertEquals(u.isSame(1, 2), true);
		assertEquals(u.isSame(2, 4), true);
		assertEquals(u.isSame(1, 4), true);
	}

}
