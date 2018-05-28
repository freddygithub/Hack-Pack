// Arup Guha
// 8/31 - 9/2/2011
// Solution to 2011 UCF Locals Contest Problem: Campout
// Edited on 3/10/2017 to use FordFulkerson code shown in class.

import java.io.*;
import java.util.*;

public class campout {
	
	final public static int NUMSTUD = 10;
	final public static int NUMSHIFTS = 42;
	final public static int N = NUMSTUD+NUMSHIFTS;

	public static void main(String[] arg) throws Exception {

		Scanner fin = new Scanner(System.in);
		int numCases = fin.nextInt();

		// Go through each case.
		for (int caseNum=1; caseNum<=numCases; caseNum++) {

			// Create flow object, adding in capacities based on the problem (20 shifts max per student,
			// 3 students for each shift minimum.)
			FordFulkerson net = new FordFulkerson(N);
			for (int i=0; i<NUMSTUD; i++) net.add(N,i,20);
			for (int i=0; i<NUMSHIFTS; i++) net.add(NUMSTUD+i,N+1,3);
			
			// Read in all 10 students' info.
			for (int stud=0; stud<10; stud++) {

				// Initialize each slot so that this student can cover it.
				boolean[] cancover = new boolean[NUMSHIFTS];
				for (int i=0; i<NUMSHIFTS; i++)
					cancover[i] = true;

				int numConflicts = fin.nextInt();

				// Go through each conflict.
				for (int con=0; con<numConflicts; con++) {

					int d = fin.nextInt();
					int s = fin.nextInt();
					int e = fin.nextInt();

					// Each time maps to one of the time ranges, so we just go through these and make them false.
					for (int i=(d-1)*6+s/4; i<(d-1)*6+(e+3)/4; i++)
						cancover[i] = false;
				}

				// Fill in shifts student can do.
				for (int i=0; i<NUMSHIFTS; i++)
					if (cancover[i])
						net.add(stud, NUMSTUD+i, 1);
			}

			// Get the flow of this network.
			int maxflow = net.ff();

			System.out.print("Case #"+caseNum+": ");

			// This means all of 42 shifts were covered by at least 3 students each.
			if (maxflow == 126)
				System.out.println("YES");
			else
				System.out.println("NO");

			System.out.println();

		}
	}

}

class FordFulkerson {

	// Stores graph.
	public int[][] cap;
	public int n;
	public int source;
	public int sink;

	// "Infinite" flow.
	final public static int oo = (int)(1E9);

	// Set up default flow network with size+2 vertices, size is source, size+1 is sink.
	public FordFulkerson(int size) {
		n = size + 2;
		source = n - 2;
		sink = n - 1;
		cap = new int[n][n];
	}

	// Adds an edge from v1 -> v2 with capacity c.
	public void add(int v1, int v2, int c) {
		cap[v1][v2] = c;
	}

	// Wrapper function for Ford-Fulkerson Algorithm
	public int ff() {

		// Set visited array and flow.
		boolean[] visited = new boolean[n];
		int flow = 0;

		// Loop until no augmenting paths found.
		while (true) {

			// Run one DFS.
			Arrays.fill(visited, false);
			int res = dfs(source, visited, oo);

			// Nothing found, get out.
			if (res == 0) break;

			// Add this flow.
			flow += res;
		}

		// Return it.
		return flow;
	}

	// DFS to find augmenting math from v with maxflow at most min.
	public int dfs(int v, boolean[] visited, int min) {

		// got to the sink, this is our flow.
		if (v == sink)  return min;

		// We've been here before - no flow.
		if (visited[v])  return 0;

		// Mark this node and recurse.
		visited[v] = true;
		int flow = 0;

		// Just loop through all possible next nodes.
		for (int i = 0; i < n; i++) {

			// We can augment in this direction.
			if (cap[v][i] > 0)
				flow = dfs(i, visited, Math.min(cap[v][i], min));

			// We got positive flow on this recursive route, return it.
			if (flow > 0) {

				// Subtract it going forward.
				cap[v][i] -= flow;

				// Add it going backwards, so that later, we can flow back through this edge as a backedge.
				cap[i][v] += flow;

				// Return this flow.
				return flow;
			}
		}

		// If we get here there was no flow.
		return 0;
	}
}
