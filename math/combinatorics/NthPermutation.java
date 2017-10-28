public class NthPermutation {
	public static void main(String[] args) {
		String str = "abbbc";
		for (int n = 1; n <= 20; n++) {
			String result = nthPermutation(str, n);
			System.out.println(result);
		}
	}

	static long[] factorials;

	public static void precomputeFactorials() {
		factorials = new long[21];
		factorials[0] = 1;
		for (int i = 1; i <= 20; i++) {
			factorials[i] = i * factorials[i - 1];
		}
	}

	/**
	 * Assumption: str is a string containing only lowercase letters FIRST
	 * PERMUTATION IS FOUND AT INDEX 1 INDEX 0 GIVES YOU THE LAST PERMUTATION
	 * (APPARENTLY), SO DONT DO THAT
	 * 
	 * @param str
	 *            the String of things permute
	 * @param n
	 *            the permutation to find
	 * @return the nth lexicographic permutation of the characters in str
	 */
	public static String nthPermutation(String str, int n) {
		precomputeFactorials();

		int[] freq = new int[26];
		for (int i = 0; i < str.length(); i++) {
			freq[str.charAt(i) - 'a']++;
		}

		char[] result = new char[str.length()];

		int sum = 0;
		int k = 0;

		while (sum != n) {
			sum = 0;
			for (int i = 0; i < 26; i++) {
				if (freq[i] == 0) {
					continue;
				}

				freq[i]--;

				// calculate sum after using this character using the
				// MISSISSIPPI rule
				long xsum = factorials[str.length() - 1 - k];
				for (int j = 0; j < 26; j++) {
					xsum /= factorials[freq[j]];
				}
				sum += xsum;

				// fix this character, update sum needed
				if (sum >= n) {
					result[k] = (char) (i + 'a');
					k++;
					n -= (sum - xsum);
					break;
				} else { // add character back to frequency
					freq[i]++;
				}
			}
		}

		// add extra character if necessary
		for (int i = 26 - 1; i >= 0; i--) {
			while (freq[i] > 0) {
				result[k] = (char) (i + 'a');
				freq[i]--;
				k++;
			}
		}

		return new String(result);
	}
}
