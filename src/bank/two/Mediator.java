package bank.two;

import java.util.ArrayList;

public interface Mediator {

    void addBank(Bank bank);
    void receiveTransfers(ArrayList<Transfer> incomingTransfers);
    ArrayList<Transfer> sendTransfers(int bankCode);
}
