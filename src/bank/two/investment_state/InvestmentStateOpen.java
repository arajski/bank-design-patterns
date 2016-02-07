package bank.two.investment_state;

public class InvestmentStateOpen implements InvestmentState {

    @Override
    public int terminate(InvestmentStateContext context) {
        return 0;
    }

    @Override
    public void stepOver() {
        System.out.println("Error, Undefined length");
    }
}
