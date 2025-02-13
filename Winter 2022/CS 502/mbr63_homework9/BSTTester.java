//Mark Boady
//Drexel 2021

//Libraries
import java.util.Random;

//Tests for Binary Search Tree
class BSTTester {
	// Set up the random number generator
	public static final Random RAND = new Random(System.currentTimeMillis());

	// Run some tests!
	public static void main(String[] argv) {
		// First we do some simple examples.
		BST B = new BST();
		// Insert some numbers to make an expected tree
		test_insert(6, B);
		test_insert(4, B);
		test_insert(1, B);
		test_insert(10, B);
		test_insert(8, B);
		test_insert(5, B);
		test_insert(12, B);
		// Check the height
		System.out.printf("The Tree Height is %d\n", B.height());
		// Check find
		for (int i = 0; i < 13; i++) {
			System.out.printf("Is %d in the tree?\n", i);
			System.out.println(B.find(i));
//			if (B.find(i) == false) {
//				System.out.println(B.find(i));
//			} else {
//				System.out.println("1");
//			}
		}
		// Delete the values in order
		test_delete(6, B);
		test_delete(4, B);
		test_delete(1, B);
		test_delete(10, B);
		test_delete(8, B);
		test_delete(5, B);
		test_delete(12, B);

		// Implement this once you have
		// Everything else working
		run_experiment();
		return;
	}

	public static void test_insert(int v, BST B) {
		B.insert(v);
		System.out.printf("Tree After Insert of %d\n", v);
		System.out.println("Inorder:");
		B.inorder();
		System.out.println("Preorder:");
		B.preorder();
		System.out.println("Postorder:");
		B.postorder();
	}

	public static void test_delete(int v, BST B) {
		B.delete_from_tree(v);
		System.out.printf("Tree After Delete of %d\n", v);
		System.out.println("Inorder:");
		B.inorder();
		System.out.println("Preorder:");
		B.preorder();
		System.out.println("Postorder:");
		B.postorder();
	}

	// ****Time to Run Your Experiment
	public static void run_experiment() {
		System.out.println("Experiments");
		System.out.println("|   N  |   T1  |   T2  |   T3  |   T4  |   T5  |  Average |");
		// Print out the test results
		int n = 2;
		for (int i = 1; i < 11; i++) {
			// Print the Row
			System.out.printf("| %5d", n);
			// Run 5 Tests
			float avg = 0;
			for (int j = 0; j < 5; j++) {
				int res = single_experiment(n);
				System.out.printf("| %6d", res);
				avg = avg + (res);
			}
			System.out.printf("| %9f", (avg / 5));
			// End
			System.out.println("|");
			n = n * 2;
		}
	}

	// Have this function run 1 test
	// Generate a random tree with n elements
	// and return it's height
	// This function should insert n random numbers
	// into a BST.
	// Then return the height of the tree
	public static int single_experiment(int n) {
		// Generate n random integers
		// Insert into a new tree
		// return the height of the tree
		// height(empty) = 0
		// height(tree) = 1 + max(height(tree.left), height(tree.right))
		// 2√πn
		BST N = new BST();
		Random randomNumber = new Random();
		int finalResult = 0;
		int max_limit = 1024;
		for (int i = 0; i < n; i++) {
			finalResult = randomNumber.nextInt(max_limit);
			N.insert(finalResult);
		}
		return N.height();

	}
}
