package com.shoponline.org.orderservice.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "order_tbl")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
    @SequenceGenerator(name="order_generator", sequenceName = "order_seq", allocationSize=1)
    private Long id;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderlineItemsList;

    public Order(Long id, String orderNumber, List<OrderLineItems> orderlineItemsList) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderlineItemsList = orderlineItemsList;
    }

    public Order() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<OrderLineItems> getOrderlineItemsList() {
        return orderlineItemsList;
    }

    public void setOrderlineItemsList(List<OrderLineItems> orderlineItemsList) {
        this.orderlineItemsList = orderlineItemsList;
    }
}
