package com.lenercab.ribbonclient;


import com.netflix.appinfo.InstanceInfo;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
/*@RibbonClient(
        name = "product001",
        configuration = RibbonConfiguration.class) */
public class ControllerRibbonClient {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /*@Autowired
    private DiscoveryClient discoveryClient;

    @LoadBalanced
    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/Ribbon/Product")
    public ResponseEntity GetProductAll(){

        List<ServiceInstance> instance = this.discoveryClient.getInstances("product001");
        System.out.println("instancia");
        System.out.println(instance.get(0).getUri());
        return   this.restTemplate.getForObject(instance.get(0).getUri()+"/Product",
                 ResponseEntity.class);
    }*/
    @Autowired
    private Product001ServiceProxy proxy;

    @GetMapping("/RibbonFeing/Product")
    public ResponseEntity<Object> GetProductAllFeing(){

        return proxy.GetProductAll();

    }

}
