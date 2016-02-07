package bank.two.investment_state;

public class InvestmentStateTerminated implements InvestmentState {
    @Override
    public int terminate(InvestmentStateContext context) {
        return 0;
    }

    @Override
    public void stepOver() {

    }
}
