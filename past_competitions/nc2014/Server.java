
import java.util.Scanner;

public class Server {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextInt()){
			int n = scan.nextInt(), t = scan.nextInt();
			int sumTime = 0, taskTime, taskCount = 0;
			boolean done = false;
			for(int i=0; i<n; i++){
				taskTime = scan.nextInt();
				if(done) continue;
				sumTime += taskTime;
				if(sumTime > t){
					done = true;
					continue;
				}
				taskCount++;
			}
			System.out.println(taskCount);
		}
		scan.close();
	}
}
