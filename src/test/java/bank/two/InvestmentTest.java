package bank.two;

import static org.hamcrest.CoreMatchers.instanceOf;

import bank.two.investment_state.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InvestmentTest {

    Investment testInvestment;

    @Before
    public void setUp() throws Exception {
        testInvestment = new Investment(15000);
    }

    @Test
    public void testIsOpen() {
        assertThat(testInvestment.contextState(), instanceOf(InvestmentStateOpen.class));
    }

    @Test
    public void testIsActive() {
        testInvestment.setLength(3);
        assertThat(testInvestment.contextState(), instanceOf(InvestmentStateActive.class));
    }

    @Test
    public void testAmountValid() {
        assertEquals(15000, testInvestment.getAmount());
    }

    @Test
    public void testLengthValid() {
        int investmentLengthPeriod = 9;
        testInvestment.setLength(investmentLengthPeriod);
        assertEquals(investmentLengthPeriod, testInvestment.getLength());
    }

    @Test
    public void testStateForInvestmentLengthSmallerThanMinimal() {
        testInvestment.setLength(2);
        assertThat(testInvestment.contextState(), instanceOf(InvestmentStateOpen.class));
    }

    @Test
    public void testIsFinished() {
        int investmentLengthPeriod = 6;
        testInvestment.setLength(investmentLengthPeriod);
        for (int i=0; i<=investmentLengthPeriod; i++) {
            testInvestment.contextState().stepOver();
        }
        assertThat(testInvestment.contextState(), instanceOf(InvestmentStateFinished.class));
    }

    @Test
    public void testIsTerminated() {
        int investmentLengthPeriod = 8;
        testInvestment.setLength(investmentLengthPeriod);
        for (int i=0; i<6; i++) {
            testInvestment.contextState().stepOver();
        }
        testInvestment.currentContext().getMoney();
        assertThat(testInvestment.contextState(), instanceOf(InvestmentStateTerminated.class));
    }

    @Test
    public void testInvestmentTerminationPayment() {
        int amount = testInvestment.getAmount();
        int investmentLengthPeriod = 8;
        testInvestment.setLength(investmentLengthPeriod);
        for (int i=0; i<6; i++) {
            testInvestment.contextState().stepOver();
        }
        int investmentMoney = testInvestment.currentContext().getMoney();
        assertEquals(amount, investmentMoney);
    }

    @Test
    public void testInvestmentExpirationPayment() {
        int amount = testInvestment.getAmount();
        int investmentLengthPeriod = 9;
        testInvestment.setLength(investmentLengthPeriod);
        for (int i=0; i<=investmentLengthPeriod; i++) {
            testInvestment.contextState().stepOver();
        }
        int investmentMoney = testInvestment.currentContext().getMoney();
        //15000 * 0,9 / 100 = 135
        assertEquals(15135, investmentMoney);
    }

    @After
    public void tearDown() throws Exception {
        testInvestment = null;
    }
}