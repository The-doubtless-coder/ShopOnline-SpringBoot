package com.shoponline.org.orderservice.service;

import com.shoponline.org.orderservice.dtos.OrderRequestDTO;
import com.shoponline.org.orderservice.exceptions.ProductNotInStockException;

public interface IOrderService {
    public void CreateOrder(OrderRequestDTO orderRequestDTO) throws Exception;
}
