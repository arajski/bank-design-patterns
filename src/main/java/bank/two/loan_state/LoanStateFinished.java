package bank.two.loan_state;

public class LoanStateFinished implements LoanState {

    @Override
    public int terminate(LoanStateContext context) {
    	return 0;
    }

    @Override
    public void stepOver() {

    }
}
