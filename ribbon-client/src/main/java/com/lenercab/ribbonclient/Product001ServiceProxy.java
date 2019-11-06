package com.lenercab.ribbonclient;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


//@RibbonClient(name="product001",  configuration = RibbonConfiguration.class)
//@FeignClient(name="product001", url = "localhost:8081")
@FeignClient(name="product001")
@RibbonClient(name="product001")
public interface Product001ServiceProxy {


    @GetMapping("/Product")
    public ResponseEntity<Object> GetProductAll();

    @GetMapping("/Product/{id}")
    public ResponseEntity<Object> GetProductById(@PathVariable("id") Long id);

    @PostMapping("/Product")
    public ResponseEntity<Object> saveProduct(@Valid @RequestBody Product product);
}
