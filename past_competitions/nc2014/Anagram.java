import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;


public class Anagram {
	static HashSet<ArrayList<Integer>> dict;
	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		int C = 0;
		while (s.hasNextInt()) {
			int N = s.nextInt();
			C++;
			dict = new HashSet<>();
			for (int i = 0; i < N; i++) {
				String word = s.next();
				dict.add(countLetters(word.toUpperCase()));
			}
			int M = s.nextInt();
			System.out.println("Case " + C + ":");
			for (int i = 0; i < M; i++) {
				String top = s.next().toUpperCase();
				String bottom = s.next().toUpperCase();
				ArrayList<Integer> topLetters = countLetters(top);
				ArrayList<Integer> bottomLetters = countLetters(bottom);
				boolean possible = true;
				for (int j = 0; j < bottomLetters.size() && possible; j++) {
					int diff = bottomLetters.get(j) - topLetters.get(j);
					if (diff < 0) {
						possible = false;
						continue;
					}
				}
				if (!possible) {
					System.out.println("no");
				} else {
					boolean p = bfs(topLetters, bottomLetters, top.length(), bottom.length());
					System.out.println(p ? "yes" : "no");
				}
			}
		}
		
	}
	
	static boolean bfs(ArrayList<Integer> start, ArrayList<Integer> end, int startSize, int endSize) {
		HashSet<ArrayList<Integer>> visited = new HashSet<>();
		visited.add(end);
		Queue<State> q = new ArrayDeque<State>();
		q.add(new State(end, endSize));
		while (!q.isEmpty()) {
			State s = q.remove();
			
			for (int i = 0; i < s.counts.size(); i++) {
				Integer count = s.counts.get(i);
				if (count == 0) {
					continue;
				}
				ArrayList<Integer> copy = (ArrayList<Integer>) s.counts.clone();
				copy.set(i, count - 1);
				if (s.size - 1 < startSize || visited.contains(copy)) {
					continue;
				} else if (!dict.contains(copy)) {
					continue;
				} else {
					if (copy.equals(start)) {
						return true;
					}
					visited.add(copy);
					q.add(new State(copy, s.size - 1));
				}
			}
		}
		return false;
	}
	
	static class State {
		ArrayList<Integer> counts;
		int size;
		public State (ArrayList<Integer> counts, int size) {
			this.counts = counts;
			this.size = size;
		}
	}
	
	static ArrayList<Integer> countLetters(String word) {
		ArrayList<Integer> counts = new ArrayList<Integer>(26);
		for (int i = 0; i < 26; i++) {
			counts.add(0);
		}
		for (int i = 0; i < word.length(); i++) {
			counts.set(word.charAt(i) - 'A', counts.get(word.charAt(i) - 'A') + 1);
		}
		return counts;
	}
	
	static class Pair {
		int letter, count;
		public Pair(int a, int b) {
			this.letter = a;
			this.count = b;
		}
		
		public Object clone() {
			try {
				return super.clone();
			} catch (CloneNotSupportedException e) {
				return null;
			}
		}
	}
}


