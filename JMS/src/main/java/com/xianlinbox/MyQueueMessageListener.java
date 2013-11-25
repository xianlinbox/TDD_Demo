package com.xianlinbox;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.ArrayList;
import java.util.List;

public class MyQueueMessageListener implements MessageListener {
    private List<String> messages = new ArrayList<String>();

    @Override
    public void onMessage(Message message) {
        try {
            messages.add(((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public List<String> getMessages() {
        return messages;
    }
}
