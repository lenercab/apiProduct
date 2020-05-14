package com.phone.phone.repository;


import com.phone.phone.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Optional findById(Long id);

    @Query("SELECT c FROM Customer c JOIN Identification i ON c.identification = i.id JOIN Gender g ON c.gender = g.code JOIN IdentificationType t ON t.code = i.identificationType WHERE i.numberIdentification =:ni AND t.code=:cc")
    public Customer findByNumerIdentification(@Param("ni") String numberIdentification, @Param("cc") String identificationType);


}
