package com.shoponline.org.orderservice.controllers;

import com.shoponline.org.orderservice.dtos.OrderRequestDTO;
import com.shoponline.org.orderservice.service.IOrderService;
import com.shoponline.org.orderservice.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/v1/orders")
public class OrderController {

    private final IOrderService iOrderService;

    public OrderController(IOrderService iOrderService) {
        this.iOrderService = iOrderService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, code = HttpStatus.CREATED)
    public void createOrder(@RequestBody @Valid OrderRequestDTO orderRequestDTO){
        //ensure the list is not empty
        try {
            iOrderService.CreateOrder(orderRequestDTO);
        }catch (Exception ex){
            //log error to console
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "error: " + ex.getMessage() , ex);
        }
    }



}
