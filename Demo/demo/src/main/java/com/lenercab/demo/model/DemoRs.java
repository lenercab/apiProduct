package com.lenercab.demo.model;

// import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
@Entity
public class DemoRs{

    @Id
    @GeneratedValue
    private long id;
    @Column @NotNull(message = "value field is required")
    private String value;
    @Column @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", message="date field has a format invalid o date not exist")
    private String date;
    @Column @Pattern(regexp = "^M|F$", message = "the sex field only accepts these two values M or F")
    private String Sexo;
    @Column @Pattern(regexp = "^\\d+$", message = "the numeroIdentificacion field only accept values numeric")
    private String numeroIdentificacion;
}