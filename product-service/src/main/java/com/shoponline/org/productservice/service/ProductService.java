package com.shoponline.org.productservice.service;

import com.shoponline.org.productservice.dtos.ProductRequestDTO;
import com.shoponline.org.productservice.exceptions.NoProductsException;
import com.shoponline.org.productservice.exceptions.ProductNameExistsException;
import com.shoponline.org.productservice.exceptions.ProductNotAvailableEx;
import com.shoponline.org.productservice.models.Product;
import com.shoponline.org.productservice.repository.IProductRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductService implements IProductService{

    private final IProductRepository iProductRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final Logger LOGGER = Logger.getLogger( ProductService.class.getName() );

    public ProductService(IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;

    }

    @Override
    public void Create(ProductRequestDTO productRequestDTO) throws ProductNameExistsException {
        //ensure name does not exist
        Optional<Product> optionalProduct = iProductRepository.
                findByName(productRequestDTO.getName());
        if(optionalProduct.isPresent()){
            LOGGER.fine("error: prod name already exists");
            throw new ProductNameExistsException("product with name::"
                    +productRequestDTO.getName() + " is already available");
        }
        //else, save product
        Product mapped = modelMapper.map(productRequestDTO, Product.class);
        Product save = iProductRepository.save(mapped);
        LOGGER.log(Level.FINE, "product saved to db", save);
    }

    @Override
    public List<Product> GetAll() throws NoProductsException {
        List<Product> productList = iProductRepository.findAll();
        if(productList.isEmpty()){
            throw new NoProductsException("product tbl is empty");
        }
        return productList;
    }

    @Override
    public Optional<Product> GetById(Long id) throws ProductNotAvailableEx {
        Optional<Product> optionalProduct = iProductRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw  new ProductNotAvailableEx("prod with id::"+ id + " not available");
        }
        return optionalProduct;
    }

    @Override
    public Optional<List<Product>> GetAllByNameContaining(String Name) {

        return Optional.empty();
    }
}
