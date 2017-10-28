import java.util.*;

public class Prim {
    public static HashMap<Integer, HashMap<Integer, Integer>> graph;

    public static class WD implements Comparable<WD> {
        public int from;
        public int to;
        public int weight;

        public WD(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public String toString() {
            return from + "->" + to + " [" + weight + "]";
        }

        @Override
        public int compareTo(WD b) {
            return Integer.compare(weight, b.weight);
        }
    }

    public static class Result {
        public HashMap<Integer, HashMap<Integer, Integer>> graph;
        public int mst_weight;

        public Result() {
            graph = new HashMap<Integer, HashMap<Integer, Integer>>();
            mst_weight = 0;
        }
    }

    public static Result prim(int start) {
        Result r = new Result();

        PriorityQueue<WD> pq = new PriorityQueue<WD>();
        for (Integer v : graph.get(start).keySet()) {
            pq.add(new WD(start, v, graph.get(start).get(v)));
        }
        r.graph.put(start, new HashMap<Integer, Integer>());

        while (r.graph.keySet().size() != graph.keySet().size() && !pq.isEmpty()) {
            WD uwd = pq.poll();
            while (!pq.isEmpty() && r.graph.keySet().contains(uwd.to) && r.graph.keySet().contains(uwd.from)) {
                uwd = pq.poll();
            }

            if (r.graph.keySet().contains(uwd.to) && r.graph.keySet().contains(uwd.from)) {
                break;
            }

            int u = uwd.from;
            int v = uwd.to;
            r.mst_weight += uwd.weight;
            r.graph.get(u).put(v, uwd.weight);
            r.graph.put(v, new HashMap<Integer, Integer>());
            for (Integer vv : graph.get(v).keySet()) {
                if (!r.graph.keySet().contains(vv)) {
                    pq.add(new WD(v, vv, graph.get(v).get(vv)));
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
        Result r = prim(1);
        System.out.println(r.mst_weight);
        for (Integer u : r.graph.keySet()) {
            for (Integer v : r.graph.get(u).keySet()) {
                System.out.println(u + "-" + r.graph.get(u).get(v) + "->" + v);
            }
        }
    }

}
