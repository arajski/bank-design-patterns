package bank.two;
import static org.junit.Assert.assertEquals;

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

        firstAccount = firstBank.createAccount();
        secondAccount = firstBank.createAccount();
        thirdAccount = firstBank.createAccount();
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

}

