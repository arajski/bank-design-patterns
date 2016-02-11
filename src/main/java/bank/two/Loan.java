package bank.two;

import bank.two.loan_state.*;

public class Loan {
	LoanStateContext context;
	
	public Loan(int amount, Interest interest){
		this.context = new LoanStateContext(amount, interest);
	}

	public void setLength(int length){
		this.context.setLength(length);
	}
	
	public void setInterest(Interest interest){
		this.context.setInterest(interest);
	}
	
    public int getAmount() {
        return this.context.getAmount();
    }
    
    public double getTotalPaidAmount() {
        return context.getTotalPaidAmount();
    }
    
    public double getDueAmount() {
        return this.context.getDueAmount();
    }
    
    public double getMonthlyRate() {
        return context.getMonthlyRate();
    }

    public int getLength() {
        return this.context.getLength();
    }
    
    public Interest getInterest() {
        return this.context.getInterest();
    }
    
    public LoanStateContext currentContext() {
        return this.context;
    }
    
    public void payInstallment(){
    	this.context.payInstallment();
    }
    
    public boolean isPaid(){
    	return this.context.isPaid();
    }
}
