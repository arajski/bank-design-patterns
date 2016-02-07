package bank.two;

import bank.two.reporting_visitor.Report;
import bank.two.reporting_visitor.ReportingObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ReportingVisitorTest {

    Bank sampleBank;
    Account sampleAccount;
    Deposit sampleDeposit;
    Investment sampleInvestment;

    @Before
    public void setUp() throws Exception {
        sampleBank = new Bank();
        sampleAccount = sampleBank.createStandardAccount();
        sampleAccount.increaseBalance(10000);
        sampleDeposit = new Deposit(sampleAccount, 5000);
        sampleInvestment = new Investment(5000);
    }

    @Test
    public void testVisitor() {
        Report report = sampleBank.getVisitor();
        List<ReportingObject> results = report.listOfObjectsWithValueGreaterThan(4000);
        Assert.assertEquals(results.size(), 2);

    }

    @After
    public void tearDown() throws Exception {
        sampleBank = null;
        sampleAccount = null;
        sampleDeposit = null;
        sampleInvestment = null;
    }
}