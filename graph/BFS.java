import java.util.*;

public class BFS {
    public static HashMap<Integer, HashSet<Integer>> graph;


    public static class Result {
        public HashMap<Integer, Integer> parents;
        public HashSet<Integer> reachable;

        public Result() {
            parents = new HashMap<Integer, Integer>();
            reachable = new HashSet<Integer>();
        }
    }

	public static Result bfs(int start, int end) {
        Result r = new Result();
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(start);

		while (!queue.isEmpty()) {
			Integer u = queue.remove(0);
            r.reachable.add(u);
			for (Integer v : graph.get(u)) {
				if (v == end) {
					r.parents.put(v, u);
					return r;
				}

				if (!r.parents.containsKey(v)) {
					r.parents.put(v, u);
					queue.add(v);
				}
			}
		}

		// Found no start-end paths.
		return r;
	}

    public static void main(String[] args) {
        graph = new HashMap<Integer, HashSet<Integer>>();
        graph.put(1, new HashSet<Integer>());
        graph.put(2, new HashSet<Integer>());
        graph.put(3, new HashSet<Integer>());
        graph.put(4, new HashSet<Integer>());
        graph.put(5, new HashSet<Integer>());
        graph.put(6, new HashSet<Integer>());
        graph.get(1).add(2);
        graph.get(2).add(3);
        graph.get(2).add(3);
        graph.get(4).add(5);
        graph.get(5).add(6);
        graph.get(6).add(5);
        Result r = bfs(1, 5);
        System.out.println(r.parents.get(3));
        System.out.println(Arrays.toString(r.reachable.toArray(new Integer[0])));
    }

}
