package kkm.securityProject.users.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestLoginController {

    @PostMapping("/api/login")
    public String restLogin(){
        return "restLogin";
    }
}