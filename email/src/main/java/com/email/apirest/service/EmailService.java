package com.email.apirest.service;

import com.email.apirest.controller.EmailController;
import com.email.apirest.exception.CustomerNotFoundExpection;
import com.email.apirest.exception.EmailNotFoundException;
import com.email.apirest.model.Email;
import com.email.apirest.model.Message;
import com.email.apirest.repository.CustomerRepository;
import com.email.apirest.repository.EmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class EmailService {

    Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private CustomerRepository customerRepository;


    public List<Email> getAllEmails(long customerId) {
        logger.info("Entry logic bussiness get all emails customer");
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundExpection("Not found customer: id: " + customerId);
        }
        List<Email> emails = emailRepository.findAllByCustomerId(customerId);

        if (emails.isEmpty()) {
            throw new EmailNotFoundException("Not found email");
        }
        return emails;
    }

    public Email getByIdEmail(Long id, long customerId) {
        logger.info("Entry logic bussiness get by id  email of one customer");
        if (!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundExpection("Not found customer: id: " + customerId);
        }

        Email email = emailRepository.findByIdandCustomerId(id, customerId);
        if (email == null) {
            throw new EmailNotFoundException("Not found email");
        }
        return email;
    }

    public Email saveEmail(Email email) {
        logger.info("Entry logic bussiness insert one customer in the data base");
        if (customerRepository.existsById(email.getCustomerId())) {
            return emailRepository.save(email);
        }
        throw new CustomerNotFoundExpection("Not found customer: id: " + email.getCustomerId());
    }

    public Email updateEmail(Email email) {
        logger.info("Entry logic bussiness for modify register one customer in the data base");
        if (!customerRepository.existsById(email.getCustomerId())) {
            throw new CustomerNotFoundExpection("Not found customer: id: " + email.getCustomerId());
        }
        if (emailRepository.ExistsByIdAndCustomerId(email.getId(), email.getCustomerId()) == 0) {
            throw new EmailNotFoundException("Not found email");
        }
        return emailRepository.save(email);
    }

    public Message deleteEmailById(long id) {
        logger.info("Entry logic bussiness for delete  one customer in the data base");
        if (!emailRepository.existsById(id)) {
            throw new EmailNotFoundException("Not found email");
        }

        Message message = new Message();
        emailRepository.deleteById(id);
        message.setMessage("Se elimino Email: id: " + id);

        return message;
    }
}
