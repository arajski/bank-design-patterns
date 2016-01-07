package bank.two;

public class Transfer implements Command {
    private Account srcAccount;
    private Account dstAccount;
    private boolean isExecuted;
    private int amount;

    public Transfer(Account srcAccount, Account dstAccount, int amount) {
        this.srcAccount = srcAccount;
        this.dstAccount = dstAccount;
        this.amount = amount;
        this.isExecuted = false;
    }

    public Account getSrcAccount() {
        return this.srcAccount;
    }

    public Account getDstAccount() {
        return this.dstAccount;
    }

    @Override
    public void execute() {
        if(!this.isExecuted){
            this.srcAccount.reduceBalance(this.amount);
            this.isExecuted = true;
        }
        else {
            this.dstAccount.increaseBalance(this.amount);
        }

    }
}
