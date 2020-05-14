package com.phone.phone.service.model;

import com.phone.phone.dto.PhonePSTrq;
import com.phone.phone.dto.PhonePTCrq;
import com.phone.phone.model.Phone;

import java.util.ArrayList;
import java.util.List;

public class MockPhone {

    public static PhonePSTrq getPhonePSTrq(){
        PhonePSTrq phonePSTrq = new PhonePSTrq();
        phonePSTrq.setId(1l);
        phonePSTrq.setCustomerId(1l);
        phonePSTrq.setNumberPhone("3135718791");
        phonePSTrq.setOperator("TIGO");
        phonePSTrq.setTypePhone("CELULAR");
        return phonePSTrq;
    }

    public static Phone getPhone(){
        Phone phone = new Phone();
        phone.setId(1l);
        phone.setCustomerId(1l);
        phone.setNumberPhone("3135718791");
        phone.setOperator("TIGO");
        phone.setTypePhone("CELULAR");
        return phone;
    }

    public static PhonePTCrq getPhonePTCrq(){
        PhonePTCrq phonePTCrq = new PhonePTCrq();
        phonePTCrq.setId(1l);
        phonePTCrq.setCustomerId(1l);
        phonePTCrq.setNumberPhone("3135718791");
        phonePTCrq.setOperator("TIGO");
        phonePTCrq.setTypePhone("CELULAR");
        return phonePTCrq;
    }

    public static List<Phone> getAllPhone(){
        Phone phone = new Phone();
        phone.setId(1l);
        phone.setCustomerId(1l);
        phone.setNumberPhone("3135718791");
        phone.setOperator("TIGO");
        phone.setTypePhone("CELULAR");

        List<Phone> phones = new ArrayList<>();
        phones.add(phone);
        return phones;
    }
}
