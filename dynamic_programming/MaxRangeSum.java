
import java.util.Scanner;

/*
 * Solution for UVa online judge problem 10864: The Jackpot
 * 
 * Uses the 1D Range Sum dynamic programming technique (see below)
 */

public class MaxRangeSum {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n;
		while((n = scan.nextInt()) != 0){
			int[] seq = new int[n];
			for(int i=0; i<n; i++){
				seq[i] = scan.nextInt();
			}
			int result = maxRangeSum(seq);
			if(result == 0){
				System.out.println("Losing streak.");
			}
			else{
				System.out.println("The maximum winning streak is "+result+".");
			}
		}
		scan.close();
	}
	
	/**
	 * Implementation of Maximum 1D Range Sum. This is an O(n) algorithm to find the
	 * value of the continuous sub-array with the maximum sum. If there is no positive
	 * subarray, we return 0 (this can be easily modified)
	 *
	 * For a version of this function that allows negative values, look at Max3dGridSum
	 * @param arr the array to search
	 * @return the maximum sub array sum
	 */
	static int maxRangeSum(int[] arr){
		int maxSoFar = 0, maxEndingHere = 0;
		for(int x: arr){
			maxEndingHere = Math.max(0, maxEndingHere+x);
			maxSoFar = Math.max(maxEndingHere, maxSoFar);
		}
		return maxSoFar;
	}
}
