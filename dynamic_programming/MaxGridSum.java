
import java.util.Scanner;

/*
 * Solution to UVa Online Judge problem 108: Maximum Sum
 * 
 * We just use the maximum 2d array sum algorithm (see below)
 */
public class MaxGridSum {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int size = scan.nextInt();
		int[][] arr = new int[size][size];
		for(int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				arr[i][j] = scan.nextInt();
			}
		}
		scan.close();
		System.out.println(max2dSum(arr));
	}
	
	/**
	 * Finds the maximum sum of numbers on a 2d grid. We do this by enumerating the
	 * 1d range sum over all possible ranges of columns. Using the 1d array sum algorithm
	 * reduces the time to O(n^3) from a naive O(n^4)
	 * @param arr
	 * @return
	 */
	static int max2dSum(int[][] arr){
		int cols = arr[0].length;
		int rows = arr.length;
		int currentResult;
		int maxSum = Integer.MIN_VALUE;
		
		for(int leftCol = 0; leftCol < cols; leftCol++){
			int[] tmp = new int[rows];
			for(int rightCol = leftCol; rightCol < cols; rightCol++){
				for(int i=0; i<rows; i++){
					tmp[i] += arr[i][rightCol];
				}
				currentResult = max1dSum(tmp);
				maxSum = Math.max(maxSum, currentResult);
			}
		}
		
		return maxSum;
	}
	
	static int max1dSum(int[] arr){
		int maxSoFar = 0, maxEndingHere = 0;
		for(int x: arr){
			maxEndingHere = Math.max(0, maxEndingHere+x);
			maxSoFar = Math.max(maxEndingHere, maxSoFar);
		}
		return maxSoFar;
	}

}
