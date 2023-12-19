package com.shoponline.org.notificationservice.service;


import com.shoponline.org.notificationservice.model.MailStructure;

public interface IMailService {
    public void SendMail(String mail, MailStructure structure);
}
