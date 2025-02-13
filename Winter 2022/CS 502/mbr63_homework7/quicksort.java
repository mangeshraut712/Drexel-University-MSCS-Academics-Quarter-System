//Mark Boady - CS502
//Insertion Sort Experiment

//This function tests meregSort to see how fast it is in practice.

//Libraries
import java.util.Random;

public class quicksort {
	// Random Number Generator
	// A computer cannot get truely random numbers.
	// It can generate a sequence that looks random.
	// To start the sequence at an unpredicatable point, use current time
	public static final Random RAND = new Random(System.currentTimeMillis());

	// The main function runs all the tests.
	public static void main(String[] argv) {
		// How many random arrays to test at each size.
		int arrays_per_size = 10;
		int current_size = 8;
		int max_size = 131072;

		// Loop over different sizes
		while (current_size <= max_size) {
			// Run Tests and print results
			test_size(current_size, arrays_per_size);
			// Set up for next test
			current_size = current_size * 2;
		}
		// End the Main Function
		return;
	}

	// --------------Function Definitions Below This Line---------------
	// This function checks to see if an array is sorted.
	// It returns 1 if the array is sorted.
	// It returns 0 if the array is not sorted.
	public static boolean is_sorted(int[] Array, int size) {
		int i = 1;
		while (i < size) {
			if (Array[i] < Array[i - 1])
				return false;// Not Sorted!
			i = i + 1;
		}
		return true;
	}

	// This function gives you a random array.
	// It creates a new array of size given and returns the pointer to it.
	// The array will be have random numbers in it.
	public static int[] random_array(int size) {
		// We need to ask the computer for memory
		int[] Array = new int[size];
		int counter = 0;
		while (counter < size) {
			Array[counter] = RAND.nextInt(10 * size);
			counter = counter + 1;
		}
		return Array;
	}

	// This function runs multiple tests for a specific size.
	// It prints a summary of the results.
	public static void test_size(int size, int num_of_tests) {
		int tests_run = 0;
		int tests_passed = 0;
		// Clock Items for timing
		long start;
		long end;
		long time_used;
		long total_time = 0;// For computing average
		while (tests_run < num_of_tests) {
			// Generate a random array
			int[] my_random_array = random_array(size);
			// RUN THE TEST
			start = System.currentTimeMillis();
			quicksort(my_random_array, size);
			end = System.currentTimeMillis();
			// END OF TEST RUN
			// How long did it take?
			time_used = (end - start);// Convert to seconds
			total_time = total_time + time_used;
			// Did it actually sort?
			if (is_sorted(my_random_array, size)) {
				tests_passed = tests_passed + 1;
			}
			// Count that we ran a tests
			tests_run = tests_run + 1;
			// Delete From Memory
			my_random_array = null;// Java will delete now
			// Print out Results
		}
		System.out.printf("Test size: %d\n", size);
		System.out.printf("Tests Ran: %d\n", tests_run);
		System.out.printf("Tests Passed: %d\n", tests_passed);
		System.out.printf("Average Time to Sort: %f milliseconds.\n", ((double) total_time) / ((double) tests_run));
	}

	// -------------------------------------------------------
	// -------------------Quick Sort-------------------------
	// -------------------------------------------------------

	// Quick Sort
	// This is what we are actually tests
	// It returns void because it changes the array in place
	// See lecture for details

	// a utility function to swap two elements
	static void swap(int[] Array, int i, int j) {
		int temp = Array[i];
		Array[i] = Array[j];
		Array[j] = temp;
	}

	private static int partition(int[] Array, int start, int stop) {
		// select random index
		Random randInt = new Random();
		int randomIndex = randInt.nextInt(start, stop);
		// swap with last position
		swap(Array, stop, randomIndex);
		// partition based on last index pivot
		int pivot = Array[stop];
		int i = start;
		for (int j = start; j < stop; j++) {
			// swap a small and large value into place
			if (Array[j] < pivot) {
				swap(Array, i, j);
				i = i + 1;
			}
		}
		// put the pivot in place
		swap(Array, i, stop);
		// return index of pivot
		return i;
	}

	// Quick sort
	public static void quicksort(int[] Array, int size) {
		qsort(Array, 0, size - 1);
	}

	private static void qsort(int[] Array, int start, int stop) {
		if (start < stop) {
			int p = partition(Array, start, stop);
			// separately sort elements before partition and after partition
			qsort(Array, start, p - 1);
			qsort(Array, p + 1, stop);
		}
	}
}