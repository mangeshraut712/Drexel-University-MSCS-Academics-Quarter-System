class Currency {
	String description = "Unknown Currency";

	public String getCurrencyDescription() {
		return description;
	}

	public void bills() {
		double[] value = { 1.00, 2.00, 5.00, 10.00, 50.00, 100.00 };
		for (int i = 0; i < value.length; i++) {
			System.out.println("US Currency bills: " + value[i]);
		}
	}

	public void coins() {
		double[] coins = { 0.1, 0.5, 0.10, 0.25 };
		for (int i = 0; i < coins.length; i++) {
			System.out.println("US Currency coins: " + coins[i]);
		}

	}

	public class UKCurrency extends Currency {

		public UKCurrency() {
			description = "UK Currency";
		}

		@Override
		public void bills() {
			double[] value = { 5.00, 10.00, 20.00, 50.00 };
			for (int i = 0; i < value.length; i++) {
				System.out.println("UK Currency bills: " + value[i]);
			}
		}

		@Override
		public void coins() {
			double[] coins = { 0.1, 0.2, 0.5, 0.10, 0.20, 0.50, 1.0, 2.0 };
			for (int i = 0; i < coins.length; i++) {
				System.out.println("UK Currency coins: " + coins[i]);
			}

		}

	}

	public class IndianCurrency extends Currency {

		public IndianCurrency() {
			description = "Indian Currency";
		}

		@Override
		public void bills() {
			double[] value = { 10.00, 20.00, 50.00, 100.00, 200.0, 500.0, 2000.0 };
			for (int i = 0; i < value.length; i++) {
				System.out.println("Indian Currency bills: " + value[i]);
			}
		}

		@Override
		public void coins() {
			double[] coins = { 1.0, 2.0, 5.0, 10.0 };
			for (int i = 0; i < coins.length; i++) {
				System.out.println("Indian Currency coins: " + coins[i]);
			}
		}

	}

	public static void main(String args[]) {
		Currency c = new Currency();
		Currency u = c.new UKCurrency();
		Currency r = c.new IndianCurrency();
		c.getCurrencyDescription();
		c.bills();
		c.coins();
		u.getCurrencyDescription();
		u.bills();
		u.coins();
		r.getCurrencyDescription();
		r.bills();
		r.coins();
	}
}
