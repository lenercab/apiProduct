package com.address.apirest.repository;

import com.address.apirest.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE customerId =:customerId")
    List<Address> findAllByCustomerId(@Param("customerId") long customerId);

    @Query("SELECT a FROM Address a WHERE a.id =:id AND customerId =:customerId")
    Address findByIdAndCustomerId(@Param("id") long id, @Param("customerId") long customerId);

    @Modifying
    @Query("DELETE FROM Address a WHERE a.id =:id AND a.customerId =:customerId")
    void deleteByIdAndCustomerId(@Param("id") long id, @Param("customerId") long customerId);
}
