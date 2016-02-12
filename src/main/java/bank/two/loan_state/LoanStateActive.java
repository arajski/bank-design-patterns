package bank.two.loan_state;

public class LoanStateActive implements LoanState {

    private LoanStateContext context;

    public LoanStateActive(LoanStateContext context) {
        this.context = context;
    }

    @Override
    public int terminate(LoanStateContext context) {
        return 0;
    }

    @Override
    public void stepOver() {
        if(context.getCurrentLength() > 0) {
        	context.currentLength--;
        	context.totalPaidAmount += (context.getInterest().calculate(context.monthlyRate) + context.monthlyRate);
            return;
        }
        context.setState(new LoanStateFinished());
    }
}
