package com.lenercab.ribbonclient;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


//@RibbonClient(name="product001",  configuration = RibbonConfiguration.class)
//@FeignClient(name="product001", url = "localhost:8081")
//@FeignClient(name="product001")
@FeignClient(name="zuul")
@RibbonClient(name="product001")
public interface Product001ServiceProxy {

//  @GetMapping("/Product")
    @GetMapping("/product001/Product")
    public ResponseEntity<Object> GetProductAll();

//   @GetMapping("/Product/{id}")
    @GetMapping("/product001/Product/{id}")
    public ResponseEntity<Object> GetProductById(@PathVariable("id") Long id);

//  @PostMapping("/Product")
    @PostMapping("/product001/Product")
    public ResponseEntity<Object> saveProduct(@Valid @RequestBody Product product);

    @PutMapping("/Product")
    public ResponseEntity<Object> updateProduct(@Valid @RequestBody Product product);
}
