package com.phone.phone.service;

import com.phone.phone.dto.*;
import com.phone.phone.exception.CustomerNotFoundExpection;
import com.phone.phone.exception.PhoneExistException;
import com.phone.phone.exception.PhoneNotFoundException;
import com.phone.phone.dto.Message;
import com.phone.phone.model.Phone;
import com.phone.phone.repository.CustomerRepository;
import com.phone.phone.repository.PhoneRepository;
import com.phone.phone.util.Hateoas;
import com.phone.phone.util.Mapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhoneService {

    Logger logger = LoggerFactory.getLogger(PhoneService.class);

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    CustomerRepository customerRepository;

    public PhonePSTrs insertPhone(PhonePSTrq phonePSTrq) {
        logger.info("logic business for insert basic data of phone");
        if (!customerRepository.existsById(phonePSTrq.getCustomerId())) {
            throw new CustomerNotFoundExpection("Not found customer: id:" + phonePSTrq.getCustomerId());
        }
        if (phoneRepository.exitNumberPhoneOfCustomer(phonePSTrq.getNumberPhone(), phonePSTrq.getCustomerId()) > 0) {
            throw new PhoneExistException("Number phone of the customer already exist: " + phonePSTrq.getNumberPhone());
        }
        Phone phone = Mapper.InputMapperPhonePST(phonePSTrq);
        phoneRepository.save(phone);

        PhonePSTrs phonePSTrs = new PhonePSTrs();
        phonePSTrs.setPhoneId(phone.getId());
        Hateoas.getHateoasPhonePST(phonePSTrs, phonePSTrq);
        return phonePSTrs;
    }

    public PhonePTCrs updatePhone(PhonePTCrq phonePTCrq) {

        logger.info("logic business for modify basic data of phone");
        if (!customerRepository.existsById(phonePTCrq.getCustomerId())) {
            throw new CustomerNotFoundExpection("Not found customer: id:" + phonePTCrq.getCustomerId());
        }
        if (!phoneRepository.existsById(phonePTCrq.getId())) {
            throw new PhoneNotFoundException("Not found phone by id: " + phonePTCrq.getId());
        }
        Phone phone = Mapper.InputMapperPhonePTC(phonePTCrq);
        phoneRepository.save(phone);

        PhonePTCrs phonePTCrs = new PhonePTCrs();
        phonePTCrs.setPhoneId(phone.getId());
        Hateoas.getHateoasPhonePTC(phonePTCrs,phonePTCrq);
        return phonePTCrs;
    }

    public PhonesGETALLrs getAllPhoneByCustomerId(long customerId) {
        logger.info("log business get data of the entity phone customer");
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundExpection("Not found customer: id:" + customerId);
        }
        List<Phone> phones = phoneRepository.findAllByCustomerId(customerId);
        if (phones.isEmpty()) {
            throw new PhoneNotFoundException("Not found list of phone of the customer id:" + customerId);
        }

        return Mapper.OutputMapperPhoneGETALL(phones);
    }

    public PhoneGETrs getPhoneByIdAndCustomerId(long id, long customerId) {
        logger.info("logic business get data the entity phone by Id and customer id");
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundExpection("Not found customer: id:" + customerId);
        }
        Phone phones = phoneRepository.findByIdAndCustomerId(id, customerId);
        if (phones == null) {
            throw new PhoneNotFoundException("Not found phone by id:" + id);
        }

        Phone phone = phoneRepository.findByIdAndCustomerId(id, customerId);

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(phone, PhoneGETrs.class);
    }

    @Transactional
    public Message deletePhoneById(long id, long customerId) {

        logger.info("logic business for delete data of phone");

        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundExpection("Not found customer: id:" + customerId);
        }

        if (!phoneRepository.existsById(id)) {
            throw new PhoneNotFoundException("Not found phone by id: " + id);
        }


        phoneRepository.deleteByIdAndCustomerId(id, customerId);

        Message message = new Message();
        message.setMessage("Phone deleted successfully");

        return message;

    }
}
