//Mark Boady
//2021 Drexel University

//A full Heap Implementation

class Heap {
	// Data Structure Attributes
	protected int[] data;
	protected int max_size;
	protected int current_size;

	// Methods
	// Make a new Heap
	public Heap(int capacity) {
		data = new int[capacity];
		current_size = 0;
		max_size = capacity;
	}

	// Is the heap empty?
	public boolean empty() {
		return current_size == 0;
	}

	// What is the min?
	public int min() {
		if (empty()) {
			return 0;
			// the min is always at the root
		}
		return data[0];
	}

	// Get Index of Parent
	protected int parent(int n) {
		if (n == 0) {
			return -1;
		}

		return (n - 1) / 2;
	}

	// Get index of Left Child
	protected int left_child(int n) {
		return 2 * n + 1;
	}

	// Get index of Right Child
	protected int right_child(int n) {
		return 2 * n + 2;
	}

	// Swap i and j in heap
	protected void swap(int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
		return;
	}

	// Just to help debug
	// You can have this do whatever you want.
	// We suggest you have a way to print the heap
	// So you can test it when you get errors
	public void print_heap() {
		System.out.println("A Heap!");
		System.out.printf("Heap Current Size: %d\n", this.current_size);
		System.out.printf("Heap Max Size: %d\n", this.max_size);
		System.out.printf("Contents:\n");
		for (int i = 0; i < this.current_size; i++) {
			System.out.printf("%d ", this.data[i]);
		}
		System.out.printf("\n");
	}

	// Insert a new value
	public void insert(int x) {
		if (current_size == max_size) {
			expand();
		}
		data[current_size] = x;
		current_size++;
		upheap(current_size - 1);
		return;
	}

	public void expand() {
		int newSize = 2 * current_size;
		int[] newData = new int[newSize];
		for (int i = 0; i < current_size; i++) {
			newData[i] = data[i];
		}
		data = newData;
		max_size = newSize;
		return;
	}

	// Upheap a value at index i
	protected void upheap(int i) {
		int p = parent(i);
		if (p < 0) {
			return;
		}
		int pVal = data[p];
		if (pVal <= data[i]) {
			return;
		}
		swap(i, p);
		upheap(p);
		return;
	}

	// Delete the min
	public void deletemin() {
		if (empty()) {
			return;
		}
		swap(0, current_size - 1);
		current_size = current_size - 1;
		downheap(0);
		return;
	}

	// Downheap a value at index i
	protected void downheap(int i) {
		int leftIndex = left_child(i);
		int rightIndex = right_child(i);
		if (leftIndex >= current_size) {
			return;
		}
		int minIndex = pickSmallerChild(leftIndex, rightIndex);
		if (data[i] > data[minIndex]) {
			swap(i, minIndex);
			downheap(minIndex);
		}
		return;
	}

	public int pickSmallerChild(int leftIndex, int rightIndex) {
		int minIndex;
		if (rightIndex >= current_size) {
			minIndex = leftIndex;
		} else {
			if (data[leftIndex] < data[rightIndex]) {
				minIndex = leftIndex;
			} else {
				minIndex = rightIndex;
			}
		}
		return minIndex;
	}

}