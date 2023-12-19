package org.shoponline.org.inventoryservice.dtos;

import lombok.Builder;

@Builder
public class InventoryResponse {
    private boolean isAvailable;
    private String skuCode;
    private boolean isInStock;

    public InventoryResponse(boolean isAvailable, String skuCode, boolean isInStock) {
        this.isAvailable = isAvailable;
        this.skuCode = skuCode;
        this.isInStock = isInStock;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }
}
