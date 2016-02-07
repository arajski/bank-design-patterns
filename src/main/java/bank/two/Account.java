package bank.two;

public interface Account {
    Bank getBank();
    int getAccountBankCode();
    int getBalance();
    int getNumber();
    void depositMoney(int amount);
    void withdrawMoney(int amount);
    void transferMoney(Account dstAccount,int amount);
    void increaseBalance(int amount);
    void reduceBalance(int amount) throws AccountException;
}
