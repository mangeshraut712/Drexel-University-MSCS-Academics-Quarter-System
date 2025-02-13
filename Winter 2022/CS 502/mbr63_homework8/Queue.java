//A Queue Template by Mark Boady

//The Queue
public class Queue {

	// The Node Class
	class Node {

		public int value;
		public Node next;
	}

	private Node front;
	private Node back;

	public Queue() {
		// Do something?
		this.front = null;
		this.back = null;
	}

	// Print out the Queue
	public void print() {
		if (this.front == null) {
			System.out.println("Queue Empty");
		} else {
			System.out.print("FRONT -> ");
			Node current = this.front;
			while (current != null) {
				System.out.print(current.value);
				System.out.print(" -> ");
				current = current.next;
			}
			System.out.println("END");
		}
	}

	// What is at the front of the queue?
	public int front() {
		if (this.front == null) {
			return 0;
		}
		return this.front.value;
	}

	// Is the Queue empty? true=Yes false=No
	public boolean empty() {
		return this.front == null;
	}

	// Add a new item to the back of the Queue
	public void enqueue(int x) {
		Node newNode = new Node();
		newNode.value = x;
		newNode.next = null;
		if (this.front == null) {
			// this is first value
			this.front = newNode;
			back = newNode;

		} else {
			// add to the end
			back.next = newNode;
			back = newNode;
		}
		return;
	}

	// Remove an item from the front of the queue
	// Return the item you removed
	public int dequeue() {
		if (this.front == null)
			return -1;
		int temp = this.front.value;
		// move forward by 1 space
		this.front = this.front.next;
		// we just erased the tail
		if (this.front == null) {
			back = null;
		}
		return temp;
	}

}