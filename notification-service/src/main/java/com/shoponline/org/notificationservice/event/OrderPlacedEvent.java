package com.shoponline.org.notificationservice.event;

import com.shoponline.org.notificationservice.dtos.OrderLineItemsDTO;

import java.util.List;

public class OrderPlacedEvent {
    private String orderNumber;
    private List<OrderLineItemsDTO> lineItems;

    @Override
    public String toString() {
        return "OrderPlacedEvent{" +
                "orderNumber='" + orderNumber + '\'' +
                ", lineItems=" + lineItems +
                '}';
    }
    public OrderPlacedEvent(){

    }

    public OrderPlacedEvent(String orderNumber, List<OrderLineItemsDTO> lineItems) {
        this.orderNumber = orderNumber;
        this.lineItems = lineItems;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<OrderLineItemsDTO> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<OrderLineItemsDTO> lineItems) {
        this.lineItems = lineItems;
    }
}
