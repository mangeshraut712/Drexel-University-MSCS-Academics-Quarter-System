
public class RandomArrayTester {
	public static void main(String[] args) {
		RandomArray ar = new RandomArray();
		int[] size = new int[100];
		int[][] twod = new int[100][2];
		int[] his;
		ar.zeroandoneArray(size);
		ar.diceArray(size);
		his = ar.twodiceArray(twod);
		ar.histogram(his);
	}

}
