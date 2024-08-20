package org.example.entities;

import java.util.Date;

public class Message {

    private User author;
    private User recipient;
    private String message;
    private Date dateCreate;
    private boolean readMessage;

    public Message(User author, User recipient, String message, Date dateCreate, boolean readMessage) {
        this.author = author;
        this.recipient = recipient;
        this.message = message;
        this.dateCreate = new Date();
        this.readMessage = false;
    }

    public Message(User loggedInUser, User recipient, String messageText) {
    }

    public User getAuthor() {
        return author;
    }

    public User getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public boolean isReadMessage() {
        return readMessage;
    }

    public void setReadMessage(boolean readMessage) {
        this.readMessage = readMessage;
    }

    @Override
    public String toString() {
        return "Сообшение{" +
                "\n       Получатель =" + recipient +
                "\n       От кого пришло =" + author +
                "\n       Сообщение ='" + message + '\'' +
                "\n       Дата отправки =" + dateCreate +
                '}';
    }
}
