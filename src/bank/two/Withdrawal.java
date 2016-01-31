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
        try {
            this.dstAccount.reduceBalance(this.amount);
        } catch (AccountException e) {
            System.out.print(e);
        }
    }
}
