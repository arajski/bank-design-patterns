package bank.two.loan_state;

import bank.two.Interest;
import bank.two.investment_state.InvestmentStateFinished;
import bank.two.investment_state.InvestmentStateTerminated;

public class LoanStateContext {
    private LoanState state;
    private int amount = 0;
    private int length = 0;
    int currentLength = 0;
    double monthlyRate = 0;
    double totalPaidAmount = 0;
    private Interest interest;

    public LoanStateContext(int amount, Interest interest) {
        this.amount = amount;
        this.interest = interest;
        state = new LoanStateOpen();
    }

    public void setState(LoanState state) {
        this.state = state;
    }

    public void setLength(int length) {
        int minimumLoanPeriod = 12;
        if(length >= minimumLoanPeriod) {
            this.length = length;
            this.currentLength = length;
            this.state = new LoanStateActive(this);
            this.monthlyRate = amount/length;
        }
    }

    public void setInterest(Interest interest) {
        if(interest.getRate() > 0) {
            this.interest = interest;
        }
    }
    
    public void stepOver() {
        state.stepOver();
    }

    public LoanState getState() {
        return this.state;
    }

    public int getLength() {
        return this.length;
    }

    public int getAmount() {
        return this.amount;
    }
     
    public double getTotalPaidAmount() {
        return this.totalPaidAmount;
    }
    
    public double getDueAmount() {
        return this.monthlyRate * currentLength;
    }
     
    
    public Interest getInterest() {
        return this.interest;
    }
    
    public double getMonthlyRate() {
        return this.monthlyRate;
    }
    
    public int getCurrentLength() {
        return this.currentLength;
    }
     
    public void payInstallment(){
    	this.stepOver();
    	if(currentLength == 0){
    		this.state = new LoanStateFinished();
    	}
    }
    
    public boolean isPaid() {
    	if((state instanceof LoanStateFinished)) {
            return true;
        }
    	return false;
    }
}
