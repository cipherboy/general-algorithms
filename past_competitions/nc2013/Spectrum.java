
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayDeque;

public class Spectrum {
	static HashMap <String, HashSet<String>> dict;
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		dict = new HashMap<>();
		int t = 1;
		System.out.println("Case "+t+":");
		while(scan.hasNextLine()){
			String[] tokens = scan.nextLine().trim().split("\\s");
			if(tokens[0].equals("add")){
				if(tokens.length == 2){
					add(tokens[1]);
				}
				else{
					add(tokens[1], tokens[2]);
				}
			}
			else if(tokens[0].equals("associated")){
				associated(tokens[1], tokens[2]);
			}
			else if(tokens[0].equals("connections")){
				connections(tokens[1]);
			}
			else if(tokens[0].equals("reset")){
				System.out.println("----------");
				dict.clear();
				t++;
				System.out.println("Case "+t+":");
			}
		}
		System.out.println("----------");
	}
	
	static void add(String a){
		if(!dict.containsKey(a)){
			dict.put(a, new HashSet<String>());
		}
	}
	
	static void add(String a, String b){
		if(a.equals(b)){
			add(a);
			return;
		}
		add(a);
		add(b);
		dict.get(a).add(b);
		dict.get(b).add(a);
	}
	
	static void connections(String target){
		if(!dict.containsKey(target)){
			System.out.println("target does not exist");
			return;
		}
		if(dict.get(target).isEmpty()){
			System.out.println("no connections");
			return;
		}
		int i = 0;
		HashSet<String> seen = new HashSet<>();
		ArrayDeque<String> cur = new ArrayDeque<>();
		ArrayDeque<String> next;
		
		seen.add(target);
		cur.add(target);
		while(!cur.isEmpty()){
			next = new ArrayDeque<>();
			while(!cur.isEmpty()){
				String key = cur.poll();
				HashSet<String> neighbors = dict.get(key);
				for(String s: neighbors){
					if(seen.contains(s)) continue;
					next.add(s);
					seen.add(s);
				}
			}
			if(next.size() != 0){
				System.out.println(i+": "+next.size());
			}
			cur = next;
			i++;
		}
	}
	
	static void associated(String t1, String t2){
		if(!dict.containsKey(t1) || !dict.containsKey(t2)){
			System.out.println("target does not exist");
			return;
		}
		if(t1.equals(t2)){
			System.out.println("yes: -1");
			return;
		}
		
		HashSet<String> seen = new HashSet<>();
		ArrayDeque<String> cur = new ArrayDeque<>();
		ArrayDeque<String> next;
		int i = 0;
		
		seen.add(t1);
		cur.add(t1);
		while(!cur.isEmpty()){
			next = new ArrayDeque<>();
			while(!cur.isEmpty()){
				String key = cur.poll();
				HashSet<String> neighbors = dict.get(key);
				for(String s: neighbors){
					if(seen.contains(s)) continue;
					if(s.equals(t2)){
						System.out.println("yes: "+i);
						return;
					}
					next.add(s);
					seen.add(s);
				}
			}
			cur = next;
			i++;
		}
		System.out.println("no");
	}
}
