package bank.two.tests;
import static org.junit.Assert.assertEquals;

import bank.two.Account;
import bank.two.Bank;
import bank.two.BankMediator;
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

        firstAccount = firstBank.createStandardAccount();
        secondAccount = firstBank.createStandardAccount();
        thirdAccount = secondBank.createStandardAccount();

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
    @Test
    public void insufficientFunds() {
        firstBank.outSession();
        firstAccount.transferMoney(thirdAccount,1000);
        assertEquals(3, bankMediator.getTransfers().size());
    }
}