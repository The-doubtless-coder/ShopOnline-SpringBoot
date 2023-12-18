package com.shoponline.org.productservice.repository;

import com.shoponline.org.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String Name);
}
