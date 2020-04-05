package com.email.apirest.service;


import com.email.apirest.dto.*;
import com.email.apirest.exception.CustomerNotFoundExpection;
import com.email.apirest.exception.EmailNotFoundException;
import com.email.apirest.model.Email;
import com.email.apirest.dto.Message;
import com.email.apirest.repository.CustomerRepository;
import com.email.apirest.repository.EmailRepository;
import com.email.apirest.util.Hateoas;
import com.email.apirest.util.Mapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {

    Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private CustomerRepository customerRepository;


    public EmailGETALL getAllEmails(long customerId) {
        logger.info("Entry logic business get all emails customer");
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundExpection("Not found customer: id: " + customerId);
        }
        List<Email> emails = emailRepository.findAllByCustomerId(customerId);

        if (emails.isEmpty()) {
            throw new EmailNotFoundException("Not found email");
        }

        EmailGETALL emailGETALL = new EmailGETALL();
        emailGETALL.setEmails(new ArrayList());
        for(Email email: emails){
            emailGETALL.getEmails().add(new EmailGETById(email.getId(),email.getEmail()));
        }

        return  emailGETALL;
    }

    public EmailGETById getByIdEmail(Long id, long customerId) {
        logger.info("Entry logic business get by id  email of one customer");
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundExpection("Not found customer: id: " + customerId);
        }

        Email email = emailRepository.findByIdandCustomerId(id, customerId);
        if (email == null) {
            throw new EmailNotFoundException("Not found email");
        }

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(email, EmailGETById.class);
    }

    public EmailPSTRs saveEmail(EmailPSTRq emailPSTRq) {
        logger.info("Entry logic business insert one customer in the data base");
        if (customerRepository.existsById(emailPSTRq.getCustomerId())) {


            Email email = Mapper.mapperRequestPST(emailPSTRq);
            emailRepository.save(email);

            EmailPSTRs emailPSTRs = new EmailPSTRs();
            emailPSTRs.setEmailId(email.getId());

            Hateoas.getHateoasEmailPST(emailPSTRs, emailPSTRq);

            return emailPSTRs;

        }
        throw new CustomerNotFoundExpection("Not found customer: id: " + emailPSTRq.getCustomerId());
    }

    public EmailPTCRs updateEmail(EmailPTCRq emailPTCRq) {
        logger.info("Entry logic business for modify register one customer in the data base");
        if (!customerRepository.existsById(emailPTCRq.getCustomerId())) {
            throw new CustomerNotFoundExpection("Not found customer: id: " + emailPTCRq.getCustomerId());
        }
        if (emailRepository.ExistsByIdAndCustomerId(emailPTCRq.getId(), emailPTCRq.getCustomerId()) == 0) {
            throw new EmailNotFoundException("Not found email");
        }

        Email email = Mapper.mapperRequestPTC(emailPTCRq);
        emailRepository.save(email);

        EmailPTCRs emailPTCRs = new EmailPTCRs();
        emailPTCRs.setEmailId(email.getId());
        Hateoas.getHateoasEmailPTC(emailPTCRs, emailPTCRq);
        return emailPTCRs;
    }

    @Transactional
    public Message deleteEmailById(long id, long customerId) {
        logger.info("Entry logic business for delete  one customer in the data base");
        if (!emailRepository.existsById(id)) {
            throw new EmailNotFoundException("Not found email");
        }

        Message message = new Message();
        emailRepository.deleteByIdAndCustomerId(id, customerId);
        message.setMessage("Email deleted successfully: id: " + id);

        return message;
    }
}
