
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LongestIncreasingSubsequence {
	public static void main(String[] args) {
		// finds the longest increasing subsequence (not just the length)
		// solves UVa 481 - What Goes Up
		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> nums = new ArrayList<>();

		while (scan.hasNextInt()) {
			nums.add(scan.nextInt());
		}

		int[] result = longestIncreasingSubsequence(nums);
		System.out.println(result.length);
		System.out.println("-");
		for (int x : result) {
			System.out.println(x);
		}
	}

	/**
	 * Finds the sequence of the longest increasing subsequence of an array.
	 * This works in O(n^2) time. Can be modified to do Decreasing Subsequences.
	 * First finds the length, then constructs the subsequence in O(n) time
	 * 
	 * @param nums
	 * @return
	 */
	public static int[] longestIncreasingSubsequence(ArrayList<Integer> nums) {
		int[] lis = new int[nums.size()];
		int[] prev = new int[nums.size()];

		Arrays.fill(lis, 1);
		int lastIndex = 0;

		for (int i = 1; i < nums.size(); i++) {
			for (int j = 0; j < i; j++) {
				// IMPORTANT: check the problem statement to see if your
				// subsequence needs to be strictly increasing, or not strictly
				// (this implementation is strict)
				if (nums.get(i) > nums.get(j)) {
					if (lis[j] + 1 > lis[i]) {
						lis[i] = lis[j] + 1;
						prev[i] = j;
					}
				}
				if (lis[i] >= lis[lastIndex]) {
					lastIndex = i;
				}
			}
		}

		int[] result = new int[lis[lastIndex]];
		while (lis[lastIndex] > 1) {
			result[lis[lastIndex] - 1] = nums.get(lastIndex);
			lastIndex = prev[lastIndex];
		}
		result[0] = nums.get(lastIndex);
		return result;
	}
}
