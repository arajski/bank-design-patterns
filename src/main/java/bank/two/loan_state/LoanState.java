package bank.two.loan_state;

public interface LoanState {

    int terminate(LoanStateContext context);
    void stepOver();
}
