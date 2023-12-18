package com.shoponline.org.orderservice.dtos;

import com.shoponline.org.orderservice.models.OrderLineItems;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Empty;

import java.util.List;

public class OrderRequestDTO {
    //
    private String orderNumber;
    //@NotEmpty.List(10)
    //validate list, make sure it's non empty
    private List<OrderLineItemsDTO> orderLineItemsDTOList;

    public List<OrderLineItemsDTO> getOrderLineItemsDTOList() {
        return orderLineItemsDTOList;
    }

    public void setOrderLineItemsDTOList(List<OrderLineItemsDTO> orderLineItemsDTOList) {
        this.orderLineItemsDTOList = orderLineItemsDTOList;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}
