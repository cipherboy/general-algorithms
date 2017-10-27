import java.util.*;

public class Flow {
	public static HashMap<Integer, HashMap<Integer, EdgeData>> graph;

    static class Edge {
        public int from;
        public int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public String toString() {
            return from + "->" + to;
        }
    }

	static class EdgeData {
		public int from;
		public int to;
		public int capacity;
		public int flow;

        public EdgeData(int from, int to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
            this.flow = 0;
        }

        public EdgeData(int capacity) {
            this.capacity = capacity;
            this.flow = 0;
        }

		public int remainder() {
			return capacity - flow;
		}

		public void update(int v) {
			this.flow += v;
		}
	}

	static int flow(int source, int end) {
		int flow_value = 0;
		HashMap<Integer, Integer> parents = new HashMap<Integer, Integer>();

		while (bfs(source, end, parents)) {
			Integer current = end;

			int min_cap = Integer.MAX_VALUE;
			while (parents.get(current) != null) {
				Integer prev = parents.get(current);
				int capacity = graph.get(prev).get(current).remainder();
				min_cap = Math.min(capacity, min_cap);
				current = prev;
			}

			current = end;
			while (parents.get(current) != null) {
				Integer prev = parents.get(current);
				graph.get(prev).get(current).update(min_cap);
				current = prev;
			}

			flow_value += min_cap;
			parents = new HashMap<Integer, Integer>();
		}

		return flow_value;
	}

	static boolean bfs(int start, int end, HashMap<Integer, Integer> parents) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		parents.put(start, null);

		while (!queue.isEmpty()) {
			Integer u = queue.remove(0);
			for (Integer v : graph.get(u).keySet()) {
				if (graph.get(u).get(v).remainder() == 0) {
					continue;
				}

				if (v == end) {
					parents.put(v, u);
					// Found a start-end path.
					return true;
				}

				if (!parents.containsKey(v)) {
					parents.put(v, u);
					queue.add(v);
				}
			}
		}

		// Found no start-end paths.
		return false;
	}

	public static HashSet<Integer> bfs2(int start) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
        HashSet<Integer> result = new HashSet<Integer>();
		queue.add(start);

		while (!queue.isEmpty()) {
			Integer u = queue.remove(0);
            result.add(u);
			for (Integer v : graph.get(u).keySet()) {
				if (graph.get(u).get(v).remainder() == 0) {
					continue;
				}

				if (!result.contains(v)) {
					queue.add(v);
				}
			}
		}

		// Found no start-end paths.
		return result;
	}


    public static HashSet<Edge> mincut(int start, int end) {
        HashSet<Edge> min_cut = new HashSet<Edge>();
        HashSet<Integer> cs = bfs2(start);
        if (cs.contains(end)) {
            return null;
        }

        for (Integer v : cs) {
            for (Integer u : graph.get(v).keySet()) {
                if (!cs.contains(u)) {
                    min_cut.add(new Edge(v, u));
                }
            }
        }

        return min_cut;
    }

    public static void main(String[] args) {
        graph = new HashMap<Integer, HashMap<Integer, EdgeData>>();
        graph.put(1, new HashMap<Integer, EdgeData>());
        graph.put(2, new HashMap<Integer, EdgeData>());
        graph.put(3, new HashMap<Integer, EdgeData>());
        graph.put(4, new HashMap<Integer, EdgeData>());
        graph.put(5, new HashMap<Integer, EdgeData>());
        graph.put(6, new HashMap<Integer, EdgeData>());
        graph.get(1).put(2, new EdgeData(1, 2, 8));
        graph.get(1).put(3, new EdgeData(1, 3, 10));
        graph.get(2).put(4, new EdgeData(2, 4, 2));
        graph.get(2).put(5, new EdgeData(2, 5, 3));
        graph.get(3).put(5, new EdgeData(3, 5, 4));
        graph.get(4).put(6, new EdgeData(4, 6, 5));
        graph.get(5).put(6, new EdgeData(5, 6, 7));
        System.out.println(flow(1, 6));
        System.out.println(Arrays.toString(mincut(1, 6).toArray(new Edge[0])));
    }
}
