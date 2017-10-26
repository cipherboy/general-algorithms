
import java.util.Scanner;

public class ChangeMaking {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// change making DP example. solves UVa 147 - Dollars
		while (true) {
			String money = scan.next();
			String[] dc = money.split("\\.");
			int size = Integer.parseInt(dc[0]) * 100 + Integer.parseInt(dc[1]);
			if (size == 0) {
				break;
			}
			long result = getNumWays(size);
			System.out.printf("%6s%17d\n", money, result);
		}
	}

	// this array contains all coin and bill amounts that we are allowed to use,
	// expressed in cents
	static int[] denominations = { 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000 };

	/**
	 * Finds the number of ways 'limit' number of cents can be made, using the
	 * above denominations Runs in O(mn) time, where m is the number of
	 * denominations, and n is the limit
	 * 
	 * @param limit
	 * @return the number of ways we can make limit cents of change
	 */
	public static long getNumWays(int limit) {
		long[] dp = new long[limit + 1];
		dp[0] = 1;
		for (int d : denominations) {
			for (int i = d; i <= limit; i++) {
				dp[i] += dp[i - d];
			}
		}

		return dp[limit];
	}
}
