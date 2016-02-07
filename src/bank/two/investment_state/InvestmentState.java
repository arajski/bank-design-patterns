package bank.two.investment_state;

public interface InvestmentState {

    int terminate(InvestmentStateContext context);
    void stepOver();
}
