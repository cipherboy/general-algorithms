
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigInteger;

/*
 * Solution to UVa online judge problem 787: Maximum Sub-sequence Product
 * 
 * Uses the Maximum Range sum algorithm, modified for products (see below)
 */
public class MaximumProduct {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextInt()){
			ArrayList<BigInteger> nums = new ArrayList<>();
			int next;
			while((next = scan.nextInt()) != -999999){
				nums.add(BigInteger.valueOf(next));
			}
			System.out.println(maxRangeProduct(nums.toArray(new BigInteger[0])));
		}
	}
	
	/**
	 * Finds the maximum continuous product in an array. Similar to the 
	 * maximum sum problem, but this time we must also keep track of the
	 * minimum at our current spot.
	 * 
	 * Also, as numbers get big much faster when multiplied rather than
	 * added, we implement this with BigIntegers
	 * @param arr
	 * @return
	 */
	static BigInteger maxRangeProduct(BigInteger[] arr){
		BigInteger result = arr[0];
		BigInteger maxEndingHere = arr[0];
		BigInteger minEndingHere = arr[0];
		
		for(int i=1; i<arr.length; i++){
			if(arr[i].compareTo(BigInteger.ZERO) > 0){
				maxEndingHere = arr[i].max(arr[i].multiply(maxEndingHere));
				minEndingHere = arr[i].min(arr[i].multiply(minEndingHere));
			}
			else{
				BigInteger temp = maxEndingHere;
				maxEndingHere = arr[i].max(arr[i].multiply(minEndingHere));
				minEndingHere = arr[i].min(arr[i].multiply(temp));
			}
			
			result = result.max(maxEndingHere);
		}
		
		return result;
	}
}
