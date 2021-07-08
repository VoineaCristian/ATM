package exception;

public class NotEnoughMoney extends Exception {

    public NotEnoughMoney() {
        super("Not enough money in ATM. Enter smaller amount");
    }
}