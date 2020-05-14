package com.address.apirest.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddressGETALLrs {

    private List<AddressGETrs> addresses = new ArrayList();

}
