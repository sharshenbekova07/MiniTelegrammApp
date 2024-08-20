package org.example.servisec;

import org.example.entities.Message;
import org.example.entities.User;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {
    private List<Message> messages = new ArrayList<>();

    public void sendMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessageForUser(User user) {
        List<Message> userMessages = new ArrayList<>();
        for (Message message : messages) {
            if (message.getRecipient().equals(user)) {
                userMessages.add(message);
            }
        }
        return userMessages;
    }

    public int getMessageCountForUser(User user) {
        int count = 0;
        for (Message message : messages) {
            if (message.getRecipient().equals(user)){
                count++;
            }
        }
        return count;
    }

    public int getTotalMessageCount() {
        return messages.size();
    }


}
