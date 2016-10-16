
import java.util.Scanner;

public class SearchDoubter {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		long num;
		int trial = 1;
		while(scan.hasNextLong()){
			num = scan.nextLong() + 1;
			int x = (int)(Math.log(num)/Math.log(2));
			int k = x+1;
			long answer = 1 + num*k - (1 << k);
			System.out.println("Case "+trial+": "+answer);
			trial++;
		}
		scan.close();
	}
}
