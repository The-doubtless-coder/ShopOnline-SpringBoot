package com.shoponline.org.notificationservice;

import com.shoponline.org.notificationservice.dtos.OrderLineItemsDTO;
import com.shoponline.org.notificationservice.event.OrderPlacedEvent;
import com.shoponline.org.notificationservice.model.MailStructure;
import com.shoponline.org.notificationservice.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;

import java.math.BigDecimal;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class NotificationServiceApplication {

    private final IMailService iMailService;

    public NotificationServiceApplication(IMailService iMailService) {
        this.iMailService = iMailService;
    }

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "order_topic")
    public void handleOrderPlacedEvent(OrderPlacedEvent orderPlacedEvent){
//send mail to customer
        int totalQty = orderPlacedEvent.getLineItems().size();
        BigDecimal totalPrice = orderPlacedEvent.getLineItems()
                .stream().map(OrderLineItemsDTO::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        String message = "Order of number: " + orderPlacedEvent.getOrderNumber()
                + " has been placed \nTotal Quantity::" +
                totalQty + " \nTotal Price::" + totalPrice;
        iMailService.SendMail("kingian907@gmail.com",
                new MailStructure("Order Placed!", message));



        log.info("received order request:: {}", orderPlacedEvent);
    }

}
