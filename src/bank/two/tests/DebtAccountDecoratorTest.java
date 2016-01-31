package bank.two.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import bank.two.Account;
import bank.two.Bank;
import bank.two.BankMediator;
import org.junit.Before;
import org.junit.Test;

public class DebtAccountDecoratorTest {
    Bank firstBank;
    Bank secondBank;
    BankMediator bankMediator;
    Account firstAccount;
    Account secondAccount;
    Account thirdAccount;

    @Before
    public void setUp() throws Exception {
        bankMediator = new BankMediator();
        firstBank = new Bank();
        secondBank = new Bank();
        bankMediator.addBank(firstBank);
        bankMediator.addBank(secondBank);

        firstAccount = firstBank.createDebtAccount(100);
        secondAccount = firstBank.createStandardAccount();
        thirdAccount = secondBank.createStandardAccount();

    }
    @Test
    public void checkInitialDebt() {
        assertEquals(0, firstAccount.getBalance());
    }
    @Test
    public void assignAccountNumber() {
        assertArrayEquals(new int[]{1,2,1}, new int[]{firstAccount.getNumber(),secondAccount.getNumber(),thirdAccount.getNumber()});
    }
    @Test
    public void depositMoney() {
        firstAccount.depositMoney(1000);
        assertEquals(1000, firstAccount.getBalance());
    }
    @Test
    public void withdrawMoney() {
        firstAccount.depositMoney(1000);
        firstAccount.withdrawMoney(100);
        assertEquals(900, firstAccount.getBalance());
    }
    @Test
    public void transferMoney() {
        firstAccount.depositMoney(1000);
        firstAccount.transferMoney(thirdAccount,100);
        firstBank.outSession();
        secondBank.inSession();
        assertArrayEquals(new int[]{900,100}, new int[]{firstAccount.getBalance(),thirdAccount.getBalance()});
    }
    @Test
    public void increaseDebt() {
        firstAccount.depositMoney(100);
        firstAccount.withdrawMoney(150);
        firstAccount.withdrawMoney(20);
        assertEquals(-70, firstAccount.getBalance());
    }
    @Test
    public void payDebt() {
        firstAccount.depositMoney(100);
        firstAccount.withdrawMoney(110);
        firstAccount.depositMoney(20);
        assertEquals(10, firstAccount.getBalance());
    }
    @Test
    public void exceedDebt() {
        firstAccount.depositMoney(100);
        firstAccount.withdrawMoney(150);
        firstAccount.transferMoney(thirdAccount,10);
        firstBank.outSession();
        secondBank.inSession();
        firstAccount.transferMoney(thirdAccount,200);
        firstBank.outSession();
        secondBank.inSession();
        assertArrayEquals(new int[]{-60,10}, new int[]{firstAccount.getBalance(),thirdAccount.getBalance()});
    }


}
