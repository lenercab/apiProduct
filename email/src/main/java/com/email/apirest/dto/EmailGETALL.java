package com.email.apirest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class EmailGETALL {

    private List<EmailGETById> emails;
}
