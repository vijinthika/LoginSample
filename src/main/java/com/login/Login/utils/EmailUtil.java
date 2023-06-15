package com.login.Login.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOtpEmail(String email, String otp) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Verify OTP");

        String text = "<div>" +
                "<a href=\"http://localhost:8080/verify-account?email=" + email +
                "&otp=" + otp + "\" target=\"_blank\">click link to verify</a>" +
                "</div>";

        mimeMessage.setContent(text, "text/html");

        javaMailSender.send(mimeMessage);
    }

}
