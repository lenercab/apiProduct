package com.lenercab.producto001.repository;

import com.lenercab.producto001.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Optional findById(Long id);
}
