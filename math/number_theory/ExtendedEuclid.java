public class ExtendedEuclid {
	/**
	 * @return GCD(a, b) = ax + by where [0] is GCD, [1] is x, [2] is y
	 */
	public static long[] gcd(long a, long b) {
		if (b == 0)
			return new long[] { a, 1, 0 };
		long[] ans = gcd(b, a % b);
		long x = ans[1], y = ans[2], q = a / b;
		ans[1] = y;
		ans[2] = x - y * q;
		return ans;
	}

	public static void main(String[] args) {
		long a = 1027, b = 712;
		long[] ans = gcd(a, b);
		System.out.printf("%d = %d * %d + %d * %d", ans[0], a, ans[1], b, ans[2]);
	}

}
