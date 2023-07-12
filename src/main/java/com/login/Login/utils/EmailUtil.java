package com.login.Login.utils;

import com.login.Login.request.dto.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;

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

    public void sendEmailWithAttachment(EmailDto emailDto, byte[] attachmentContent, String attachmentFilename) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(emailDto.getToEmail());
            helper.setSubject(emailDto.getSubject());
            helper.setText(emailDto.getBody());

            ByteArrayDataSource attachmentDataSource = new ByteArrayDataSource(attachmentContent, "application/octet-stream");
            helper.addAttachment(attachmentFilename, attachmentDataSource);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email with attachment. Please try again.", e);
        }
    }
}
