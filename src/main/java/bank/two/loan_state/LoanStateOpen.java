package bank.two.loan_state;

public class LoanStateOpen implements LoanState {

    @Override
    public int terminate(LoanStateContext context) {
        return 0;
    }

    @Override
    public void stepOver() {
        System.out.println("Error, Undefined length");
    }
}
