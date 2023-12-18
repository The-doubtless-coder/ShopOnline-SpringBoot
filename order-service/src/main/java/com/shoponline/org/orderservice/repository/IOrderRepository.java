package com.shoponline.org.orderservice.repository;

import com.shoponline.org.orderservice.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Long> {


}
