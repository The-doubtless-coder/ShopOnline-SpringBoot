package com.shoponline.org.productservice.dtos;

import jakarta.validation.constraints.*;


import java.math.BigDecimal;

public class ProductRequestDTO {
    @NotBlank(message = "Invalid description: Empty name")
    @NotNull(message = "Invalid description: Name is NULL")
    @Size(min = 3, max = 100, message = "Invalid description: Must be of 3 - 30 characters")
    private String description;
    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
    private String name;
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=5, fraction=2)
    private BigDecimal price;

    public ProductRequestDTO(String description, String name, BigDecimal price) {
        this.description = description;
        this.name = name;
        this.price = price;
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
