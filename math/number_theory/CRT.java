/**
 * Chinese Remainder Theorem
 */
public class CRT {
	/**
	 * @return n (mod rs) such that n == a (mod r) and n == b (mod s)
	 */
	public static long crt(long r, long s, long a, long b) {
		long[] ans = ExtendedEuclid.gcd(r, s);

		if (Math.abs(ans[0]) != 1)
			throw new IllegalArgumentException("gcd(r, s) must be 1");

		long n = b * r * ans[1] + a * s * ans[2], rs = r * s;

		return (n % rs + rs) % rs; // least positive n (mod rs)
	}

	public static void main(String[] args) {
		long r = 7, s = 12, a = 3, b = 5;
		System.out.println(crt(r, s, a, b));
	}
}

