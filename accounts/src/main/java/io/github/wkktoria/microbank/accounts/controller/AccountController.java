package io.github.wkktoria.microbank.accounts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @GetMapping("sayHello")
    public String sayHello() {
        return "Hello Account Controller!";
    }
}
