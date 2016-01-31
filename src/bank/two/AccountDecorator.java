package bank.two;

public abstract class AccountDecorator implements Account{
    protected Account account;

    public AccountDecorator(Account account) {
        this.account = account;
    }
    public Bank getBank() { return account.getBank();}
    public int getAccountBankCode(){
       return account.getAccountBankCode();
    }
    public int getBalance() {
        return account.getBalance();
    }
    public int getNumber() {
        return account.getNumber();
    }
    public void depositMoney(int amount) {
        account.depositMoney(amount);
    }
    public void withdrawMoney(int amount){
        account.withdrawMoney(amount);
    }
    public void transferMoney(Account dstAccount, int amount) {
        account.transferMoney(dstAccount,amount);
    }
    public void increaseBalance(int amount){
        account.increaseBalance(amount);
    }
    public void reduceBalance(int amount) throws AccountException {
        account.reduceBalance(amount);
    }
}
