package com.xianlinbox;

import com.icegreen.greenmail.store.MailFolder;
import com.icegreen.greenmail.store.StoredMessage;
import com.icegreen.greenmail.user.GreenMailUser;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MyMailSenderTest {
    private MyMailSender mailSender;
    private GreenMail mailServer;

    @Before
    public void setUp() throws Exception {
        mailServer = new GreenMail(new ServerSetup(12000, null, "smtp"));
        mailServer.start();
        mailSender = new MyMailSender("localhost", 12000);
    }

    @After
    public void tearDown() throws Exception {
        mailServer.stop();
    }

    @Test
    public void shouldSendEmailToServer() throws Exception {
        mailSender.sendEmail("Unit Test  for java mail Demo");
        String receivedMessage = (String) mailServer.getReceivedMessages()[0].getContent();
        assertThat(receivedMessage.trim(), equalTo("Unit Test  for java mail Demo"));
    }

    @Test
    public void shouldSendToSpecifiedUser() throws Exception {
        mailSender.sendEmail("Unit Test  for java mail Demo");
        GreenMailUser greenMailUser = mailServer.setUser("xianlinbox@163.com", null);
        MailFolder inbox = mailServer.getManagers().getImapHostManager().getInbox(greenMailUser);
        List<StoredMessage> messages = inbox.getMessages();
        assertThat(messages.size(),equalTo(1));
        String content = (String) messages.get(0).getMimeMessage().getContent();
        assertThat(content.trim(), equalTo("Unit Test  for java mail Demo"));
    }
}
