import java.util.*;

public class Topo {
    public static HashMap<Integer, HashSet<Integer>> graph;

    public static class Result {
        public ArrayList<Integer> order;
        public boolean dag;
        public Result() {
            order = new ArrayList<Integer>();
            dag = true;
        }
    }

    public static Result topo() {
        Result r = new Result();

        HashMap<Integer, HashSet<Integer>> rev_graph = new HashMap<Integer, HashSet<Integer>>();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int u : graph.keySet()) {
            rev_graph.put(u, new HashSet<Integer>());
            pq.add(u);
        }
        for (int u : graph.keySet()) {
            for (int v : graph.get(u)) {
                rev_graph.get(v).add(u);
                pq.remove(v);
            }
        }

        while (!pq.isEmpty()) {
            int u = pq.poll();
            r.order.add(u);
            for (int v : graph.get(u)) {
                rev_graph.get(v).remove(u);
                if (rev_graph.get(v).size() == 0) {
                    pq.add(v);
                }
            }
        }

        if (r.order.size() != graph.keySet().size()) {
            r.dag = false;
        }

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
        graph.get(1).add(3);
        graph.get(3).add(2);
        graph.get(3).add(4);
        graph.get(4).add(5);
        graph.get(5).add(6);
        Result r = topo();
        System.out.println(r.dag);
        for (int v : r.order) {
            System.out.println(v);
        }
    }

}
