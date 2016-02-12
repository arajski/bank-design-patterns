package bank.two;

public class LinearInterest implements Interest {

	double rate;
	
	@Override
	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public double calculate(double amount) {
		double calculatedInterest = amount * rate;
		return calculatedInterest;
	}

	@Override
	public double getRate(){
		return rate;
	}
}
