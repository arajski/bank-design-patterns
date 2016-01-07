package bank.two;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class BankMediatorTest {
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

        firstAccount = firstBank.createAccount();
        secondAccount = firstBank.createAccount();
        thirdAccount = secondBank.createAccount();

        firstAccount.depositMoney(1000);
        firstAccount.transferMoney(thirdAccount,100);
        firstAccount.transferMoney(thirdAccount,20);
        firstAccount.transferMoney(secondAccount,20);
    }

    @Test
    public void assignBankCode() {
        assertEquals(2, secondBank.getBankCode());
    }
    @Test
    public void storeBanks() {
        assertEquals(2, bankMediator.getBanks().size());
    }
    @Test
    public void receiveTransfers() {

        firstBank.outSession();
        assertEquals(3, bankMediator.getTransfers().size());
    }
    @Test
    public void sendTransfersToFirstBank() {

        firstBank.outSession();
        secondBank.inSession();
        assertEquals(1, bankMediator.getTransfers().size());
    }
    @Test
    public void sendTransfersToAllBanks() {

        firstBank.outSession();
        firstBank.inSession();
        secondBank.inSession();
        assertEquals(0, bankMediator.getTransfers().size());
    }
}