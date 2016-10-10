
public class bitmasks {
	public long[] data;
	public long bits; 
	
	public bitmasks(int max) {
		data = new long[(max+63)/64];
		this.bits = max;
	}
	
	public void setBit(int bit, boolean value) {
		int index = bit/64;
		long offset = bit % 64;
		long mask = 1 << offset;
		
		if (value) {
			data[index] = data[index] | mask;
		} else {
			data[index] = data[index] & ~mask;
		}
	}
	
	public boolean getBit(int bit) {
		int index = bit/64;
		long offset = bit % 64;
		long mask = 1 << offset;
		
		return (data[index] & mask) != 0;
	}
	
	public void flagAll(int[] bits) {
		for (int bit : bits) {
			setBit(bit, true);
		}
	}
	
	public void clearAll(int[] bits) {
		for (int bit : bits) {
			setBit(bit, false);
		}
	}
	
	public int getNextUnset(int start) {
		int last_index = (((start/64)+1)*64)-1;
	
		for (int i = start; start < last_index && start < bits; start++) {
			if (getBit(i) ==  false) {
				return i;
			}
		}
		
		int next = (start/64)+1;
		for (int i = next; i < data.length; i++) {
			if ((data[i] & 0xFFFFFFFFFFFFFFFFl) != 0xFFFFFFFFFFFFFFFFl) {
				for (int j = i*64; j < (i+1)*64 && j < bits; j++) {
					if (getBit(j) == false) {
						return j;
					}
				}
			}
		}
		
		return -1;
	}
	
	public int getNextSet(int start) {
		int last_index = (((start/64)+1)*64)-1;
	
		for (int i = start; start < last_index && start < bits; start++) {
			if (getBit(i) ==  true) {
				return i;
			}
		}
		
		int next = (start/64)+1;
		
		for (int i = next; i < data.length; i++) {
			if ((data[i] & 0xFFFFFFFFFFFFFFFFl) != 0xFFFFFFFFFFFFFFFFl) {
				for (int j = next*64; j < (next+1)*64 && j < bits; j++) {
					if (getBit(i) == true) {
						return i;
					}
				}
			}
		}
		
		return -1;
	}
}
