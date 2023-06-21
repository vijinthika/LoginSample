package com.login.Login.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendOtpEmail(String email, String otp) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject("Verify OTP");
            mailMessage.setText("Your OTP: " + otp);

            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            throw new RuntimeException("Failed to send OTP. Please try again.", e);
        }
    }

}
