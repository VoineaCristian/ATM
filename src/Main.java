import model.Receipt;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args){

        int amount = 0;
        Scanner s = new Scanner(System.in);
        ATM atm = new ATM();
        Receipt rcp;

        while(amount != -1){
            amount = s.nextInt();
            rcp = atm.withDraw(amount);

            if(rcp != null) {
                System.out.println(rcp);
                System.out.println("ATM STOCK " + atm);
            }
        }
    }
}
