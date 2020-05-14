package com.phone.phone.controller.Mock;

import com.phone.phone.dto.*;

import java.util.ArrayList;
import java.util.List;

public class MockPhone {

   public static PhoneGETrs getPhoneGETrs(){

       PhoneGETrs phoneGETrs = new PhoneGETrs();
       phoneGETrs.setCustomerId(1l);
       phoneGETrs.setId(1l);
       phoneGETrs.setNumberPhone("3135718791");
       phoneGETrs.setOperator("TIGO");
       phoneGETrs.setTypePhone("Movil");

       return phoneGETrs;
   }

    public static PhonesGETALLrs getPhonesGETALLrs(){

        PhoneGETrs phoneGETrs = new PhoneGETrs();
        phoneGETrs.setCustomerId(1l);
        phoneGETrs.setId(1l);
        phoneGETrs.setNumberPhone("3135718791");
        phoneGETrs.setOperator("TIGO");
        phoneGETrs.setTypePhone("Movil");

        List<PhoneGETrs> phones = new ArrayList<>();
        phones.add(phoneGETrs);
        PhonesGETALLrs phonesGETALLrs = new PhonesGETALLrs();
        phonesGETALLrs.setPhones(phones);

        return phonesGETALLrs;
    }

    public static PhonePSTrq getPhonePSTrq(){

        PhonePSTrq phonePSTrq = new PhonePSTrq();
        phonePSTrq.setCustomerId(1l);
        phonePSTrq.setId(1l);
        phonePSTrq.setNumberPhone("3135718791");
        phonePSTrq.setOperator("TIGO");
        phonePSTrq.setTypePhone("Movil");

        return phonePSTrq;

    }

    public static PhonePSTrs getPhonePSTrs(){

        PhonePSTrs phonePSTrs = new PhonePSTrs();
        phonePSTrs.setPhoneId(1l);

        return phonePSTrs;

    }

    public static PhonePTCrq getPhonePTCrq(){

        PhonePTCrq phonePTCrq = new PhonePTCrq();
        phonePTCrq.setCustomerId(1l);
        phonePTCrq.setId(1l);
        phonePTCrq.setNumberPhone("3135718791");
        phonePTCrq.setOperator("TIGO");
        phonePTCrq.setTypePhone("Movil");

        return phonePTCrq;

    }

    public static PhonePTCrs getPhonePTCrs(){

        PhonePTCrs phonePTCrs = new PhonePTCrs();
        phonePTCrs.setPhoneId(1l);

        return phonePTCrs;

    }
}
