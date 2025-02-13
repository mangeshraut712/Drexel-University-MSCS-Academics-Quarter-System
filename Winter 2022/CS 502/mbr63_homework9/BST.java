//Mark Boady
//Drexel 2021

//A Simple Binary Search Tree
class BST {
	// We need to know where the root node is
	protected Node root;

	// Make a new Binary Search Tree
	public BST() {
		this.root = null;
	}

	// This function returns a pointer to the root node
	// It will be useful to implement later functions
	// You may assume tree is not null
	public Node getRoot() {
		return this.root;
	}

	// Primary Operations
	// Insert value into tree
	public void insert(int value) {
		this.root = insert_value(value, this.root);
	}

	// Find a value in the tree
	public boolean find(int value) {
		return find_value(value, this.root);
	}

	// Delete a value
	public void delete_from_tree(int value) {
		this.root = delete_value(value, this.root);
	}

	// Find the min of the tree
	public int min() {
		return find_min(this.root);
	}

	// Prints
	// These will print directly to System.out.
	// Print Inorder
	public void inorder() {
		inorder_walker(this.root);
		System.out.println();
	}

	// Prints Preorder
	public void preorder() {
		preorder_walker(this.root);
		System.out.println();
	}

	// Prints Postorder
	public void postorder() {
		postorder_walker(this.root);
		System.out.println();
	}

	// Determine the height
	int height() {
		return node_height(this.root);
	}

	// --------------------------------------------
	// --------Homework Implementation ------------
	// --------------------------------------------
	// You have to start implementing from here down

	// Do a walk starting at the node given
	// Print the results to System.out
	// Print N for nulls
	// Print a space after each value
	// This one is given as a template
	protected void inorder_walker(Node current) {
		if (current != null) {
			inorder_walker(current.left);
			System.out.print(current.value);
			System.out.print(" ");
			inorder_walker(current.right);
		} else {
			System.out.print("N ");
		}
	}

	protected void preorder_walker(Node current) {
		if (current != null) {
			System.out.print(current.value);
			System.out.print(" ");
			preorder_walker(current.left);
			preorder_walker(current.right);
		} else {
			System.out.print("N ");
		}
	}

	protected void postorder_walker(Node current) {
		if (current != null) {
			postorder_walker(current.left);
			postorder_walker(current.right);
			System.out.print(current.value);
			System.out.print(" ");
		} else {
			System.out.print("N ");
		}
	}
	// End of Walks

	// Insert Helper
	protected Node insert_value(int v, Node current) {
		if (current == null) {
			Node newNode = new Node();
			newNode.value = v;
			newNode.left = null;
			newNode.right = null;
			return newNode;
		}
		if (current.value == v) {
			return current;
		}
		if (current.value > v) {
			current.left = insert_value(v, current.left);
			return current;
		}
		current.right = insert_value(v, current.right);

		return current;
	}

	// Find Helper
	protected boolean find_value(int value, Node current) {
		if (current == null) {
			return false;
		}
		if (current.value == value) {
			return true;
		}
		if (current.value > value) {
			return find_value(value, current.left);
		}

		return find_value(value, current.right);
	}

	// Find the min starting at node current
	// Return -1 on current is null
	protected int find_min(Node current) {
		if (current == null) {
			return -1;
		}
		if (current.left == null) {
			return current.value;
		}
		return find_min(current.left);
	}

	// Find Height of a Node
	// Hint: The height of a null is -1
	protected int node_height(Node current) {
		if (current == null) {
			return -1;
		}
		int leftHeight = node_height(current.left);
		int rightHeight = node_height(current.right);
		if (leftHeight > rightHeight) {
			return leftHeight + 1;
		}

		return rightHeight + 1;
	}

	// Delete Helper
	// I will provide you with delete
	// It only works if your find_min is correct
	protected Node delete_value(int v, Node current) {
		if (current == null) {
			return null;
		} else if (current.value == v) {
			Node x = delete_node(current);
			return x;
		} else if (current.value > v) {
			current.left = delete_value(v, current.left);
			return current;
		} else {
			current.right = delete_value(v, current.right);
			return current;
		}
	}

	protected Node delete_node(Node current) {
		if (current.left == null) {
			return current.right;
		}
		if (current.right == null) {
			return current.left;
		}
		int minval = find_min(current.right);
		current.value = minval;
		current.right = delete_value(minval, current.right);
		return current;
	}
}// End of Class