package com.phone.phone.repository;

import com.phone.phone.model.Phone;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    @Query("SELECT p FROM Phone p WHERE customerId =:customerId")
    List<Phone> findAllByCustomerId(@Param("customerId") long customerId);

    @Query("SELECT p FROM Phone p WHERE p.id =:id AND customerId =:customerId")
    Phone findByIdAndCustomerId(@Param("id") long id, @Param("customerId") long customerId);

    @Query("SELECT COUNT(p) FROM Phone p WHERE p.numberPhone =:numberPhone AND p.customerId =:customerId")
    int exitNumberPhoneOfCustomer(@Param("numberPhone") String numberPhone, @Param("customerId") long customerId);

    @Modifying
    @Query("DELETE FROM Phone p WHERE p.id =:id AND p.customerId =:customerId")
    void deleteByIdAndCustomerId(@Param("id") long id, @Param("customerId") long customerId);

}
