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

    public String saveProduct(Product product){

        productRepository.save(product);
        return "Product fue insertado con exito";
    }

    public Optional findById(Long id) {

        return productRepository.findById(id);
    }

    public List<Product> findALL(){
        return productRepository.findAll();
    }

    public String updateProduct(Product product){
        productRepository.save(product);
        return "Product fue actualizado con exito";
    }

    public String deleteProduct(Long id){
        productRepository.deleteById(id);
        return "Product fue eliminado con exito";
    }
}
