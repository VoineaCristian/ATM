package model;

import enums.MessageType;

public class Message {

    MessageType messageType;
    String sendTo;
    String info;

    public Message() {
    }

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

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageType=" + messageType +
                ", sendTo='" + sendTo + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
