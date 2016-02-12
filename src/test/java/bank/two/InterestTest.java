package bank.two;

import static org.hamcrest.CoreMatchers.instanceOf;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import bank.two.investment_state.InvestmentStateOpen;

public class InterestTest {
	
    Interest testLinearInterest;

    @Before
    public void setUp() throws Exception {
    	testLinearInterest = new LinearInterest();
    	testLinearInterest.setRate(0.05);
    }

    @Test
    public void testLinearInterestValidRate() {
    	assertEquals(0.05,testLinearInterest.getRate(), 0);
    }
    
    @Test
    public void testLinearInterestValidInterest() {
    	double calculatedInterest = testLinearInterest.calculate(10000);
    	assertEquals(calculatedInterest,500, 0);
    }

    @After
    public void tearDown() throws Exception {
    	testLinearInterest = null;
    }
}
