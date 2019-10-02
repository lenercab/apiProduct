package com.lenercab.producto001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class Producto001Application {

	public static void main(String[] args) {
		SpringApplication.run(Producto001Application.class, args);
	}

}
