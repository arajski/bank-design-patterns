package bank.two;

public class DebtAccountDecorator extends AccountDecorator {

    private int debtLimit;
    private int debtBalance;

    public DebtAccountDecorator(Account account, int debtLimit) {
        super(account);
        this.debtLimit = debtLimit;
        this.debtBalance = 0;
    }

    private void executeCommand(Command newCommand) {
        newCommand.execute();
    }

    @Override
    public int getBalance() {
        if(debtBalance > 0) return (-1) * debtBalance;
        else return account.getBalance();
    }
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
            account.getBank().addTransferToSession(transfer);
        }
    }
    @Override
    public void increaseBalance(int amount){
        if(debtBalance > 0 && amount < debtBalance) {
            debtBalance -= amount;
            return;
        }
        else if (debtBalance > 0 && amount > debtBalance) {
            account.increaseBalance(amount-debtBalance);
            debtBalance = 0;
            return;
        }
        account.increaseBalance(amount);

    }
    private int calculateDebt(int balance, int amount) {
        if((balance - amount) >= 0) {
            return 0;
        }
        return (-1)*(balance-amount);
    }
    @Override
    public void reduceBalance(int amount) throws AccountException {
        int calculatedDebt = calculateDebt(account.getBalance(), amount);
        if (calculatedDebt > 0 && (calculatedDebt <= debtLimit - debtBalance)) {

            account.reduceBalance(account.getBalance());
            debtBalance += calculatedDebt;
            return;

        } else if (calculatedDebt > 0 && (calculatedDebt > debtLimit - debtBalance)) {

            throw new AccountException("Insufficient funds");
        }
        account.reduceBalance(amount);
    }
}
