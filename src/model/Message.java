package model;

import Enums.MessageType;

public class Message {

    MessageType messageType;
    String sendTo;
    String info;

    public Message(MessageType messageType, String sendTo, String info) {
        this.messageType = messageType;
        this.sendTo = sendTo;
        this.info = info;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getSendTo() {
        return sendTo;
    }

    public String getInfo() {
        return info;
    }
}
