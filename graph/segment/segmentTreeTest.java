import static org.junit.Assert.*;

import org.junit.Test;

public class segmentTreeTest {

	@Test
	public void test() {
		int[] data = {18, 17, 13, 19, 15, 11, 20};
		segmentTree t = new segmentTree(data);
		assertEquals(t.rmq(1,  3), 2);
		assertEquals(t.rmq(4,  6), 5);
	}

}
