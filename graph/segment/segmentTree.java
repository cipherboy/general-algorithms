import java.util.Arrays;

public class segmentTree {
	private int[] tree;
	private int[] array;
	private int n;
	
	public segmentTree(int[] data) {
		n = data.length;
		array = Arrays.copyOf(data, n);
		tree = new int[4*n];
		build(1, 0, n - 1);
	}
	
	private void build(int pos, int left, int right) {
		if (left == right) {
			tree[pos] = left;
		} else {
			//System.out.println(pos + " " + left + " " + (left + right)/2 + " " + right);
			build(pos << 1, left, (left + right)/2);
			build((pos << 1) + 1, (left + right)/2 + 1, right);
			int p1 = tree[pos << 1];
			int p2 = tree[(pos << 1) + 1];
			
			if (array[p1] <= array[p2]) {
				tree[pos] = p1;
			} else {
				tree[pos] = p2;
			}
		}
	}
	
	private int rmq(int pos, int left, int right, int i, int j) {
		if (i > right || j < left) {
			return -1;
		} else if (left >= i || right <= j) {
			return tree[pos];
		}
		
		int p1 = rmq(pos << 1, left, (left+right)/2, i, j);
		int p2 = rmq((pos << 1) + 1, (left+right)/2 + 1, right, i, j);
		
		if (p1 == -1) {
			return p2;
		} else if (p2 == -1) {
			return p1;
		} else if (array[p1] <= array[p2]) {
			return p1;
		} else {
			return p2;
		}
	}
	
	public int rmq(int i, int j) {
		return rmq(1, 0, n-1, i, j);
	}
}
