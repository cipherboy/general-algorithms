
import java.util.ArrayList;
import java.util.Scanner;

public class LongestIncreasingSubsequence {
	public static void main(String[] args) {
		// example for Longest Increasing Subsequence: Solves UVa 231 - Testing
		// the CATCHER
		Scanner scan = new Scanner(System.in);

		int trial = 0;
		while (true) {
			ArrayList<Integer> heights = new ArrayList<>();
			while (true) {
				int next = scan.nextInt();
				if (next == -1) {
					break;
				}
				heights.add(next);
			}

			if (heights.size() == 0) {
				break;
			}

			if (trial != 0) {
				System.out.println();
			}
			trial++;

			// this problem is actually Longest Decreasing Subsequence. It can
			// be done as Longest Increasing Subsequence on a reversed array
			// like so, or you can implement it better
			int[] nums = new int[heights.size()];
			for (int i = 0; i < nums.length; i++) {
				nums[i] = heights.get(nums.length - 1 - i);
			}

			int answer = longestIncreasingSubsequence(nums);
			System.out.println("Test #" + trial + ":");
			System.out.println("  maximum possible interceptions: " + answer);
		}
	}

	/**
	 * Finds the length of the longest increasing subsequence of an array. This
	 * works in O(n^2) time. Can be modified to do Decreasing Subsequences
	 * 
	 * @param nums
	 * @return
	 */
	public static int longestIncreasingSubsequence(int[] nums) {
		int[] lis = new int[nums.length];
		lis[0] = 1;
		int length = 1;

		for (int i = 1; i < lis.length; i++) {
			for (int j = 0; j < i; j++) {
				// IMPORTANT: check the problem statement to see if your
				// subsequence needs to be strictly increasing, or not strictly
				// (this implementation is non-strict)
				if (nums[i] >= nums[j]) {
					lis[i] = Math.max(lis[i], lis[j] + 1);
				}
				length = Math.max(length, lis[i]);
			}
			if (lis[i] == 0) {
				lis[i] = 1;
			}
		}

		return length;
	}
}
