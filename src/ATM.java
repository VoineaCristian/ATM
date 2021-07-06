import model.Receipt;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ATM {

    Bank bank = Bank.getInstance();

    public Receipt withDraw(int amount){

        Receipt receipt;

        if(bank.getInstance().validAmount(amount)) {
            receipt = bank.getInstance().getAmountOf(amount);
        } else {
            throw new EmptyStackException();
        }

        return receipt;
    }

    @Override
    public String toString() {
        return "ATM{" +
                "bank=" + bank +
                '}';
    }

}
