package bank.two.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import bank.two.Account;
import bank.two.Bank;
import bank.two.BankMediator;
import org.junit.Before;
import org.junit.Test;

public class StandardAccountTest {
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

        firstAccount = firstBank.createStandardAccount();
        secondAccount = firstBank.createStandardAccount();
        thirdAccount = secondBank.createStandardAccount();

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
    public void insufficientFunds() {
        thirdAccount.depositMoney(50);
        thirdAccount.transferMoney(secondAccount,100);
        assertEquals(50, thirdAccount.getBalance());
    }

}
