package bank.two;

public class Deposit implements Command {

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
}
