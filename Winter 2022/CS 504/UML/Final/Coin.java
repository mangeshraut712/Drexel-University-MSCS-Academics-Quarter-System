public class Coin implements Currency {

	int penny, nickel, dime, quarter;

	public Coin(int penny, int nickel, int dime, int quarter) {
		this.penny = penny;
		this.nickel = nickel;
		this.dime = dime;
		this.quarter = quarter;
	}

	public static int[] coinValue(String coins) {
		String[] numberCoinsInText = coins.split(",");
		int[] result = new int[numberCoinsInText.length];
		for (int index = 0; index < numberCoinsInText.length; index++) {
			result[index] = Integer.parseInt(numberCoinsInText[index]);
		}
		return result;
	}

	@Override
	public int currencyAmount() {
		this.penny = 1;
		this.nickel = 5;
		this.dime = 10;
		this.quarter = 25;
		return currencyAmount();
	}

	@Override
	public Double getCurrencyDetails() {
		// TODO Auto-generated method stub
		return null;
	}

}
