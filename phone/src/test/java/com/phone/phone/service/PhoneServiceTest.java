package com.phone.phone.service;

import com.phone.phone.dto.*;
import com.phone.phone.exception.CustomerNotFoundExpection;
import com.phone.phone.exception.PhoneExistException;
import com.phone.phone.exception.PhoneNotFoundException;
import com.phone.phone.model.Phone;
import com.phone.phone.repository.CustomerRepository;
import com.phone.phone.repository.PhoneRepository;
import com.phone.phone.service.model.MockPhone;
import com.phone.phone.util.Mapper;
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
public class PhoneServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    PhoneRepository phoneRepository;

    @InjectMocks
    PhoneService phoneService;

    @Test
    public void insertPhone() {

        Phone phone = MockPhone.getPhone();
        PhonePSTrq phonePSTrq = MockPhone.getPhonePSTrq();

        when(customerRepository.existsById(1l)).thenReturn(true);
        when(phoneRepository.exitNumberPhoneOfCustomer(phonePSTrq.getNumberPhone(), 1l)).thenReturn(0);

        new MockUp<Mapper>() {
            @mockit.Mock
            public Phone InputMapperPhonePST(PhonePSTrq phonePSTrq) {
                return phone;
            }
        };
        Deencapsulation.invoke(Mapper.class, "InputMapperPhonePST", phonePSTrq);
        when(phoneRepository.save(phone)).thenReturn(phone);

        PhonePSTrs phonePSTrs = phoneService.insertPhone(phonePSTrq);

        assertEquals("fallo test insert phone", 1l, phonePSTrs.getPhoneId());

    }

    @Test(expected = CustomerNotFoundExpection.class)
    public void insertPhone_when_not_found_customer() {

        PhonePSTrq phonePSTrq = MockPhone.getPhonePSTrq();

        when(customerRepository.existsById(1l)).thenReturn(false);

        phoneService.insertPhone(phonePSTrq);


    }

    @Test(expected = PhoneExistException.class)
    public void insertPhone_when_exist_number_phone() {

        PhonePSTrq phonePSTrq = MockPhone.getPhonePSTrq();

        when(customerRepository.existsById(1l)).thenReturn(true);
        when(phoneRepository.exitNumberPhoneOfCustomer(phonePSTrq.getNumberPhone(), phonePSTrq.getCustomerId())).thenReturn(1);

        phoneService.insertPhone(phonePSTrq);


    }


    @Test
    public void updatePhone() {
        Phone phone = MockPhone.getPhone();
        PhonePTCrq phonePTCrq = MockPhone.getPhonePTCrq();

        when(customerRepository.existsById(1l)).thenReturn(true);
        when(phoneRepository.existsById(phonePTCrq.getId())).thenReturn(true);

        new MockUp<Mapper>() {
            @mockit.Mock
            public Phone InputMapperPhonePTC(PhonePTCrq phonePTCrq) {
                return phone;
            }
        };
        Deencapsulation.invoke(Mapper.class, "InputMapperPhonePTC", phonePTCrq);
        when(phoneRepository.save(phone)).thenReturn(phone);

        PhonePTCrs phonePTCrs = phoneService.updatePhone(phonePTCrq);

        assertEquals("fallo test update phone", 1l, phonePTCrs.getPhoneId());
    }

    @Test(expected = PhoneNotFoundException.class)
    public void updatePhone_when_not_found_phone() {

        PhonePTCrq phonePTCrq = MockPhone.getPhonePTCrq();

        when(customerRepository.existsById(1l)).thenReturn(true);
        when(phoneRepository.existsById(phonePTCrq.getId())).thenReturn(false);
        phoneService.updatePhone(phonePTCrq);

    }

    @Test(expected = CustomerNotFoundExpection.class)
    public void updatePhone_when_not_found_customer() {

        PhonePTCrq phonePTCrq = MockPhone.getPhonePTCrq();
        when(customerRepository.existsById(1l)).thenReturn(false);
        phoneService.updatePhone(phonePTCrq);

    }

    @Test
    public void getAllPhoneByCustomerId() {
        when(customerRepository.existsById(1l)).thenReturn(true);
        List<Phone> phones = MockPhone.getAllPhone();
        when(phoneRepository.findAllByCustomerId(1l)).thenReturn(phones);
        PhonesGETALLrs phonesGETALLrs = phoneService.getAllPhoneByCustomerId(1l);
        assertFalse("fail operation get all phones", phonesGETALLrs.getPhones().isEmpty());
    }

    @Test(expected = CustomerNotFoundExpection.class)
    public void getAllPhoneByCustomerId_when_not_found_customer() {
        when(customerRepository.existsById(1l)).thenReturn(false);
        phoneService.getAllPhoneByCustomerId(1l);
    }

    @Test(expected = PhoneNotFoundException.class)
    public void getAllPhoneByCustomerId_when_not_found_phones() {

        when(customerRepository.existsById(1l)).thenReturn(true);
        when(phoneRepository.findAllByCustomerId(1l)).thenReturn(new ArrayList<>());
        phoneService.getAllPhoneByCustomerId(1l);

    }

    @Test
    public void getPhoneByIdAndCustomerId() {
        when(customerRepository.existsById(1l)).thenReturn(true);
        Phone phone = MockPhone.getPhone();
        when(phoneRepository.findByIdAndCustomerId(1l, 1l)).thenReturn(phone);
        PhoneGETrs phoneGETrs = phoneService.getPhoneByIdAndCustomerId(1l, 1l);
        assertNotNull("fail operation get phones", phoneGETrs);
    }

    @Test(expected = CustomerNotFoundExpection.class)
    public void getPhoneByIdAndCustomerId_when_not_found_customer() {

        when(customerRepository.existsById(1l)).thenReturn(false);
        phoneService.getPhoneByIdAndCustomerId(1l, 1l);

    }

    @Test(expected = PhoneNotFoundException.class)
    public void getPhoneByIdAndCustomerId_when_not_found_phone() {

        when(customerRepository.existsById(1l)).thenReturn(true);
        when(phoneRepository.findByIdAndCustomerId(1l, 1l)).thenReturn(null);
        phoneService.getPhoneByIdAndCustomerId(1l, 1l);

    }

    @Test
    public void deletePhoneById() {

        when(customerRepository.existsById(1l)).thenReturn(true);
        when(phoneRepository.existsById(1l)).thenReturn(true);

        Message message = phoneService.deletePhoneById(1l, 1l);
        assertEquals("fail operation delete phone", "Phone deleted successfully", message.getMessage());
    }

    @Test(expected = CustomerNotFoundExpection.class)
    public void deletePhoneById_when_not_found_customer() {

        when(customerRepository.existsById(1l)).thenReturn(false);
        phoneService.deletePhoneById(1l, 1l);

    }

    @Test(expected = PhoneNotFoundException.class)
    public void deletePhoneById_when_not_found_phone() {

        when(customerRepository.existsById(1l)).thenReturn(true);
        when(phoneRepository.existsById(1l)).thenReturn(false);
        phoneService.deletePhoneById(1l, 1l);

    }
}