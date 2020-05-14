package com.lenercab.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoRepositoryTest {

    @Autowired
    DemoRepository demoRepository;

    @Test
    void findById() {
        Optional optional = demoRepository.findById(1L);
        System.out.println(optional);
        assertNotNull(optional);
    }
}