package com.address.apirest.service.mock;

import com.address.apirest.dto.AddressPSTrq;
import com.address.apirest.dto.AddressPTCrq;
import com.address.apirest.dto.AddressPTCrs;
import com.address.apirest.model.Address;

import java.util.ArrayList;
import java.util.List;

public class MockAddresses {

    public static Address getAddress(){
        Address address = new Address();
        address.setStreet("Kra 75 #52a-86");
        address.setMainAddress(true);
        address.setLocation("Engativa");
        address.setCity("Bogota D.C");
        address.setCountry("Colombia");
        address.setId(1l);
        address.setCustomerId(1l);

        return address;
    }

    public static List<Address> getAllAddresses(){
        List<Address> addresses = new ArrayList<>();

        Address address = new Address();
        address.setStreet("Kra 75 #52a-86");
        address.setMainAddress(true);
        address.setLocation("Engativa");
        address.setCity("Bogota D.C");
        address.setCountry("Colombia");
        address.setId(1l);
        address.setCustomerId(1l);
        addresses.add(address);

        return addresses;
    }

    public static AddressPSTrq getAddressPSTrq(){
        AddressPSTrq addressPSTrq = new AddressPSTrq();
        addressPSTrq.setStreet("Kra 75 #52a-86");
        addressPSTrq.setMainAddress(true);
        addressPSTrq.setLocation("Engativa");
        addressPSTrq.setCity("Bogota D.C");
        addressPSTrq.setCountry("Colombia");
        addressPSTrq.setId(1l);
        addressPSTrq.setCustomerId(1l);

        return addressPSTrq;
    }

    public static AddressPTCrq getAddressPTCrq(){

        AddressPTCrq addressPTCrq = new AddressPTCrq();
        addressPTCrq.setStreet("Kra 75 #52a-86");
        addressPTCrq.setMainAddress(true);
        addressPTCrq.setLocation("Engativa");
        addressPTCrq.setCity("Bogota D.C");
        addressPTCrq.setCountry("Colombia");
        addressPTCrq.setId(1l);
        addressPTCrq.setCustomerId(1l);

        return addressPTCrq;
    }
}
