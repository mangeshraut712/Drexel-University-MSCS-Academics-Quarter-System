public class CoinBundle implements Currency {
	int oneUSD;
	int fiveUSD;

	public CoinBundle(int oneUSD, int fiveUSD) {
		this.oneUSD = oneUSD;
		this.fiveUSD = fiveUSD;
	}

	public static int[] coinBundleValue(String coins) {
		String[] numberCoinsInText = coins.split(",");
		int[] result = new int[numberCoinsInText.length];
		for (int index = 0; index < numberCoinsInText.length; index++) {
			result[index] = Integer.parseInt(numberCoinsInText[index]);
		}
		return result;
	}

	@Override
	public int currencyAmount() {
		this.oneUSD = 1;
		this.fiveUSD = 5;
		return currencyAmount();
	}

	@Override
	public Double getCurrencyDetails() {
		// TODO Auto-generated method stub
		return null;
	}

}
