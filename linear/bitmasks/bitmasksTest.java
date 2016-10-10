import static org.junit.Assert.*;

import org.junit.Test;

public class bitmasksTest {

	@Test
	public void testSetGetOne() {
		bitmasks b = new bitmasks(1);
		b.setBit(0, true);
		assertEquals(b.data.length, 1);
		assertEquals(b.data[0], 1);
		assertEquals(b.getBit(0), true);
		b.setBit(1, true);
		assertEquals(b.data[0], 3);
		assertEquals(b.getBit(0), true);
		assertEquals(b.getBit(1), true);
		b.setBit(2, true);
		assertEquals(b.data[0], 7);
		assertEquals(b.getBit(0), true);
		assertEquals(b.getBit(1), true);
		assertEquals(b.getBit(2), true);
		b.setBit(1, false);
		assertEquals(b.data[0], 5);
		b.setBit(0, false);
		assertEquals(b.data[0], 4);
		b.setBit(2, false);
		assertEquals(b.data[0], 0);
	}
	@Test
	public void testSetGetLarge() {
		bitmasks b = new bitmasks(512);
		assertEquals(b.data.length, 512/64);
		b.setBit(0, true);
		assertEquals(b.getBit(0), true);
		assertEquals(b.getBit(256), false);
		assertEquals(b.getBit(511), false);
		b.setBit(256, true);
		assertEquals(b.getBit(0), true);
		assertEquals(b.getBit(256), true);
		assertEquals(b.getBit(511), false);
		b.setBit(511, true);
		assertEquals(b.getBit(0), true);
		assertEquals(b.getBit(256), true);
		assertEquals(b.getBit(511), true);
		b.setBit(256, false);
		assertEquals(b.getBit(0), true);
		assertEquals(b.getBit(256), false);
		assertEquals(b.getBit(511), true);
		b.setBit(511, false);
		assertEquals(b.getBit(0), true);
		assertEquals(b.getBit(256), false);
		assertEquals(b.getBit(511), false);
		b.setBit(1, false);
		assertEquals(b.getBit(0), true);
		assertEquals(b.getBit(256), false);
		assertEquals(b.getBit(511), false);
		b.setBit(0, false);
		assertEquals(b.getBit(0), false);
		assertEquals(b.getBit(256), false);
		assertEquals(b.getBit(511), false);
	}
	
	@Test
	public void testSetRange() {
		bitmasks b = new bitmasks(512);
		int[] indices = new int[256];
		for (int i = 0; i < 256; i++) {
			indices[i] = i;
		}
		
		b.flagAll(indices);
		for (int i = 0; i < 256; i++) {
			assertEquals(b.getBit(i), true);
		}
		
		for (int i = 256; i < 512; i++) {
			assertEquals(b.getBit(i), false);
		}
		
		assertEquals(b.getNextUnset(10), 256);
		
		assertEquals(b.getNextSet(10), 10);
	}

}
