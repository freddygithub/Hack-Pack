// Arup Guha
// 1/16/2018
// Example that shows how to custom sort arrays and lists in java.

/*** Input format: Line 1: n, number of candidates
 *                 Lines 2-(n+1): one name followed by number of votes that person got.
 *   Output format: Sorted list of vote getters from most to least. If two 
 *                  people get the same number of votes, break ties by alpha order.
 ***/
 
import java.util.*;

public class election1 {

	public static void main(String[] args) {

		Scanner stdin = new Scanner(System.in);
		int numCandidates = stdin.nextInt();

		// Read in all the candidates.
		candidate[] array = new candidate[numCandidates];
		ArrayList<candidate> list = new ArrayList<candidate>();
		for (int i=0; i<numCandidates; i++) {
			String name = stdin.next();
			int numVotes = stdin.nextInt();
			array[i] = new candidate(name, numVotes);
			list.add(array[i]);
		}

		// How to sort an array.
		Arrays.sort(array);

		for (int i=0; i<array.length; i++)
			System.out.println(array[i]);

		System.out.println();

		// Unsorted list.
		for (int i=0; i<list.size(); i++)
			System.out.println(list.get(i));

		// How to sort a collection.
		Collections.sort(list);

		System.out.println();

		// Unsorted list.
		for (int i=0; i<list.size(); i++)
			System.out.println(list.get(i));
	}
}

class candidate implements Comparable<candidate> {

	private String name;
	private int numVotes;

	public candidate(String n, int v) {
		name = n;
		numVotes = v;
	}

	public String toString() {
		return name+" "+numVotes;
	}

	public int compareTo(candidate other) {

		// We can break the tie by looking at votes, go most to least.
		if (this.numVotes != other.numVotes)
			return other.numVotes - this.numVotes;

		// Otherwise, for same number of votes, we go in alpha order.
		return this.name.compareTo(other.name);
	}
}