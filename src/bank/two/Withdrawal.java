package bank.two;

public class Withdrawal implements Command{

    private Account destinationAccount;
    private int amount;

    public Withdrawal(Account destinationAccount, int amount) {
        this.amount = amount;
        this.destinationAccount = destinationAccount;
    }

    @Override
    public void execute() {
        try {
            this.destinationAccount.reduceBalance(this.amount);
        } catch (AccountException e) {
            System.out.print(e);
        }
    }
}
