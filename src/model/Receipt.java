package model;

import enums.MoneyType;

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

        List<MoneyType> money = Arrays.stream(MoneyType.values()).map(s->(MoneyType)s).collect(Collectors.toList());
        StringBuilder printedReceipt = new StringBuilder("RECEIPT: {");

        money.forEach(moneyType->{
            int moneyIndex = moneyType.ordinal();
            printedReceipt.append("[ "+ moneyType.name() + " : " + moneyCounter.get(moneyIndex) +   " ] ");
        });

        printedReceipt.append("}");

        return printedReceipt.toString();
    }
}
