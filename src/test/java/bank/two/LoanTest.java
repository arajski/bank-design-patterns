package bank.two;

import bank.two.Loan;
import bank.two.loan_state.*;
import static org.hamcrest.CoreMatchers.instanceOf;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoanTest {

	Loan testLoan;
    Interest testInterest;

    @Before
    public void setUp() throws Exception {
    	testInterest = new DummyInterest();
    	testLoan = new Loan(12000,testInterest);
    }

    @Test
    public void testIsOpen() {
        assertThat(testLoan.currentContext().getState(), instanceOf(LoanStateOpen.class));
    }

    @Test
    public void testIsActive() {
    	testLoan.setLength(12);
        assertThat(testLoan.currentContext().getState(), instanceOf(LoanStateActive.class));
    }

    @Test
    public void testAmountValid() {
        assertEquals(12000, testLoan.getAmount());
    }

    @Test
    public void testLengthValid() {
        int loanLengthPeriod = 12;
        testLoan.setLength(loanLengthPeriod);
        assertEquals(loanLengthPeriod, testLoan.getLength());
    }
    
    @Test
    public void testInterestValid() {
        assertEquals(0, testLoan.getInterest().getRate(),0);
    }

    @Test
    public void testStateForLoanLengthSmallerThanMinimal() {
    	testLoan.setLength(2);
        assertThat(testLoan.currentContext().getState(), instanceOf(LoanStateOpen.class));
    }

    @Test
    public void testIsFinished() {
        int loanLengthPeriod = 12;
        testLoan.setLength(loanLengthPeriod);
        for (int i=0; i<loanLengthPeriod; i++) {
        	testLoan.payInstallment();
        }
        assertThat(testLoan.currentContext().getState(), instanceOf(LoanStateFinished.class));
    }

    @Test
    public void testIsPaid() {
        int loanLengthPeriod = 12;
        testLoan.setLength(loanLengthPeriod);
        for (int i=0; i<loanLengthPeriod; i++) {
        	testLoan.payInstallment();
        }
        assertEquals(true, testLoan.isPaid());
    }
    
    
    @Test
    public void testTotalPaidAmount() {
        int loanLengthPeriod = 12;
        testLoan.setLength(loanLengthPeriod);
        
        int testPaymentPeriod = 3;
        for (int i=0; i<testPaymentPeriod; i++) {
        	testLoan.payInstallment();
        }
        assertEquals(3003, testLoan.getTotalPaidAmount(),0);
    }

    @Test
    public void testDueAmount() {
        int loanLengthPeriod = 12;
        testLoan.setLength(loanLengthPeriod);
        
        int testPaymentPeriod = 3;
        for (int i=0; i<testPaymentPeriod; i++) {
        	testLoan.payInstallment();
        }
        assertEquals(9000, testLoan.getDueAmount(),0);
    }
    
    @Test
    public void testMonthlyRate() {
        int loanLengthPeriod = 12;
        testLoan.setLength(loanLengthPeriod);
        assertEquals(1000, testLoan.getMonthlyRate(),0);
    }

    @After
    public void tearDown() throws Exception {
    	testLoan = null;
    }
}