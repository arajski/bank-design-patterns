package bank.two;

public class Transfer implements Command {
    private Account sourceAccount;
    private Account destinationAccount;
    private boolean isExecuted;
    private int amount;

    public Transfer(Account sourceAccount, Account destinationAccount, int amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
        this.isExecuted = false;
    }
    public boolean checkStatus() {
        return isExecuted;
    }
    public Account getSourceAccount() {
        return this.sourceAccount;
    }

    public Account getDestinationAccount() {
        return this.destinationAccount;
    }

    @Override
    public void execute() {
        if(!this.isExecuted){
            try {
                this.sourceAccount.reduceBalance(this.amount);
                this.isExecuted = true;
            } catch (AccountException e) {
                System.out.print(e);
            }
        }
        else {
            this.destinationAccount.increaseBalance(this.amount);
        }

    }
}
