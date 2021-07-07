import exception.NotEnoughMoney;
import exception.NotEnoughPennies;
import exception.RunOutOfMoney;
import model.Message;
import model.Receipt;

import java.util.List;
import java.util.logging.Logger;

public class ATM {

    Bank bank = Bank.getInstance();

    public Receipt withDraw(int amount)  {

        Logger logger =  Logger.getLogger(ATM.class.getName());
        Receipt receipt = null;

        try {
            receipt = bank.getInstance().getAmountOf(amount);
        } catch (NotEnoughMoney NEM){
            logger.warning(NEM.getMessage());
        }catch (NotEnoughPennies NEP){
            logger.warning(NEP.getMessage());
        }catch (RunOutOfMoney ROOF){
            logger.warning(ROOF.getMessage());
        }
        this.sendMessages();

        return receipt;
    }

    public void sendMessages(){

        List<Message> messages = bank.getMailbox();

        messages.forEach(message-> System.out.println(message));
        bank.cleanMailbox();

    }

    @Override
    public String toString() {
        return "ATM{" +
                "bank=" + bank +
                '}';
    }

}
