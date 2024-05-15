package kkm.securityProject.users.controller;

import kkm.securityProject.domain.dto.AccountDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @GetMapping(value="/user")
    public AccountDto restUser(@AuthenticationPrincipal AccountDto accountDto) {
        return accountDto;
    }

    @GetMapping(value="/manager")
    public AccountDto restManager(@AuthenticationPrincipal AccountDto accountDto) {
        return accountDto;
    }

    @GetMapping(value="/admin")
    public AccountDto restAdmin(@AuthenticationPrincipal AccountDto accountDto) {
        return accountDto;
    }
}
