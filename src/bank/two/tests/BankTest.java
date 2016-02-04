package bank.two.tests;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import bank.two.Account;
import bank.two.Bank;
import bank.two.BankMediator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BankTest {
    Bank firstBank;
    BankMediator bankMediator;
    Account firstAccount;
    Account secondAccount;
    Account thirdAccount;

    @Before
    public void setUp() throws Exception {
        bankMediator = new BankMediator();
        firstBank = new Bank();
        bankMediator.addBank(firstBank);

        firstAccount = firstBank.createStandardAccount();
        secondAccount = firstBank.createStandardAccount();
        thirdAccount = firstBank.createStandardAccount();
    }
    @Test
    public void hasBankCode() {
        assertEquals(1, firstBank.getBankCode());
    }
    @Test
    public void storesAccounts() {
        assertEquals(3, firstBank.getAccounts().size());
    }
    @Test
    public void storesSessionTransfers() {
        firstAccount.depositMoney(1000);
        firstAccount.transferMoney(thirdAccount,100);
        firstAccount.transferMoney(thirdAccount,20);
        firstAccount.transferMoney(secondAccount,20);

        assertEquals(3, firstBank.getTransfers().size());
    }
    @Test
    public void sendsSessionTransfers() {
        firstAccount.depositMoney(1000);
        firstAccount.transferMoney(thirdAccount,100);
        firstAccount.transferMoney(thirdAccount,20);
        firstAccount.transferMoney(secondAccount,20);

        firstBank.outSession();
        assertEquals(0, firstBank.getTransfers().size());
    }
    @After // tearDown()
    public void tearDown() {
        System.out.println("Running: tearDown");
        bankMediator = null;
        firstBank = null;
        firstAccount = null;
        secondAccount = null;
        thirdAccount = null;
        assertArrayEquals(new Object[]{null,null,null,null,null}, new Object[]{bankMediator,firstBank,firstAccount,secondAccount,thirdAccount});
    }

}

