
import java.util.Scanner;

public class PointMassCenter {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int t = 1;
		int size;
		while((size = scan.nextInt()) >= 0){
			int m = 0, mx = 0, my = 0;
			for(int i=0; i<size; i++){
				int xi = scan.nextInt(), yi = scan.nextInt(), mi = scan.nextInt();
				m += mi;
				mx += mi*yi;
				my += mi*xi;
			}
			double xbar = 1.0*my/m;
			double ybar = 1.0*mx/m;
			System.out.printf("Case %d: %.2f %.2f%n", t, xbar, ybar);
			t++;
		}
		scan.close();
	}
}
