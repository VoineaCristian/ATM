package exception;

public class NotEnoughPennies extends Exception{

    public NotEnoughPennies() {
        super("Not enough pennies. Enter rounded amount");
    }
}

