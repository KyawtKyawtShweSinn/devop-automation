package com.lattmat.devOp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Home {

    @GetMapping("/home")
    public String welcomePage() {
        return "Welcome CI/CD project";
    }
}
