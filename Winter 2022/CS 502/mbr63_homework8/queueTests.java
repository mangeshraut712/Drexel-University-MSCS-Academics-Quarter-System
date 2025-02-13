//Mark Boady
//Some Basic Queue Tests

//These working DOES NOT mean your code works.
//It just means you have no big obvious mistakes.

class queueTests {
	public static void main(String[] argv) {
		// Create a new Queue
		Queue Q = new Queue();
		System.out.println("If correct, the next line should say \"Queue Empty\"");
		Q.print();
		// We will now add Elements to the queue.
		// This will have a very predicable order.
		for (int i = 0; i < 10; i++) {
			System.out.printf("Adding %d to the end of the queue.\n", i);
			Q.enqueue(i);
			Q.print();
		}
		// Now lets delete all the values
		int predict = 0;
		while (Q.empty() == false) {
			System.out.printf("We expect Front and Dequeue to return %d\n", predict);
			System.out.printf("The current front of the Queue is %d\n", Q.front());
			int temp = Q.dequeue();
			System.out.printf("Dequeue returned %d\n", temp);
			Q.print();
			predict += 1;
		}
		return;
	}
}