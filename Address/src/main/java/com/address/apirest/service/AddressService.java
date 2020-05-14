package com.address.apirest.service;

import com.address.apirest.dto.*;
import com.address.apirest.exception.AddressNotFoundException;
import com.address.apirest.exception.CustomerNotFoundExpection;
import com.address.apirest.model.Address;
import com.address.apirest.repository.AddressRepository;
import com.address.apirest.repository.CustomerRepository;
import com.address.apirest.util.Hateoas;
import com.address.apirest.util.Mapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressService {

    Logger logger = LoggerFactory.getLogger(AddressService.class);

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public AddressPSTrs insertAddress(AddressPSTrq addressPSTrq) {
        logger.info("get to started insertAddress operation");
        if (customerRepository.existsById(addressPSTrq.getCustomerId())) {
            Address address = Mapper.InputMapperAddressPST(addressPSTrq);
            addressRepository.save(address);
            AddressPSTrs addressPSTrs = new AddressPSTrs();
            addressPSTrs.setAddressId(address.getId());
            Hateoas.getHateoasAddressPST(addressPSTrs, addressPSTrq);
            return addressPSTrs;
        }
        throw new CustomerNotFoundExpection("Not found customer: id:" + addressPSTrq.getCustomerId());
    }

    public AddressPTCrs updateAddress(AddressPTCrq addressPTCrq) {
        logger.info("get to started updateAddress operation");
        if (!customerRepository.existsById(addressPTCrq.getCustomerId())) {
            throw new CustomerNotFoundExpection("Not found customer: id: " + addressPTCrq.getCustomerId());
        }
        if (!addressRepository.existsById(addressPTCrq.getId())) {
            throw new AddressNotFoundException("Not found address: id: " + addressPTCrq.getId());
        }
        Address address = Mapper.InputMapperAddressPTC(addressPTCrq);
        addressRepository.save(address);
        AddressPTCrs addressPTCrs = new AddressPTCrs();
        addressPTCrs.setAddressId(address.getId());
        Hateoas.getHateoasAddressPTC(addressPTCrs, addressPTCrq);
        return addressPTCrs;
    }

    public AddressGETALLrs getAllAddressByCustomerId(long customerId) {
        logger.info("log business get data of the entity address customer");
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundExpection("Not found customer: id:" + customerId);
        }
        List<Address> addresses = addressRepository.findAllByCustomerId(customerId);
        if (addresses.isEmpty()) {
            throw new AddressNotFoundException("Not found list of address of the customer id:" + customerId);
        }
        return Mapper.OutputMapperAddressesGETALL(addresses);

    }

    public AddressGETrs getAddressByIdAndCustomerId(long id, long customerId) {
        logger.info("logic business get data the entity address by Id and customer id");
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundExpection("Not found customer: id:" + customerId);
        }
        Address address = addressRepository.findByIdAndCustomerId(id, customerId);
        if (address == null) {
            throw new AddressNotFoundException("Not found address by id:" + id);
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(address, AddressGETrs.class);

    }

    @Transactional
    public Message deleteAdressyId(long id, long customerId) {

        logger.info("logic business for delete data of address");

        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundExpection("Not found customer: id:" + customerId);
        }

        if (!addressRepository.existsById(id)) {
            throw new AddressNotFoundException("Not found address by id: " + id);
        }


        addressRepository.deleteByIdAndCustomerId(id, customerId);

        Message message = new Message();
        message.setMessage("Address deleted successfully");

        return message;

    }
}
