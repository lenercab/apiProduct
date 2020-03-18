package com.phone.phone.util;

import com.phone.phone.dto.PhoneGETrs;
import com.phone.phone.dto.PhonePSTrq;
import com.phone.phone.dto.PhonePTCrq;
import com.phone.phone.dto.PhonesGETALLrs;
import com.phone.phone.model.Phone;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static Phone InputMapperPhonePST(PhonePSTrq phonePSTrq){

        Phone phone = new Phone();
        phone.setNumberPhone(phonePSTrq.getNumberPhone());
        phone.setOperator(phonePSTrq.getOperator());
        phone.setTypePhone(phonePSTrq.getTypePhone());
        phone.setCustomerId(phonePSTrq.getCustomerId());

        return phone;

    }

    public static Phone InputMapperPhonePTC(PhonePTCrq phonePTCrq){

        Phone phone = new Phone();
        phone.setNumberPhone(phonePTCrq.getNumberPhone());
        phone.setOperator(phonePTCrq.getOperator());
        phone.setTypePhone(phonePTCrq.getTypePhone());
        phone.setCustomerId(phonePTCrq.getCustomerId());
        phone.setId(phonePTCrq.getId());

        return phone;

    }

    public static PhonesGETALLrs OutputMapperPhoneGETALL(List<Phone> phones){

        PhonesGETALLrs phonesGETALLrs = new PhonesGETALLrs();
        for(Phone phone: phones){
            phonesGETALLrs.getPhones().add(
                    new PhoneGETrs(phone.getId(),
                                   phone.getNumberPhone(),
                                   phone.getTypePhone(),
                                   phone.getOperator(),
                                   phone.getCustomerId()));
        }

        return phonesGETALLrs;

    }
}
