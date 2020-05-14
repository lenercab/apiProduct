package com.address.apirest.controller.Mock;

import com.address.apirest.dto.*;

import java.util.ArrayList;
import java.util.List;

public class MockAddress {


    public static AddressGETrs getAddressGETrs(){

        AddressGETrs addressGETrs = new AddressGETrs();
        addressGETrs.setAddress("Kra75 #52a-86");
        addressGETrs.setCity("Bogota D.C");
        addressGETrs.setCountry("Colombia");
        addressGETrs.setLocation("Engativa");
        addressGETrs.setMainAddress(true);
        addressGETrs.setStreet("Normandia");
        addressGETrs.setCustomerId(1l);
        addressGETrs.setId(1l);

        return addressGETrs;
    }

    public static AddressGETALLrs getAddressGETALLrs(){

        AddressGETrs addressGETrs = new AddressGETrs();
        addressGETrs.setAddress("Kra75 #52a-86");
        addressGETrs.setCity("Bogota D.C");
        addressGETrs.setCountry("Colombia");
        addressGETrs.setLocation("Engativa");
        addressGETrs.setMainAddress(true);
        addressGETrs.setStreet("Normandia");
        addressGETrs.setCustomerId(1l);
        addressGETrs.setId(1l);

        List<AddressGETrs> addresses = new ArrayList<>();
        addresses.add(addressGETrs);

        AddressGETALLrs addressGETALLrs = new AddressGETALLrs();
        addressGETALLrs.setAddresses(addresses);

        return addressGETALLrs;
    }

    public static AddressPSTrq getAddressPSTrq(){

        AddressPSTrq addressPSTrq = new AddressPSTrq();
        addressPSTrq.setAddress("Kra75 #52a-86");
        addressPSTrq.setCity("Bogota D.C");
        addressPSTrq.setCountry("Colombia");
        addressPSTrq.setLocation("Engativa");
        addressPSTrq.setMainAddress(true);
        addressPSTrq.setStreet("Normandia");
        addressPSTrq.setCustomerId(1l);
        addressPSTrq.setId(1l);

        return addressPSTrq;
    }

    public static AddressPSTrs getAddressPSTrs(){

        AddressPSTrs addressPSTrs = new AddressPSTrs();
        addressPSTrs.setAddressId(1l);

        return addressPSTrs;
    }

    public static AddressPTCrq getAddressPTCrq(){

        AddressPTCrq addressPTCrq = new AddressPTCrq();
        addressPTCrq.setAddress("Kra75 #52a-86");
        addressPTCrq.setCity("Bogota D.C");
        addressPTCrq.setCountry("Colombia");
        addressPTCrq.setLocation("Engativa");
        addressPTCrq.setMainAddress(true);
        addressPTCrq.setStreet("Normandia");
        addressPTCrq.setCustomerId(1l);
        addressPTCrq.setId(1l);

        return addressPTCrq;
    }

    public static AddressPTCrs getAddressPTCrs(){

        AddressPTCrs addressPTCrs = new AddressPTCrs();
        addressPTCrs.setAddressId(1l);

        return addressPTCrs;
    }
}
