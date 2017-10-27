
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

public class PrimeSieve {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<Integer> primes = primeSieve(1000000);

		while (true) {
			int n = scan.nextInt();
			if (n == 0) {
				break;
			}

			for (int i = 0; i < primes.size(); i++) {
				int a = primes.get(i);
				int b = n - a;
				if (isPrime.get(b)) {
					System.out.println(n + " = " + a + " + " + b);
					break;
				}
			}
		}
	}

	// fast lookup for checking is a number is prime. Filled in after calling primeSieve
	static BitSet isPrime;

	/**
	 * finds all prime numbers between 2 (inclusive) and max (exclusive) in 
	 * O(n log log n) time over O(n) memory
	 * 
	 * @param max the upper bound
	 * @return a list of all primes below max
	 */
	public static ArrayList<Integer> primeSieve(int max) {
		isPrime = new BitSet(max);
		isPrime.set(2, max);

		for (int i = 2; i * i < max; i++) {
			if (isPrime.get(i)) {
				for (int j = i * i; j < max; j += i) {
					isPrime.set(j, false);
				}
			}
		}

		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 2; i < max; i++) {
			if (isPrime.get(i)) {
				result.add(i);
			}
		}

		return result;
	}
}
