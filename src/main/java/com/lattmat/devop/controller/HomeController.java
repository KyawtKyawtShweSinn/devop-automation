package com.lattmat.devop.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String welcomePage() {
        return "Welcome CI/CD project";
    }
}
