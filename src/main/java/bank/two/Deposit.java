package bank.two;

import bank.two.reporting_visitor.ReportingObject;
import bank.two.reporting_visitor.ReportingVisitor;

public class Deposit implements Command, ReportingObject {

    private Account destinationAccount;
    private int amount;

    public Deposit(Account destinationAccount, int amount) {
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    @Override
    public void execute() {
        this.destinationAccount.increaseBalance(this.amount);
    }


    @Override
    public int objectValue() {
        return this.amount;
    }

    @Override
    public void addToVisitor(ReportingVisitor visitor) {
        visitor.visit(this);
    }
}
