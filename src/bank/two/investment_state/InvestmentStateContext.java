package bank.two.investment_state;

public class InvestmentStateContext {
    private InvestmentState state;
    private int amount = 0;
    private int length = 0;

    public InvestmentStateContext(int amount) {
        this.amount = amount;
        state = new InvestmentStateOpen();
    }

    public void setState(InvestmentState state) {
        this.state = state;
    }

    public void setLength(int length) {
        int minimumInvestmentPeriod = 3;
        if(length >= minimumInvestmentPeriod) {
            this.length = length;
            this.state = new InvestmentStateActive(length, this);
        }
    }

    public void stepOver() {
        state.stepOver();
    }

    public int getMoney() {
        if(!(state instanceof InvestmentStateFinished)) {
            state = new InvestmentStateTerminated();
        }
        int earnedMoney = state.terminate(this);
        return amount + earnedMoney;
    }

    public InvestmentState getState() {
        return this.state;
    }

    public int getLength() {
        return this.length;
    }

    public int getAmount() {
        return this.amount;
    }
}
