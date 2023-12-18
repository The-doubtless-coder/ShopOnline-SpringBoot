package com.shoponline.org.orderservice.exceptions;

public class ProductNotInStockException extends Exception{
    public ProductNotInStockException(String message) {
        super(message);
    }
}
