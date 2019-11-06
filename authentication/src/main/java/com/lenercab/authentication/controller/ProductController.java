package com.lenercab.authentication.controller;


import com.lenercab.authentication.model.Product;
import com.lenercab.authentication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/Product")
    public ResponseEntity<Object> GetProductAll(){
        return ResponseEntity.ok(productService.findALL());
    }

    @GetMapping("/Product/{id}")
    public ResponseEntity<Object> GetProductById(@PathVariable("id") Long id){
        Optional product = productService.findById(id);

        return ResponseEntity.ok(product.get());

    }

    @PostMapping("/Product")
    public ResponseEntity<Object> saveProduct(@Valid @RequestBody Product product){
        return ResponseEntity.status(201).body(productService.saveProduct(product));
    }
    @PutMapping("/Product")
    public ResponseEntity<Object> updateProduct(@Valid @RequestBody Product product){
        return ResponseEntity.status(201).body(productService.updateProduct(product));
    }

    @DeleteMapping("/Product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id){

        return ResponseEntity.status(200).body(productService.deleteProduct(id));
    }
}
