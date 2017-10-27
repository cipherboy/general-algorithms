
import java.util.Scanner;

public class TotientSieve {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[] totient = totientSieve(250000);

		while (true) {
			long next = scan.nextLong();
			if (next == 0) {
				break;
			}

			if (next == 1) {
				System.out.println("0/1");
				continue;
			}

			int d = 0;
			long upperLimit = 1;
			while (upperLimit < next) {
				d++;
				upperLimit += totient[d];
			}

			long lowerLimit = upperLimit - totient[d];
			int n = 0;
			while (lowerLimit != next) {
				n++;
				if (gcd(n, d) == 1) {
					lowerLimit += 1;
				}
			}

			System.out.println(n + "/" + d);
		}
	}

	public static long gcd(long m, long n) {
		while (n != 0) {
			long r = m % n;
			m = n;
			n = r;
		}
		return m;
	}

	/**
	 * Modified sieve of Eratosthenes to find the totient to each number from 1 to max
	 * @param max the upper bound
	 * @return an array with with the ith entry corresponding to totient(i)
	 */
	public static int[] totientSieve(int max) {
		int[] result = new int[max];
		for (int i = 0; i < max; i++) {
			result[i] = i;
		}

		for (int i = 2; i < max; i++) {
			if (result[i] == i) {
				// at a prime number
				for (int j = i; j < max; j += i) {
					// totient(n) = n * prod (p-1)/p for all primes p dividing n
					result[j] /= i;
					result[j] *= i - 1;
				}
			}
		}

		return result;
	}
}
