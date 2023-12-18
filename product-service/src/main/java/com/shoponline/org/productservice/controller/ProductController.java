package com.shoponline.org.productservice.controller;

import com.shoponline.org.productservice.dtos.ProductRequestDTO;
import com.shoponline.org.productservice.dtos.ProductResponseDTO;
import com.shoponline.org.productservice.exceptions.NoProductsException;
import com.shoponline.org.productservice.exceptions.ProductNameExistsException;
import com.shoponline.org.productservice.exceptions.ProductNotAvailableEx;
import com.shoponline.org.productservice.models.Product;
import com.shoponline.org.productservice.service.IProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductController {

    private  final IProductService iProductService;
    private final ModelMapper modelMapper = new ModelMapper();

    public ProductController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED, code = HttpStatus.CREATED)
    public void Create(@RequestBody @Valid ProductRequestDTO productRequestDTO){
        //validate the object using hibernate validators
        try{
            iProductService.Create(productRequestDTO);
        }catch (ProductNameExistsException ex){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "product name already in use", ex);
        }catch (Exception ex){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "error: " + ex.getMessage(), ex);
        }
        //return a dto
    }
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    public List<ProductResponseDTO> GetAllProducts(){
        List<ProductResponseDTO> productResponseDTOList = new ArrayList<>();
        try {
            List<Product> productList = iProductService.GetAll();
            for (Product product : productList) {
                ProductResponseDTO mapped =
                        modelMapper.map(product, ProductResponseDTO.class);
                productResponseDTOList.add(mapped);
            }
            return productResponseDTOList;
        } catch (NoProductsException e) {
            throw new ResponseStatusException(
                    HttpStatus.NO_CONTENT, "product tbl is empty", e);
        } catch (Exception ex){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "error: " + ex.getMessage(), ex);
        }
        //return a dto
    }

    @GetMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    public ProductResponseDTO GetProductById(@PathVariable long id){
        try {
            Optional<Product> optionalProduct = iProductService.GetById(id);
            ProductResponseDTO mapped = null;
            if(optionalProduct.isPresent()){
                mapped = modelMapper.map(optionalProduct.get(), ProductResponseDTO.class);
            }
            return mapped;

        }catch (ProductNotAvailableEx ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "product with that id does not exist" , ex);
        }
        catch (Exception ex){
            //log to console
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "error: " + ex.getMessage(), ex);
        }
        //return a dto
    }


//find list by name containing





    //handle validation exceptions
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error ->
//                errors.put(error.getField(), error.getDefaultMessage()));
//        return errors;
//    }

}
