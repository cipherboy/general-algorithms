package InternetBandwidth;

/**
 * Ford-Fulkerson Algorithm.
 * Solution to https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=385&page=show_problem&problem=761
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class InternetBandwidth {
	private static Node[] verts;
	public static void main(String args[]) {
		Scanner s = new Scanner(System.in);
		int casen = 0;
		while (s.hasNextInt()) {
			int n = s.nextInt();
			if (n == 0) {
				break;
			}
			casen++;
			verts = new Node[n + 1];
			for (int i = 1; i <= n; i++) {
				verts[i] = new Node(i);
			}
			int source = s.nextInt();
			int end = s.nextInt();
			int nEdges = s.nextInt();
			for (int i = 0; i < nEdges; i++) {
				int a = s.nextInt();
				int b = s.nextInt();
				int c = s.nextInt();
				if (!verts[a].edges.containsKey(b)) {
					verts[a].edges.put(b, c);
					verts[b].edges.put(a, c);
				} else {
					int old = verts[a].edges.get(b);
					verts[a].edges.put(b, old + c);
					verts[b].edges.put(a, old + c);
				}
			}
			System.out.println("Network " + casen);
			int max = edmondsKarp(verts[source], verts[end]);
			System.out.println("The bandwidth is " + max + ".");
			System.out.println();
		}
	}
	
	static int edmondsKarp(Node source, Node end) {
		HashMap<Node, Node> parents = new HashMap<>();
		int maxFlow = 0;
		while (bfs(source, end, parents)) {
			Node current = end;
			int minCapOnPath = Integer.MAX_VALUE;
			while (parents.get(current) != null) {
				Node prev = parents.get(current);
				int capacity = prev.edges.get(current.id);
				minCapOnPath = Math.min(capacity, minCapOnPath);
				current = prev;
			}
			current = end;
			while (parents.get(current) != null) {
				Node prev = parents.get(current);
				int old = prev.edges.get(current.id);
				prev.edges.put(current.id, old - minCapOnPath);
				// Edge may not exist if edges are bidirectional.
				old = current.edges.get(prev.id);
				// This is different because the problem says that the amount
				// Transmitted in both directions must be less than the capacity.
				current.edges.put(prev.id, old - minCapOnPath);
				current = prev;
			}
			maxFlow += minCapOnPath;
			parents = new HashMap<>();
		}
		return maxFlow;
	}
	
	static boolean bfs(Node start, Node end, HashMap<Node, Node> parents) {
		LinkedList<Node> queue = new LinkedList<Node>();
		queue.add(start);
		parents.put(start, null);
		while (!queue.isEmpty()) {
			Node n = queue.remove(0);
			for (Integer i : n.edges.keySet()) {
				Node out = verts[i];
				if (n.edges.get(i) == 0) {
					continue;
				}
				if (out == end) {
					parents.put(out, n);
					return true;
				}
				if (!parents.containsKey(out)) {
					parents.put(out, n);
					queue.add(out);
				}
			}
		}
		return false;
	}
	
	static class Node {
		int id;
		HashMap<Integer, Integer> edges = new HashMap<>();
		public Node(int id) {
			this.id = id;
		}
	}
}
