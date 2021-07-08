package factory;

import enums.MessageType;
import model.Message;

public class MessageFactory {

    public Message create(int valueOf,float percentage ,String sendTo){

        Message msg = new Message();
        String info = valueOf + " LEI are running out. ONLY "
                    + percentage*100 + "%" + " remained here";

        if(percentage <= 0.1){
            msg.setMessageType(MessageType.CriticalMessage);
        } else msg.setMessageType(MessageType.WarningMessage);

        msg.setInfo(info);
        msg.setSendTo(sendTo);

        return msg;

    }
}
