package br.com.washington.springsecurity.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableMethodSecurity
@RequiredArgsConstructor
@RestController
@RequestMapping("/protected")
public class ProtectedController {

    @GetMapping
    public String test() {
        return "olá";
    }


    @GetMapping(value = "/adm")
    public String adm() {

        return "Okay, you are administrator.";
    }

    @GetMapping(value = "/roles")
    public String roles() {
        return "Okay, you have roles.";
    }
}
