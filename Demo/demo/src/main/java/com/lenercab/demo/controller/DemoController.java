package com.lenercab.demo.controller;

import javax.validation.Valid;

import com.lenercab.demo.model.DemoRs;
import com.lenercab.demo.service.DemoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class DemoController {

    Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DemoService demoService;

    @GetMapping("/Demo")
    public ResponseEntity<Object> getAllDemoRs() {

        logger.info("get to started controller getAllDemoRs");
        return ResponseEntity.ok().body(demoService.getAllDemoRs());
    }

    @GetMapping("/Demo/{id}")
    public ResponseEntity<Object> getByDemoRs(@PathVariable("id") Long id) {
        logger.info("get to started controller getByDemoRs");
        return ResponseEntity.ok().body(demoService.getByIdDemoRs(id).get());
    }

    @PostMapping(value = "/Demo")
    public ResponseEntity<Object> postDemo(@Valid @RequestBody DemoRs demoRs) {
        logger.info("get to started controller postDemo");
        return ResponseEntity.status(201).body(demoService.saveDemoRs(demoRs));
    }

    @PatchMapping(value = "/Demo")
    public ResponseEntity<Object> patchDemoRs(@RequestBody DemoRs demoRs) {
        logger.info("get to started controller patchDemoRs");
        return ResponseEntity.status(HttpStatus.CREATED).body(demoService.updateDemoRs(demoRs));
    }

    @DeleteMapping("/Demo/{id}")
    public ResponseEntity<Object> deleteDemoRs(@PathVariable("id") long id) {
        logger.info("get to started controller deleteDemoRs");
        String message = "fallo operacion delete";
        if (demoService.deletedDemoRs(id)) {
            message = "exitosa operacion delete";
        }
        return ResponseEntity.ok().body(message);
    }

}