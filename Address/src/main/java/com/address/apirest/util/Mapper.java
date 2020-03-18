package com.address.apirest.util;

import com.address.apirest.dto.AddressGETALLrs;
import com.address.apirest.dto.AddressGETrs;
import com.address.apirest.dto.AddressPSTrq;
import com.address.apirest.dto.AddressPTCrq;
import com.address.apirest.model.Address;

import java.util.List;

public class Mapper {

    public static Address InputMapperAddressPST(AddressPSTrq addressPSTrq) {

        Address address = new Address();
        address.setAddress(addressPSTrq.getAddress());
        address.setCity(addressPSTrq.getCity());
        address.setCountry(addressPSTrq.getCountry());
        address.setLocation(addressPSTrq.getLocation());
        address.setStreet(addressPSTrq.getStreet());
        address.setMainAddress(addressPSTrq.isMainAddress());
        address.setCustomerId(addressPSTrq.getCustomerId());

        return address;

    }

    public static Address InputMapperAddressPTC(AddressPTCrq addressPTCrq) {

        Address address = new Address();
        address.setAddress(addressPTCrq.getAddress());
        address.setCity(addressPTCrq.getCity());
        address.setCountry(addressPTCrq.getCountry());
        address.setLocation(addressPTCrq.getLocation());
        address.setStreet(addressPTCrq.getStreet());
        address.setMainAddress(addressPTCrq.isMainAddress());
        address.setCustomerId(addressPTCrq.getCustomerId());
        address.setId(addressPTCrq.getId());

        return address;

    }

    public static AddressGETALLrs OutputMapperAddressesGETALL(List<Address> addresses){

        AddressGETALLrs addressGETALLrs = new AddressGETALLrs();
        for(Address address: addresses){
            addressGETALLrs.getAddresses().add(
                    new AddressGETrs(address.getId(),
                            address.getAddress(),
                            address.getCountry(),
                            address.getCity(),
                            address.getLocation(),
                            address.getStreet(),
                            address.isMainAddress(),
                            address.getCustomerId()));
        }

        return addressGETALLrs;

    }

}
