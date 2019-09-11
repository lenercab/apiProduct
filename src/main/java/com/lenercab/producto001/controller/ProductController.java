package com.lenercab.producto001.controller;

import com.lenercab.producto001.model.Product;
import com.lenercab.producto001.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/Product")
    public List<Product> GetProductAll(){
        return productService.findALL();
    }

    @GetMapping("/Product/{id}")
    public Optional GetProductById(@PathVariable("id") Long id){
        return productService.findById(id);
    }

    @PostMapping("/Product")
    public String saveProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }
    @PutMapping("/Product")
    public String updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @DeleteMapping("/Product/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }
}
