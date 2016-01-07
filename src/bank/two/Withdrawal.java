package bank.two;

public class Withdrawal implements Command{

    private Account dstAccount;
    private int amount;

    public Withdrawal(Account dstAccount, int amount) {
        this.amount = amount;
        this.dstAccount = dstAccount;
    }

    @Override
    public void execute() {
        this.dstAccount.reduceBalance(this.amount);
    }
}
