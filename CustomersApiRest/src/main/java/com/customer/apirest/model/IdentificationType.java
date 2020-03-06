package com.customer.apirest.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@ApiModel(description = "This model class is representing the identification type of the customer")
@Entity
@Data
public class IdentificationType{

    @ApiModelProperty(notes = "The unique values accepted were CC, TI or CE", example = "CC", required = true, position = 1)
    @Id
    @Column(unique = true)
    @Pattern(regexp = "^CC|TI|CE$", message = "the values of the field code of the object identificationType should be CC|TI|CE")
    private String code;
    @Column 
    private String identificationType;

    @OneToMany(mappedBy = "identificationType")
    @Getter(AccessLevel.NONE)
    private Set<Identification> identification;

}