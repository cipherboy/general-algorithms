import static org.junit.Assert.*;

import org.junit.Test;

public class pointTest {

	@Test
	public void test() {
		double[] a_a = {1, 2};
		double[] b_a = {3, 4};
		point a = new point(a_a);
		point b = new point(b_a);
		
		assertEquals(a.tDistance(b), 4.0, 0.001);
		assertEquals(a.eDistance(b), 2.8284271247461903, 0.00001);
		assertEquals(a.eDistanceSquared(b), 8.0, 0.001);
	}

}
