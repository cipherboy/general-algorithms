
import java.util.Scanner;

public class MaxCycleSum {
	public static void main(String[] args) {
    // Solution for UVa problem 10827 - Maximum sum on a torus
		Scanner scan = new Scanner(System.in);

		int trials = scan.nextInt();
		for (int t = 1; t <= trials; t++) {
			int n = scan.nextInt();
			long[][] grid = new long[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					grid[i][j] = scan.nextLong();
				}
			}

			System.out.println(maxCycle2dSum(grid));
		}
	}

	/**
	 * find the maximum sum of a 2d array sum, assuming the array acts as a
	 * torus, wrapping around on the columns and the rows. This is equivalent to
	 * doubling the array in one direction and doing the regular 2d array sum,
	 * while restricting ourselves to using n columns. We then flip the matrix
	 * and do it again for the rows. We also here get a time complexity of
	 * O(n^3)
	 * 
	 * @param arr
	 * @return
	 */
	static long maxCycle2dSum(long[][] arr) {
		int cols = arr[0].length;
		int rows = arr.length;
		long currentResult;
		long maxSum = Long.MIN_VALUE;

		// double the array on the columns and rows
		long[][] doubledArr = new long[rows][cols * 2];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				doubledArr[i][j] = arr[i][j];
				doubledArr[i][j + cols] = arr[i][j];
			}
		}

		for (int leftCol = 0; leftCol < cols; leftCol++) {
			long[] tmp = new long[rows];
			for (int rightCol = leftCol; rightCol < 2 * cols && rightCol - leftCol < cols; rightCol++) {
				for (int i = 0; i < rows; i++) {
					tmp[i] += doubledArr[i][rightCol];
				}
				currentResult = max1dCycleSum(tmp);
				maxSum = Math.max(maxSum, currentResult);
			}
		}

		return maxSum;
	}

	/**
	 * Find the maximum sum of an subarray, assuming the array wraps around.
	 * Uses the standard 1d subarry sum. Still a time complecity of O(n)
	 * 
	 * @param arr
	 * @return
	 */
	static long max1dCycleSum(long[] arr) {
		// case 1: standard sum, no cycles
		long maxSum = max1dSum(arr);

		// case 2: max sum of something with that cycles around. We negate the
		// array, find the largest sum there, then add it to the total array.
		// This is the same as finding a subarray, and taking the numbers on
		// either side of it to create the sum we want
		long sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			arr[i] *= -1;
		}

		long invertSum = max1dSum(arr);
		// Edge case: If all elements of the array are originally negative, then
		// the inverted array will have all positive elements, and the maxSum
		// will be everything. this causes the below to give the sum of 0, which
		// we actually do not want (as we want a sum with at least one element).
		// Thus, we check and do not update if this case hits.
		if (invertSum != -sum) {
			maxSum = Math.max(maxSum, sum + invertSum);
		}

		// we want to undo the negation as this array may be used again
		for (int i = 0; i < arr.length; i++) {
			arr[i] *= -1;
		}

		return maxSum;
	}

	static long max1dSum(long[] arr) {
		long maxSoFar = Long.MIN_VALUE, maxEndingHere = 0;
		for (long x : arr) {
			maxEndingHere += x;
			maxSoFar = Math.max(maxEndingHere, maxSoFar);
			maxEndingHere = Math.max(0, maxEndingHere);
		}
		return maxSoFar;
	}
}
