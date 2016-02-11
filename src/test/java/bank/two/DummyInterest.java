package bank.two;

public class DummyInterest implements Interest {

	@Override
	public double calculate(double amount) {
		return 1;
	}

	@Override
	public void setRate(double rate) {
	}

	@Override
	public double getRate() {
		return 0;
	}

}
