package bank.two;

public class Deposit implements Command {

    private Account dstAccount;
    private int amount;

    public Deposit(Account dstAccount, int amount) {
        this.dstAccount = dstAccount;
        this.amount = amount;
    }

    @Override
    public void execute() {
        this.dstAccount.increaseBalance(this.amount);
    }
}
