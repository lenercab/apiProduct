package com.lenercab.demo.repository;

import java.util.Optional;

import com.lenercab.demo.model.DemoRs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends JpaRepository<DemoRs, Long>{
 
    public Optional findById(long id);

}