import java.util.Arrays;
import java.util.Scanner;


public class Rank {
	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		int c = 0;
		while (s.hasNextInt()) {
			int N = s.nextInt();
			c++;
			Score[] one = new Score[N];
			for (int i = 0; i < N; i++) {
				one[i] = new Score(s.nextInt(), i);
			}
			Score[] two = new Score[N];
			for (int i = 0; i < N; i++) {
				two[i] = new Score(s.nextInt(), i);
			}
			Arrays.sort(one);
			Arrays.sort(two);
			int diff = -1;
			for (int i = 0; i < N; i++) {
				if (one[i].id != two[i].id) {
					diff = i;
					break;
				}
			}
			if (diff == -1) {
				System.out.println("Case " + c + ": agree");
			} else {
				System.out.println("Case " + c + ": " + (diff + 1));
			}
		}
	}
	
	static class Score implements Comparable<Score> {
		int score, id;
		public Score(int score, int id) {
			this.score = score;
			this.id = id;
		}
		
		@Override
		public int compareTo(Score other) {
			return -1 * (score - other.score);
		}
		
		@Override
		public String toString() {
			return score + " " + id;
		}
	}

}
