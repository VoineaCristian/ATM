import enums.MoneyType;
import exception.NotEnoughMoney;
import exception.NotEnoughPennies;
import exception.RunOutOfMoney;
import factory.MessageFactory;
import model.Message;
import model.Receipt;

import java.util.*;
import java.util.stream.Collectors;

public class Bank {

    private List<MoneyType> money;
    private List<Integer> moneyStatus;
    private static Bank instance;
    private List<Message> mailbox;
    private int availableAmount;

    private Bank(){

        this.moneyStatus = new ArrayList<>();
        this.money = new ArrayList();
        this.mailbox = new ArrayList<>();
        this.money = Arrays.stream(MoneyType.values()).map(s->(MoneyType)s).collect(Collectors.toList());
        this.money.forEach(moneyType->{
            this.moneyStatus.add(moneyType.getInitialCount());
            availableAmount += moneyType.getValue()*moneyType.getInitialCount();
        });
    }

    public static Bank getInstance() {

        if(instance == null){
            instance = new Bank();
        }
        return instance;
    }

    public Receipt getAmountOf(int amount) throws NotEnoughPennies, NotEnoughMoney,RunOutOfMoney {

        ArrayList<Integer> dueMoney = new ArrayList<>(MoneyType.values().length);
        Iterator moneyIterator = money.iterator();
        List<Integer> resourcesBackup = new ArrayList<>(moneyStatus);
        int availableAmountBackup = availableAmount;

        if(!this.validAmount(amount)){
            if(availableAmount == 0){
                throw new RunOutOfMoney();
            } else  throw new NotEnoughMoney();
        }

        while(moneyIterator.hasNext())
        {
            MoneyType moneyType = (MoneyType) moneyIterator.next();
            int moneyIndex = MoneyType.valueOf(moneyType.name()).ordinal();
            int billValue = moneyType.getValue();
            int availableBills = moneyStatus.get(moneyIndex);
            int nrOfUsedBills = Math.min(amount / billValue, availableBills);
            float percOfUsedMoney = 0;

            amount -= nrOfUsedBills*moneyType.getValue();
            availableBills -= nrOfUsedBills;
            moneyStatus.set(moneyIndex, availableBills);
            dueMoney.add(moneyIndex, nrOfUsedBills);
            percOfUsedMoney = (float)availableBills/moneyType.getInitialCount();
            this.availableAmount -= nrOfUsedBills*moneyType.getValue();

            if(moneyType == MoneyType.LEU_100 && percOfUsedMoney < 0.2){
                addNewMail(billValue, percOfUsedMoney);
            } else if(moneyType == MoneyType.LEU_50 && percOfUsedMoney < 0.15){
                addNewMail(billValue, percOfUsedMoney);
            }
        }

        if(amount != 0){
            this.moneyStatus = resourcesBackup;
            this.availableAmount = availableAmountBackup;

            if(amount < 10 && this.availableAmount >= 10){
                System.out.println(this.availableAmount);
                throw new NotEnoughPennies();
            }
            throw new NotEnoughMoney();
        }

        return new Receipt(dueMoney);
    }


    public boolean validAmount(int amount){
        return amount <= this.availableAmount;
    }

    public void addNewMail(int billValue, float percOfUsedMoney){

        MessageFactory messageFactory = new MessageFactory();
        Message msg = messageFactory.create(billValue, percOfUsedMoney, "fillMeUpPlease@superbancomat.com");
        this.mailbox.add(msg);

    }
    public List<Message> getMailbox() {
        return mailbox;
    }

    public void setMailbox(List<Message> mailbox) {
        this.mailbox = mailbox;
    }

    public void cleanMailbox() {
        this.mailbox.clear();
    }


    public String toString(){

        Receipt globalReceipt = new Receipt((ArrayList) this.moneyStatus);

        return globalReceipt.toString();
    }


}
