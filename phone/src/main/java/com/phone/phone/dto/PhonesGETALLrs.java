package com.phone.phone.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PhonesGETALLrs {

    private List<PhoneGETrs> phones = new ArrayList();
}
