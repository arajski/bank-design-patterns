package bank.two;

import bank.two.investment_state.InvestmentState;
import bank.two.investment_state.InvestmentStateContext;
import bank.two.reporting_visitor.ReportingObject;
import bank.two.reporting_visitor.ReportingVisitor;

public class Investment implements ReportingObject {
    private InvestmentStateContext context;

    public Investment(int amount) {
        this.context = new InvestmentStateContext(amount);
    }

    public void setLength(int length) {
        this.context.setLength(length);
    }

    public InvestmentState contextState() {
        return this.context.getState();
    }

    public int getAmount() {
        return this.context.getAmount();
    }

    public int getLength() {
        return this.context.getLength();
    }

    public InvestmentStateContext currentContext() {
        return this.context;
    }


    @Override
    public int objectValue() {
        return this.getAmount();
    }

    @Override
    public void addToVisitor(ReportingVisitor visitor) {
        visitor.visit(this);
    }
}
