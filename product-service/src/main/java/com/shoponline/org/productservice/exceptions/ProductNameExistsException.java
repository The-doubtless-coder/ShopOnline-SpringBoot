package com.shoponline.org.productservice.exceptions;

public class ProductNameExistsException extends Exception{
    public ProductNameExistsException(String message) {
        super(message);
    }
}
