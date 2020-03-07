package com.email.apirest.repository;

import com.email.apirest.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    @Override
    public Optional<Email> findById(Long id);

    @Query("SELECT e FROM Email e WHERE e.id =:id AND customerId =:customerId")
    public Email findByIdandCustomerId(@Param("id") long id, @Param("customerId") long customerId);

    @Query("SELECT e FROM Email e WHERE customerId =:customerId")
    public List<Email> findAllByCustomerId(@Param("customerId") long customerId);

    @Query("SELECT COUNT(e) FROM Email e WHERE e.id =:id AND customerId =:customerId")
    public int ExistsByIdAndCustomerId(@Param("id") long id, @Param("customerId") long customerId);

}
