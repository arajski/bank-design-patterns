package bank.two;

import bank.two.reporting_visitor.Report;
import bank.two.reporting_visitor.ReportingVisitor;

import java.util.ArrayList;

public class Bank {
    private Mediator bankMediator;
    private int bankCode;
    private ArrayList<Transfer> outcomingTransfers;
    private ArrayList<Account> accounts;
    private Report visitor;

    private int accountCodes;

    public Bank(){
        visitor = new Report();
        outcomingTransfers = new ArrayList<Transfer>();
        accounts = new ArrayList<Account>();
        accountCodes = 0;
    }
    public int getBankCode() {
        return bankCode;
    }

    public ArrayList<Transfer> getTransfers() {
        return this.outcomingTransfers;
    }

    public ArrayList<Account> getAccounts() {
        return this.accounts;
    }

    public void setBankCode(int bankCode, Mediator newMediator) {
        this.bankCode = bankCode;
        this.bankMediator = newMediator;
    }

    public Account createStandardAccount() {
        accountCodes++;
        Account account = new StandardAccount(this,accountCodes);
        accounts.add(account);
        return account;
    }

    public Account createDebtAccount(int debtLimit) {
        accountCodes++;
        Account account = new DebtAccountDecorator(new StandardAccount(this,accountCodes),debtLimit);
        accounts.add(account);
        return account;
    }

    public void addTransferToSession(Transfer transfer) {
        outcomingTransfers.add(transfer);
    }

    public void outSession() {
        bankMediator.receiveTransfers(outcomingTransfers);
        outcomingTransfers.clear();
    }
    public void inSession() {
        ArrayList<Transfer> incomingTransfers = new ArrayList<Transfer>();
        incomingTransfers = bankMediator.sendTransfers(bankCode);
        for(Transfer incomingTransfer: incomingTransfers){
            incomingTransfer.execute();
        }
    }

    public Report getVisitor() {
        return this.visitor;
    }
}
