package com.customer.apirest.model;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Gender {

    @Id
    @Column(unique = true)
    private String code;

    @Column(unique = true)
    private String description;

    @OneToMany(mappedBy = "gender")
    @Getter(AccessLevel.NONE)
    private Set<Customer> customers;

}