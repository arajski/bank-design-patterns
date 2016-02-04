package bank.two;

import java.util.ArrayList;
import java.util.Iterator;

public class BankMediator implements Mediator {
    private ArrayList<Bank> banks;
    private ArrayList<Transfer> transfers;

    private int bankCodes = 0;

    public BankMediator() {
        banks = new ArrayList<Bank>();
        transfers = new ArrayList<Transfer>();
    }
    public ArrayList<Bank> getBanks() {
        return this.banks;
    }
    public ArrayList<Transfer> getTransfers() {
        return this.transfers;
    }

    @Override
    public void addBank(Bank newBank) {
        bankCodes++;
        banks.add(newBank);
        newBank.setBankCode(bankCodes, this);
    }

    @Override
    public void receiveTransfers(ArrayList<Transfer> incomingTransfers) {
        for(Transfer newTransfer: incomingTransfers) {
            this.transfers.add(newTransfer);
        }
    }

    @Override
    public ArrayList<Transfer> sendTransfers(int bankCode) {
        ArrayList<Transfer> outcomingTransfers = new ArrayList<Transfer>();

        for (Iterator<Transfer> it = transfers.iterator(); it.hasNext(); ) {
            Transfer transfer = it.next();
            if (transfer.getDestinationAccount().getAccountBankCode() == bankCode) {
                outcomingTransfers.add(transfer);
                it.remove();
            }
        }
        return outcomingTransfers;
    }
}
