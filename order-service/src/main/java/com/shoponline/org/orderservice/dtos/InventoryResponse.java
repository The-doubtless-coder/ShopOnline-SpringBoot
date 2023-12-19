package com.shoponline.org.orderservice.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class InventoryResponse {

    @Setter
    @Getter
    private String skuCode;
    private boolean isInStock;
    private boolean isAvailable;

    public InventoryResponse(String skuCode, boolean isInStock, boolean available) {
        this.skuCode = skuCode;
        this.isInStock = isInStock;
        this.isAvailable = available;
    }


    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }

    @Override
    public String toString() {
        return "InventoryResponse{" +
                "skuCode='" + skuCode + '\'' +
                ", isInStock=" + isInStock +
                '}';
    }
}
