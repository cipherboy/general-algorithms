
import java.util.Scanner;

public class Knapsack {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		for(int i=0; i<n; i++){
			int size = scan.nextInt();
			int[] nums = new int[size];
			int totalSum = 0;
			for(int j=0; j<size; j++){
				nums[j] = scan.nextInt();
				totalSum += nums[j];
			}
			
			int part = knapsack(nums, nums, totalSum/2);
			int otherPart = totalSum - part;
			//System.out.println("Partitioned into "+part+" and "+otherPart);
			System.out.println(Math.abs(part - otherPart));
		}
		scan.close();
	}
	
	/**
	 * Implementation of the knapsack algorithm. Given a maximum weight and n
	 * items, each with a specific weight and value, we use this algorithm to
	 * find the maximum value we can carry. This takes O(nW) space and time 
	 * (where capacity = W). Dynamically Programmed bottom up
	 * @param weights These affect how much we can hold
	 * @param values These affect what we return
	 * @param capacity this is an upper bound on sum of weights
	 * @return the maximum sum of values such that the sum of weights are less than capacity
	 */
	static int knapsack(int[] weights, int[] values, int capacity){
		int n = weights.length;
		int[][] m = new int[n+1][capacity+1];
		
		for(int i=1; i<= n; i++){
			for(int j=0; j<=capacity; j++){
				if(i == 0 || j == 0)
					m[i][j] = 0;
				else if(weights[i-1] <= j)
					m[i][j] = Math.max(values[i-1] + m[i-1][j-weights[i-1]], m[i-1][j]);
				else
					m[i][j] = m[i-1][j];
			}
		}
		
		return m[n][capacity];
	}
}
