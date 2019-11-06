package com.lenercab.ribbonclient;


import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ControllerRibbonClient {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Product001ServiceProxy proxy;

    @GetMapping("/RibbonFeing/Product")
    public ResponseEntity<Object> GetProductAllFeing(){
        return proxy.GetProductAll();
    }

    @GetMapping("/RibbonFeing/Product/{id}")
    public ResponseEntity<Object> GetProductByIdFeing(@PathVariable("id") Long id){
        return proxy.GetProductById(id);
    }

    @PostMapping("/RibbonFeing/Product")
    public ResponseEntity<Object> PostProduct(@Valid @RequestBody Product product){
        return proxy.saveProduct(product);
    }
}
