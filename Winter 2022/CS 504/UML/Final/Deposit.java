public class Deposit extends Cookie implements Currency {

	Deposit(double cookieTotal) {
		super(cookieTotal);
	}

	@Override
	public int currencyAmount() {
		return 0;
	}

	@Override
	public Double getCurrencyDetails() {
		return null;
	}

	@Override
	double calculateBaseValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	double calculateContentValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	double calculateShapeValue() {
		// TODO Auto-generated method stub
		return 0;
	}

}