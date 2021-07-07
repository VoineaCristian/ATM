package model;

import enums.CurrencyType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Receipt {

   private ArrayList<Integer> moneyCounter;

    public Receipt(ArrayList counter) {

        this.moneyCounter = counter;
    }

    @Override
    public String toString() {

        List<CurrencyType> money = Arrays.stream(CurrencyType.values()).map(s->(CurrencyType)s).collect(Collectors.toList());
        StringBuilder printedReceipt = new StringBuilder("RECEIPT: { \n");

        money.forEach(moneyType->{

            int moneyIndex = moneyType.ordinal();
            printedReceipt.append(moneyType.name() + " : " + moneyCounter.get(moneyIndex) + '\n');
        });

        printedReceipt.append("}");

        return printedReceipt.toString();
    }
}
