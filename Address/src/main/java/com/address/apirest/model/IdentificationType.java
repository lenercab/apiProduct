package com.address.apirest.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public class IdentificationType{

    @Id
    @Column(unique = true)
    private String code;

    @Column
    private String identificationType;

    @OneToMany(mappedBy = "identificationType")
    @Getter(AccessLevel.NONE)
    private Set<Identification> identification;

}