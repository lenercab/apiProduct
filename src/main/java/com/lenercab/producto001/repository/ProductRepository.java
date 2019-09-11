package com.lenercab.producto001.repository;

import com.lenercab.producto001.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional findById(Long id);
}
