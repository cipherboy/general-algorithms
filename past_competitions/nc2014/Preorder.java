
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Preorder {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int next, trial = 0;
		ArrayList<Integer> tree = new ArrayList<>();
		while(scan.hasNextInt()){
			next = scan.nextInt();
			if(next < 0){
				//System.out.println("Partitioning "+tree);
				boolean isTree = isPreorder(tree.toArray(new Integer[0]));
				trial++;
				if(isTree){
					System.out.println("Case "+trial+": yes");
				}
				else{
					System.out.println("Case "+trial+": no");
				}
				tree.clear();
			}
			else{
				tree.add(next);
			}
		}
		scan.close();
	}
	
	static boolean isPreorder(Integer[] nodes){
		if(nodes.length < 2)
			return true;
		int root = nodes[0];
		int lSubLeft = 1;
		int lSubRight = lSubLeft;
		while(lSubRight < nodes.length && nodes[lSubRight] < root)
			lSubRight++;
		int rSubLeft = lSubRight;
		int rSubRight = rSubLeft;
		while(rSubRight < nodes.length && nodes[rSubRight] > root)
			rSubRight++;
		if(rSubRight < nodes.length) return false;
		Integer[] arr1 = Arrays.copyOfRange(nodes, lSubLeft, lSubRight);
		Integer[] arr2 = Arrays.copyOfRange(nodes, rSubLeft, rSubRight);
		//System.out.println("Partitioned into: "+Arrays.toString(arr1)+"+"+nodes[0]+"+"+Arrays.toString(arr2));
		return isPreorder(arr1) && isPreorder(arr2);
	}
}
