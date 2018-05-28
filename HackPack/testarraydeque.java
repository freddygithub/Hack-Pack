// Arup Guha
// 1/16/2018
// Use of ArrayDeque

import java.util.*;

public class testarraydeque {

	public static void main(String[] args) {

		ArrayDeque<Integer> myad = new ArrayDeque<Integer>();

		// Add stuff. 12, 7, 3, 5, 11
		myad.addFirst(3);
		myad.addFirst(7);
		myad.addLast(5);
		myad.addFirst(12);
		myad.addLast(11);

		// Remove in order from front to back.
		while (myad.size() > 0)
			System.out.println(myad.pollFirst());


	}
}