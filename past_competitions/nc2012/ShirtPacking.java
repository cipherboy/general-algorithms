
import java.util.Scanner;

public class ShirtPacking {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		long len, num, max, res;
		int trial = 0;
		while(scan.hasNextLong() && (len = scan.nextLong()) != 0){
			trial++;
			num = scan.nextLong();
			max = scan.nextLong();
			if(max == 1){
				res = (num >= len) ? len : 0;
			}
			else{
				res = num - (long)Math.ceil(1.0*(len - num)/(max - 1));
				if(res < 0){
					res = 0;
				}
			}
			if(res==1){
				System.out.println("Case "+trial+": "+res+" day");
			}
			else{
				System.out.println("Case "+trial+": "+res+" days");
			}
		}
		scan.close();
	}
}
