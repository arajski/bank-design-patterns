package bank.two.investment_state;

public class InvestmentStateActive implements InvestmentState {

    private int length = 0;
    private InvestmentStateContext context;

    public InvestmentStateActive(int lenght, InvestmentStateContext context) {
        this.length = lenght;
        this.context = context;
    }

    @Override
    public int terminate(InvestmentStateContext context) {
        return 0;
    }

    @Override
    public void stepOver() {
        if(length > 0) {
            length --;
            return;
        }
        context.setState(new InvestmentStateFinished());
    }
}
