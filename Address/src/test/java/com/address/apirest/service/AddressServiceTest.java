package com.address.apirest.service;

import com.address.apirest.dto.*;
import com.address.apirest.exception.AddressNotFoundException;
import com.address.apirest.exception.CustomerNotFoundExpection;
import com.address.apirest.model.Address;
import com.address.apirest.repository.AddressRepository;
import com.address.apirest.repository.CustomerRepository;
import com.address.apirest.service.mock.MockAddresses;
import com.address.apirest.util.Mapper;
import mockit.Deencapsulation;
import mockit.MockUp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class AddressServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    AddressRepository addressRepository;

    @InjectMocks
    AddressService addressService;

    @Test
    public void insertAddress() {
        Address address = MockAddresses.getAddress();
        AddressPSTrq addressPSTrq = MockAddresses.getAddressPSTrq();
        when(customerRepository.existsById(1l)).thenReturn(true);
        new MockUp<Mapper>() {
            @mockit.Mock
            public Address InputMapperAddressPST(AddressPSTrq addressPSTrq) {
                return address;
            }
        };
        Deencapsulation.invoke(Mapper.class, "InputMapperAddressPST", addressPSTrq);
        when(addressRepository.save(address)).thenReturn(address);
        AddressPSTrs addressPSTrs = addressService.insertAddress(addressPSTrq);
        assertEquals("fail operation insert address", 1l, addressPSTrs.getAddressId());
    }

    @Test(expected = CustomerNotFoundExpection.class)
    public void insertAddress_when_not_found_customer() {

        Address address = MockAddresses.getAddress();
        AddressPSTrq addressPSTrq = MockAddresses.getAddressPSTrq();
        when(customerRepository.existsById(1l)).thenReturn(false);
        addressService.insertAddress(addressPSTrq);

    }


    @Test
    public void updateAddress() {

        Address address = MockAddresses.getAddress();
        AddressPTCrq addressPTCrq = MockAddresses.getAddressPTCrq();
        when(customerRepository.existsById(1l)).thenReturn(true);
        when(addressRepository.existsById(1l)).thenReturn(true);
        new MockUp<Mapper>() {
            @mockit.Mock
            public Address InputMapperAddressPTC(AddressPTCrq addressPTCrq) {
                return address;
            }
        };
        Deencapsulation.invoke(Mapper.class, "InputMapperAddressPTC", addressPTCrq);
        when(addressRepository.save(address)).thenReturn(address);
        AddressPTCrs addressPTCrs = addressService.updateAddress(addressPTCrq);
        assertEquals("fail operation modify address", 1l, addressPTCrs.getAddressId());

    }

    @Test(expected = CustomerNotFoundExpection.class)
    public void updateAddress_when_not_found_customer() {

        AddressPTCrq addressPTCrq = MockAddresses.getAddressPTCrq();
        when(customerRepository.existsById(1l)).thenReturn(false);
        addressService.updateAddress(addressPTCrq);

    }

    @Test(expected = AddressNotFoundException.class)
    public void updateAddress_when_not_found_address() {

        AddressPTCrq addressPTCrq = MockAddresses.getAddressPTCrq();
        when(customerRepository.existsById(1l)).thenReturn(true);
        when(addressRepository.existsById(1l)).thenReturn(false);
        addressService.updateAddress(addressPTCrq);
    }

    @Test
    public void getAllAddressByCustomerId() {

        List<Address> addresses = MockAddresses.getAllAddresses();
        when(customerRepository.existsById(1l)).thenReturn(true);
        when(addressRepository.findAllByCustomerId(1l)).thenReturn(addresses);
        AddressGETALLrs addressGETALLrs = addressService.getAllAddressByCustomerId(1l);
        assertFalse(addressGETALLrs.getAddresses().isEmpty());
    }

    @Test(expected = CustomerNotFoundExpection.class)
    public void getAllAddressByCustomerId_when_not_found_customer() {

        when(customerRepository.existsById(1l)).thenReturn(false);
        addressService.getAllAddressByCustomerId(1l);

    }

    @Test(expected = AddressNotFoundException.class)
    public void getAllAddressByCustomerId_when_not_found_address() {

        List<Address> addresses = MockAddresses.getAllAddresses();
        when(customerRepository.existsById(1l)).thenReturn(true);
        when(addressRepository.findAllByCustomerId(1l)).thenReturn(new ArrayList<>());
        addressService.getAllAddressByCustomerId(1l);

    }

    @Test
    public void getAddressByIdAndCustomerId() {

        Address address = MockAddresses.getAddress();
        when(customerRepository.existsById(1l)).thenReturn(true);
        when(addressRepository.findByIdAndCustomerId(1l, 1l)).thenReturn(address);
        AddressGETrs addressGETrs = addressService.getAddressByIdAndCustomerId(1l, 1l);
        assertNotNull(addressGETrs);
    }

    @Test(expected = CustomerNotFoundExpection.class)
    public void getAddressByIdAndCustomerId_when_not_foud_customer() {

        Address address = MockAddresses.getAddress();
        when(customerRepository.existsById(1l)).thenReturn(false);
        addressService.getAddressByIdAndCustomerId(1l, 1l);

    }

    @Test(expected = AddressNotFoundException.class)
    public void getAddressByIdAndCustomerId_when_not_found_address() {

        when(customerRepository.existsById(1l)).thenReturn(true);
        when(addressRepository.findByIdAndCustomerId(1l, 1l)).thenReturn(null);
        addressService.getAddressByIdAndCustomerId(1l, 1l);

    }

    @Test
    public void deleteAdressyId() {

       when(customerRepository.existsById(1l)).thenReturn(true);
       when(addressRepository.existsById(1l)).thenReturn(true);

       Message message = addressService.deleteAdressyId(1l, 1l);

       assertEquals("Address deleted successfully", message.getMessage());
    }

    @Test(expected = CustomerNotFoundExpection.class)
    public void deleteAdressyId_when_not_found_Customer() {

        when(customerRepository.existsById(1l)).thenReturn(false);
        addressService.deleteAdressyId(1l, 1l);

    }

    @Test(expected = AddressNotFoundException.class)
    public void deleteAdressyId_when_not_found_address() {

        when(customerRepository.existsById(1l)).thenReturn(true);
        when(addressRepository.existsById(1l)).thenReturn(false);
        addressService.deleteAdressyId(1l, 1l);

    }
}