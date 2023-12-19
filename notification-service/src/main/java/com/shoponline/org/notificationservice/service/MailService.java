package com.shoponline.org.notificationservice.service;

import com.shoponline.org.notificationservice.model.MailStructure;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class MailService implements IMailService{

    private final JavaMailSender mailSender;
    @Value("(${spring.mail.username})")
    private String mailFrom;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void SendMail(String mail, MailStructure structure) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("ShopOnlineApplication");//pass mail from variable
        msg.setSubject(structure.getSubject());
        msg.setText(structure.getMessage());
        msg.setSentDate(Date.valueOf(LocalDate.now()));
        msg.setTo(mail);

        mailSender.send(msg);
    }


}
