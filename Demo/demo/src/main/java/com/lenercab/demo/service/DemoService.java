package com.lenercab.demo.service;

import java.util.List;

import com.lenercab.demo.controller.DemoController;
import com.lenercab.demo.model.DemoRs;
import com.lenercab.demo.repository.DemoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DemoService{

    Logger logger = LoggerFactory.getLogger(DemoService.class);

    @Autowired
    private DemoRepository demoRepository;

    public DemoRs saveDemoRs(DemoRs demoRs){
        logger.info("Get to started saveDemoRs");
        return demoRepository.save(demoRs);
    }  

    public boolean deletedDemoRs(long id){
        logger.info("Get to started deletedDemoRs");
        demoRepository.deleteById(id);
        return true;
    }

    public DemoRs updateDemoRs(DemoRs demoRs){
        logger.info("Get to started updateDemoRs");
        return demoRepository.save(demoRs);
    }

    public List<DemoRs> getAllDemoRs(){
        logger.info("Get to started getAllDemoRs");
        return demoRepository.findAll();
    }

    public Optional getByIdDemoRs(long id){
        logger.info("Get to started getByIdDemoRs");
        return demoRepository.findById(id);
    }
}