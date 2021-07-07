import enums.CurrencyType;
import exception.NotEnoughMoney;
import exception.NotEnoughPennies;
import exception.RunOutOfMoney;
import factory.MessageFactory;
import model.Message;
import model.Receipt;

import java.util.*;
import java.util.stream.Collectors;

public class Bank {

    private CurrencyType currencyType;
    private List<CurrencyType> money;
    private List<Integer> moneyStatus;
    private static Bank instance;
    private List<Message> mailbox;
    private int availableAmount;

    private Bank(){


        this.money = new ArrayList();
        this.moneyStatus = new ArrayList<>();
        this.money = Arrays.stream(CurrencyType.values()).map(s->(CurrencyType)s).collect(Collectors.toList());
        this.money.forEach(moneyType->{
            moneyStatus.add(moneyType.getInitialCount());
            availableAmount += moneyType.getValue()*moneyType.getInitialCount();
        });
        this.mailbox = new ArrayList<>();

    }

    public static Bank getInstance() {

        if(instance == null){
            instance = new Bank();
        }
        return instance;
    }


    public Receipt getAmountOf(int amount) throws NotEnoughPennies, NotEnoughMoney,RunOutOfMoney {

        ArrayList<Integer> dueMoney = new ArrayList<>(CurrencyType.values().length);
        Iterator iterator = money.iterator();
        List<Integer> resourcesBackup = new ArrayList<>(moneyStatus);

        if(!this.validAmount(amount)){
            throw new NotEnoughMoney();
        }

        if(this.availableAmount == 0){
            throw new RunOutOfMoney();
        }

        while(iterator.hasNext())
        {
            CurrencyType moneyType = (CurrencyType) iterator.next();
            int moneyIndex = CurrencyType.valueOf(moneyType.name()).ordinal();
            int billValue = moneyType.getValue();
            int availableBills = moneyStatus.get(moneyIndex);
            int nrOfUsedBills = Math.min(amount / billValue, availableBills);
            float percOfUsedMoney = 0;

            amount -= nrOfUsedBills*moneyType.getValue();
            availableBills -= nrOfUsedBills;
            moneyStatus.set(moneyIndex, availableBills);
            dueMoney.add(moneyIndex, nrOfUsedBills);
            percOfUsedMoney = (float)availableBills/moneyType.getInitialCount();


            if(moneyType == CurrencyType.LEU_100 && percOfUsedMoney < 0.2){
                addNewMail(billValue, percOfUsedMoney);
            } else if(moneyType == CurrencyType.LEU_50 && percOfUsedMoney < 0.15){
                addNewMail(billValue, percOfUsedMoney);
            }

        }


        if(amount != 0){
            this.moneyStatus = resourcesBackup;
            if(amount < 5 && availableAmount >= 5){
                throw new NotEnoughPennies();
            }
            throw new NotEnoughMoney();
        }


        return new Receipt(dueMoney);
    }


    public boolean validAmount(int amount){
        return amount < this.availableAmount;
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
