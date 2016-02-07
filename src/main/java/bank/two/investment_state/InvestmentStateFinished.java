package bank.two.investment_state;

public class InvestmentStateFinished implements InvestmentState {

    @Override
    public int terminate(InvestmentStateContext context) {
        double percentage = (context.getLength() * 0.1) / 100;
        int payment = (int) (percentage * context.getAmount());
        return payment;
    }

    @Override
    public void stepOver() {

    }
}
