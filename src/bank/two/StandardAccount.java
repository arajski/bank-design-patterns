package bank.two;

public class StandardAccount implements Account {
    private Bank bank;
    private int number;
    private int balance;

    private void executeCommand(Command newCommand) {
        newCommand.execute();
    }

    public StandardAccount(Bank bank, int number) {
        this.bank = bank;
        this.number = number;
        this.balance = 0;
    }
    @Override
    public Bank getBank() {
        return bank;
    }
    @Override
    public int getAccountBankCode() {
        return bank.getBankCode();
    }
    @Override
    public int getBalance() {
        return this.balance;
    }
    @Override
    public int getNumber() {
        return this.number;
    }
    @Override
    public void depositMoney(int amount) {
        executeCommand(new Deposit(this, amount));
    }
    @Override
    public void withdrawMoney(int amount) {
        executeCommand(new Withdrawal(this,amount));
    }
    @Override
    public void transferMoney(Account dstAccount,int amount) {
        Transfer transfer = new Transfer(this, dstAccount, amount);
        executeCommand(transfer);
        if(transfer.checkStatus()) {
            bank.addTransferToSession(transfer);
        }
    }
    @Override
    public void increaseBalance(int amount) {
        this.balance += amount;
    }
    @Override
    public void reduceBalance(int amount) throws AccountException{
        if(this.balance-amount < 0) {
            throw new AccountException("Insufficient funds");
        }
        else {
            this.balance -= amount;
        }
    }

}
