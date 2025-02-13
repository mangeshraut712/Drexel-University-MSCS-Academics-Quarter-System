public class CreateDoublyLinkedList {

	class Node {
		String data;
		Node prev;
		Node next;

		public Node(String data) {
			this.data = data;
		}
	}

	Node head = null;
	Node tail = null;

	public void addNewNode(String data) {

		Node newNode = new Node(data);

		if (head == null) {
			head = newNode;
			tail = newNode;
			head.prev = null;
			tail.next = null;
		} else {

			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
			tail.next = null;
		}
	}

	public void showData() {
		Node current = head;
		if (head == null) {
			System.out.println("List is empty");
			return;
		}
		while (current != null) {
			System.out.print(current.data + "\n");
			current = current.next;
		}
	}

	public void frontbackData() {
		Node current = head;
		if (head == null) {
			System.out.println("List is empty");
			return;
		}
		while (current != null) {
			if (current.prev == null) {
				System.out.print("\nFront of current Student is NULL\n");
			} else {
				System.out.print("Front of current Student Name:" + current.prev.data + "\n");
			}

			if (current.next == null) {
				System.out.print("Back of current Student is NULL\n");
			} else {
				System.out.print("Back of current Student Name:" + current.next.data + "\n");
			}

			System.out.print("Current Student Name:" + current.data + "\n\n");
			current = current.next;
		}
	}

	public void sortList() {
		Node current = null, index = null;
		String temp;
		if (head == null) {
			return;
		} else {
			for (current = head; current.next != null; current = current.next) {
				for (index = current.next; index != null; index = index.next) {
					if ((current.data).compareTo(index.data) > 0) {
						temp = current.data;
						current.data = index.data;
						index.data = temp;
					}
				}
			}
		}
	}

	public static void main(String[] args) {

		CreateDoublyLinkedList obj = new CreateDoublyLinkedList();

		System.out.println("\nOriginal list: \n");
		obj.addNewNode("Kapoor Akshay");
		obj.addNewNode("Raut Mangesh");
		obj.addNewNode("Bhave Vedant");
		obj.addNewNode("Vora Kartik");
		obj.addNewNode("Vaddanam Gowtham");
		obj.showData();
		obj.sortList();
		System.out.println("\nSorted list: \n");
		obj.showData();
		obj.frontbackData();

	}
}