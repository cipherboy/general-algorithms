
import java.util.Scanner;

public class TravellingSalesman {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int trials = scan.nextInt();
		for (int t = 0; t < trials; t++) {
			int x = scan.nextInt(), y = scan.nextInt();
			int startX = scan.nextInt(), startY = scan.nextInt();
			int numBeeps = scan.nextInt();

			int[] beepX = new int[numBeeps + 1];
			int[] beepY = new int[numBeeps + 1];
			beepX[0] = startX;
			beepY[0] = startY;
			for (int i = 1; i <= numBeeps; i++) {
				beepX[i] = scan.nextInt();
				beepY[i] = scan.nextInt();
			}

			int[][] graph = new int[numBeeps + 1][numBeeps + 1];
			for (int i = 0; i < numBeeps + 1; i++) {
				for (int j = 0; j < numBeeps + 1; j++) {
					graph[i][j] = Math.abs(beepX[i] - beepX[j]) + Math.abs(beepY[i] - beepY[j]);
				}
			}

			int answer = travelingSalesman(graph);
			System.out.println("The shortest path has length " + answer);
		}
	}

	/**
	 * DP solution to the travelling Salesman problem (find the minimum
	 * Hamiltonian path in a graph) DP reduces this to a time complexity of
	 * O(n^2*2^n) from O(n!)
	 * 
	 * @param graph
	 * @return
	 */
	public static int travelingSalesman(int[][] graph) {
		// set to construct subsets from. These subsets are used to construct
		// bitmasks setS = {1, 2, ..., graph.length-1} as we do not want to
		// prematurely return to the start (vertex 0)
		int[] setS = new int[graph.length - 1];
		for (int i = 1; i < graph.length; i++) {
			setS[i - 1] = i;
		}

		// DP structure: tsp[k][S] is the minimum distance from vertex 0 to
		// vertex k using all vertices in the set S. S is represented as a
		// bitmask of vertices.
		int[][] tsp = new int[graph.length][1 << graph.length];
		// Base Case: the length from vertex 0 to vertex k, using only the kth
		// vertex is graph[0][k]
		for (int k = 1; k < graph.length; k++) {
			tsp[k][1 << k] = graph[0][k];
		}

		// iterate over the size of the subset of vertices we have visted
		for (int size = 2; size < graph.length; size++) {
			// create the indexes of the subset
			int[] indexes = new int[size];
			for (int i = 0; i < size; i++) {
				indexes[i] = i;
			}

			do {
				// create a bitmask from the subset S represented by the indexes
				int mask = 0;
				for (int index : indexes) {
					mask |= (1 << setS[index]);
				}

				for (int index : indexes) {
					// choose an element k in S
					int k = setS[index];
					// the distance from 0 to k using all vertices in S is
					// min(tsp[m][S-{k}] + graph[m][k]) for all vertices m in S
					int min = Integer.MAX_VALUE;
					for (int index2 : indexes) {
						int m = setS[index2];
						if (k != m) {
							min = Math.min(min, tsp[m][mask ^ (1 << k)] + graph[m][k]);
						}
					}
					tsp[k][mask] = min;
				}
				// iterate (see below)
			} while (updateIndexes(indexes, setS.length));
		}

		int result = Integer.MAX_VALUE;
		for (int k = 1; k < graph.length; k++) {
			result = Math.min(result, tsp[k][(1 << graph.length) - 2] + graph[k][0]);
		}

		return result;
	}

	/**
	 * iterate through multiple indexes of a variable size. Each index is
	 * strictly larger than the one that came before. Iterates through these
	 * indexes in lexicographic order
	 * 
	 * @param indexes
	 * @param limit
	 * @return
	 */
	public static boolean updateIndexes(int[] indexes, int limit) {
		int i = 0;
		// start by updating lowest index
		indexes[i]++;
		// if we have hit the index above it, we need to update the next index
		while (i < indexes.length - 1 && indexes[i] == indexes[i + 1]) {
			if (i == 0) {
				// we need to drop down. No lower limit other than 0
				indexes[i] = 0;
			} else {
				// we need to drop down, but have to be greater than previous index
				indexes[i] = indexes[i - 1] + 1;
			}
			i++;
			indexes[i]++;
		}

		// if the last index is the limit, we are done iterating
		return indexes[i] != limit;
	}
}
