package com.xianlinbox;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class MyMailSender {
    private JavaMailSender mailSender;

    public MyMailSender(String host, int port) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        mailSender = javaMailSender;
    }

    public boolean sendEmail(String message) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom("xianlinbox@gmail.com");
            mimeMessageHelper.setTo("xianlinbox@163.com");
            mimeMessageHelper.setText(message);
            mailSender.send(mimeMessageHelper.getMimeMessage());
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
}
