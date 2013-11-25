package com.xianlinbox;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JmsQueueSenderIntegrationTest {
    @Autowired
    private JmsQueueSender jmsQueueSender;

    @Autowired
    private MyQueueMessageListener messageListener;

    @Before
    public void setUp() throws Exception {
        createActiveMQMessageQueue();
    }

    private void createActiveMQMessageQueue() throws Exception {
        BrokerService broker = new BrokerService();
        broker.addConnector("tcp://localhost:61616");
        broker.setPersistent(false);
        broker.start();
    }

    @Test
    public void shouldSendMessageSuccessfully() throws Exception {
        Queue testQueue = new ActiveMQQueue("TEST.QUEUE");
        String message = "test queue content";
        jmsQueueSender.send(message, testQueue);

        Thread.sleep(5000);
        assertThat(messageListener.getMessages().size(), equalTo(1));
        assertThat(messageListener.getMessages().get(0), equalTo("test queue content"));
    }
}
