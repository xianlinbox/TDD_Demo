package com.xianlinbox;

import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

public class JmsQueueSender {
    private JmsTemplate jmsTemplate;

    public void send(String message, Queue queue) {
        jmsTemplate.convertAndSend(queue, message);
    }


    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
}
