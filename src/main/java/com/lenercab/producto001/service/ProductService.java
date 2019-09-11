package com.lenercab.producto001.service;

import com.lenercab.producto001.model.Product;
import com.lenercab.producto001.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product){

        productRepository.save(product);
        return product;
    }

    public Optional findById(Long id) {

        return productRepository.findById(id);
    }

    public List<Product> findALL(){
        return productRepository.findAll();
    }
}
