
import java.util.Scanner;
import java.util.ArrayList;

public class CycleProducts {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		String line;
		int trial = 0;
		while(!(line = scan.nextLine()).equals("End")){
			trial++;
			//Parse input
			String[] tokens = line.trim().split("\\s");
			ArrayList<int[]> perms = new ArrayList<>();
			ArrayList<Integer> cycle = new ArrayList<>();
			for(int i=0; i<tokens.length; i++){
				if(tokens[i].equals("("))// starts new cycle, clear the last
					cycle.clear();
				else if(tokens[i].equals(")"))// ends cycle, put it to permutation
					perms.add(parseCycle(cycle));
				else// in cycle, add number to it
					cycle.add(Integer.parseInt(tokens[i]));
			}
			
			//find product of permutations
			int[] prodPerm = multiply(perms);
			
			//splits new permutation into disjoint cycles
			ArrayList<ArrayList<Integer>> disjointCycles = toCycles(prodPerm);
			
			//finds our result
			String result = cycleToString(disjointCycles);
			System.out.println("Case "+trial+": "+result);
		}
	}
	
	static int[] parseCycle(ArrayList<Integer> cycle){
		int[] perm = new int[9];
		for(int i=0; i<9; i++){
			perm[i] = i+1;
		}
		for(int i=0; i<cycle.size(); i++){
			perm[cycle.get(i)-1] = cycle.get((i+1)%cycle.size());
		}
		
		return perm;
	}
	
	static int[] multiply(ArrayList<int[]> perms){
		int[] result = new int[9];
		for(int i=0; i<9; i++){
			if(perms.size() == 0){
				result[i] = i+1;
				continue;
			}
			int res = perms.get(0)[i];
			for(int j=1; j<perms.size(); j++){
				res = perms.get(j)[res-1];
			}
			result[i] = res;
		}
		return result;
	}
	
	static ArrayList<ArrayList<Integer>> toCycles(int[] perm){
		boolean[] used = new boolean[9];
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		for(int i=0; i<perm.length; i++){
			if(used[i]) continue; // skips already used permutations
			if(perm[i] == i+1){ // skips identity permutations
				used[i] = true;
				continue;
			}
			
			ArrayList<Integer> cycle = new ArrayList<>();
			int cur = i+1;
			while(!used[cur-1]){
				used[cur-1] = true;
				cycle.add(cur);
				cur = perm[cur-1];
			}
			
			result.add(cycle);
		}
		
		return result;
	}
	
	static String cycleToString(ArrayList<ArrayList<Integer>> cycles){
		if(cycles.size() == 0)
			return "I";
		
		StringBuilder result = new StringBuilder();
		for(int i=0; i<cycles.size(); i++){
			result.append("( ");
			for(int j=0; j<cycles.get(i).size(); j++)
				result.append(cycles.get(i).get(j)+" ");
			result.append(")");
			if(i != cycles.size()-1)
				result.append(" ");
		}
		
		return result.toString();
	}
}
