
import java.util.Scanner;

/*
 * Implementation of UVa Online Judge problem 11718: Fantasy of a Summation
 * 
 * Implements fast modular exponentiation algorithm, which runs in O(log n) time,
 * where n is the exponent
 */
public class ModPow {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int trials = scan.nextInt();
		for(int t=1; t<=trials; t++){
			int n = scan.nextInt(), k = scan.nextInt(), mod = scan.nextInt();
			long f = (k*modpow(n, k-1, mod))%mod;
			long answer = 0;
			for(int i=0; i<n; i++){
				long sum = scan.nextLong();
				answer = (answer+(sum*f)%mod)%mod;
			}
			System.out.println("Case "+t+": "+answer);
		}
		scan.close();
	}
	
	static long modpow(long base, long exp, long mod){
		if(mod == 1) return 0;
		long result = 1;
		base = base%mod;
		while(exp > 0){
			if(exp%2==1){
				result = (result*base)%mod;
			}
			exp >>= 1;
			base = (base*base)%mod;
		}
		return result;
	}
}
