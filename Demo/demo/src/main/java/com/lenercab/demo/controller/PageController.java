package com.lenercab.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PageController {

    @GetMapping("/User")
    public String getPageUser() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/Admin")
    public String getPageAdmin() {
        return ("<h1>Welcome Admin</h1>");
    }

    @GetMapping("/")
    public String getPageWelcome() {
        return ("<h1>Welcome Welcome</h1>");
    }
}
