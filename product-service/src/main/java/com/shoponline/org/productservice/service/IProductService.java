package com.shoponline.org.productservice.service;

import com.shoponline.org.productservice.dtos.ProductRequestDTO;
import com.shoponline.org.productservice.exceptions.NoProductsException;
import com.shoponline.org.productservice.exceptions.ProductNameExistsException;
import com.shoponline.org.productservice.exceptions.ProductNotAvailableEx;
import com.shoponline.org.productservice.models.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    public void Create(ProductRequestDTO productRequestDTO) throws ProductNameExistsException;
    public List<Product> GetAll() throws NoProductsException;
    public Optional<Product> GetById(Long id) throws ProductNotAvailableEx;
    public Optional<List<Product>> GetAllByNameContaining(String Name);

}
