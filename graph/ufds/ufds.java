import java.util.ArrayList;

public class ufds {
	private ArrayList<Integer> point;
	private ArrayList<Integer> rank;
	
	private int disjoint;
	
	public ufds(int n) {
		point = new ArrayList<Integer>(n);
		rank = new ArrayList<Integer>(n);
		
		for (int i = 0; i < n; i++) {
			point.add(i, i);
			rank.add(i, 0);
		}
		
		disjoint = n;
	}
	
	public int find(int i) {
		if (point.get(i) == i) {
			return i;
		}
		
		int result = find(point.get(i));
		point.set(i, result);
		return result;
	}
	
	public boolean isSame(int i, int j) {
		return find(i) == find(j);
	}
	
	public void union(int i, int j) {
		if (!isSame(i, j)) {
			int x = find(i);
			int y = find(j);
			
			if (rank.get(x) > rank.get(y)) {
				point.set(y, x);
			} else {
				point.set(x, y);
				
				if (rank.get(x) == rank.get(y)) {
					rank.set(y, rank.get(y) + 1);
				}
			}
			
			this.disjoint -= 1;	
		}
	}
	
	public int number() {
		int result = 0;
		for (int i = 0; i < point.size(); i++) {
			if (point.get(i) == i) {
				result += 1;
			}
		}
		
		if (result != disjoint) {
			System.out.println("not correct!" + result + " " + disjoint);
		}
		
		return result;
	}
	
	public int size(int t) {
		int count = 0;
		int target = find(t);
		for (int i = 0; i < point.size(); i++) {
			if (find(i) == target) {
				count += 1;
			}
		}
		
		return count;
	}
}
