package com.shoponline.org.productservice.dtos;

import java.math.BigDecimal;

public class ProductResponseDTO {
    //add a uuid automatically in the db and send that instead of sending an id
    private long id;
    private String description;
    private String name;
    private BigDecimal price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
