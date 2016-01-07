package bank.two;

public class Account {
    private Bank bank;
    private int number;
    private int balance;

    private void executeCommand(Command newCommand) {
        newCommand.execute();
    }

    public Account(Bank bank, int number) {
        this.bank = bank;
        this.number = number;
        this.balance = 0;
    }

    public int getAccountBankCode() {
        return bank.getBankCode();
    }
    public int getBalance() {
        return this.balance;
    }
    public int getNumber() {
        return this.number;
    }
    public void depositMoney(int amount) {
        executeCommand(new Deposit(this, amount));
    }
    public void withdrawMoney(int amount) {
        executeCommand(new Withdrawal(this,amount));
    }
    public void transferMoney(Account dstAccount,int amount) {
        Transfer transfer = new Transfer(this, dstAccount, amount);
        executeCommand(transfer);
        bank.addTransferToSession(transfer);
    }
    public void increaseBalance(int amount) {
        this.balance += amount;
    }
    public void reduceBalance(int amount) {
        if(this.balance-amount < 0) {
            this.balance = 0;
        }
        else {
            this.balance -= amount;
        }
    }

}
