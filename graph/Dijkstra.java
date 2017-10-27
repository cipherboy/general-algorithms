import java.util.*;

public class Dijkstra {
    public static HashMap<Integer, HashMap<Integer, Integer>> graph;

    public static class WD implements Comparable<WD> {
        public int vertex;
        public long distance;
        public Integer parent;

        public WD(int v, long d) {
            this.vertex = v;
            this.distance = d;
            this.parent = null;
        }

        public WD(int v, long d, int p) {
            this.vertex = v;
            this.distance = d;
            this.parent = p;
        }

        public String toString() {
            return parent + "->" + vertex + " [" + distance + "]";
        }

        @Override
        public int compareTo(WD b) {
            return Long.compare(distance, b.distance);
        }
    }

    public static class Result {
        public HashMap<Integer, Integer> parents;
        public HashMap<Integer, Long> distances;
        public Long total_distance;

        public Result() {
            parents = new HashMap<Integer, Integer>();
            distances = new HashMap<Integer, Long>();
            total_distance = -1L;
        }
    }

    public static Result dijkstra(int start, int end) {
        Result r = new Result();
		PriorityQueue<WD> pq = new PriorityQueue<WD>();
        r.parents.put(start, null);
        r.distances.put(start, 0L);
        r.total_distance = -1L;
        pq.add(new WD(start, 0L));

		while (!pq.isEmpty()) {
			WD u = pq.poll();
            if (r.distances.get(u.vertex) != null && r.distances.get(u.vertex) < u.distance) {
                continue;
            }

            r.parents.put(u.vertex, u.parent);
            r.distances.put(u.vertex, u.distance);

            if (u.vertex == end) {
                r.total_distance = u.distance;
                return r;
            }

			for (Integer v : graph.get(u.vertex).keySet()) {
                long nd = u.distance + graph.get(u.vertex).get(v);
                if (!r.distances.containsKey(v)) {
                    r.distances.put(v, nd);
                    pq.add(new WD(v, nd, u.vertex));
                } else if (r.distances.get(v) > nd) {
                    r.distances.put(v, nd);
                    pq.add(new WD(v, nd, u.vertex));
                }
			}
		}

		return r;
	}

    public static void main(String[] args) {
        graph = new HashMap<Integer, HashMap<Integer, Integer>>();
        graph.put(1, new HashMap<Integer, Integer>());
        graph.put(2, new HashMap<Integer, Integer>());
        graph.put(3, new HashMap<Integer, Integer>());
        graph.put(4, new HashMap<Integer, Integer>());
        graph.put(5, new HashMap<Integer, Integer>());
        graph.put(6, new HashMap<Integer, Integer>());
        graph.get(1).put(2, 8);
        graph.get(1).put(3, 10);
        graph.get(2).put(4, 2);
        graph.get(2).put(5, 3);
        graph.get(3).put(5, 4);
        graph.get(4).put(6, 5);
        graph.get(5).put(6, 7);
        Result r = dijkstra(1, 6);
        System.out.println(r.total_distance);
        for (Integer k : r.distances.keySet()) {
            System.out.println(k + ": " + r.parents.get(k));
            System.out.println(k + ": " + r.distances.get(k));
        }
    }

}
