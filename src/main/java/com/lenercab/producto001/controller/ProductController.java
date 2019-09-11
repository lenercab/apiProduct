package com.lenercab.producto001.controller;

import com.lenercab.producto001.model.Product;
import com.lenercab.producto001.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping("/Product")
    public ResponseEntity<Object> saveProduct(@RequestBody Product product){
        return ResponseEntity.status(201).body(productService.saveProduct(product));
    }
    @PutMapping("/Product")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product){
        return ResponseEntity.status(201).body(productService.updateProduct(product));
    }

    @DeleteMapping("/Product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Long id){
        return ResponseEntity.status(200).body(productService.deleteProduct(id));
    }
}
