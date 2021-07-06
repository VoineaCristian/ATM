import model.Receipt;

import java.util.EmptyStackException;

public class Bank {

    private int unLeu;
    private int cinciLei;
    private int zeceLei;
    private int cinciZeciLei;
    private int oSutaLei;
    private int totalAmountOfMoney;
    private static Bank instance;

    private Bank(){
        this.unLeu = 100;
        this.cinciLei = 100;
        this.zeceLei = 100;
        this.cinciZeciLei = 50;
        this.oSutaLei = 50;
        this.totalAmountOfMoney = 9100;
    }

    public static Bank getInstance() {

        if(instance == null){
            instance = new Bank();
        }
        return instance;
    }

    public Receipt getAmountOf(int amount){

        int nrOfUnLeuBnk = 0;
        int nrOfCinciLeiBnk = 0;
        int nrOfZeceLeiBnk = 0;
        int nrOfCinciZeciLeiBnk = 0;
        int nrOfOSutaLeiBnk = 0;

        nrOfOSutaLeiBnk = Math.min(amount / 100, this.oSutaLei);
        amount -= nrOfOSutaLeiBnk*100;

        nrOfCinciLeiBnk = Math.min(amount / 50, this.cinciZeciLei);
        amount -= nrOfCinciZeciLeiBnk*50;

        nrOfZeceLeiBnk= Math.min(amount / 10, this.zeceLei);
        amount -= nrOfZeceLeiBnk*10;

        nrOfCinciLeiBnk = Math.min(amount / 5, this.cinciLei);
        amount -= nrOfCinciLeiBnk*5;

        nrOfUnLeuBnk = Math.min(amount, this.unLeu);
        amount -= nrOfUnLeuBnk;


        return new Receipt(nrOfUnLeuBnk, nrOfCinciLeiBnk, nrOfZeceLeiBnk, nrOfCinciZeciLeiBnk, nrOfOSutaLeiBnk);

    }

    public boolean validAmount(int amount){
        return amount < this.totalAmountOfMoney;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "unLeu=" + unLeu +
                ", cinciLei=" + cinciLei +
                ", zeceLei=" + zeceLei +
                ", cinciZeciLei=" + cinciZeciLei +
                ", oSutaLei=" + oSutaLei +
                ", totalAmountOfMoney=" + totalAmountOfMoney +

                '}';
    }



}
