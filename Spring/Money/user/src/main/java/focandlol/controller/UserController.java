package focandlol.controller;

import focandlol.domain.UserInfo;
import focandlol.repository.UserInfoRepository;
import focandlol.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fintech/v1/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/information")
    public String user() {
        return userService.save(UserInfo.builder()
                        .userKey("Asd")
                        .userRegistrationNumber("Asd")
                        .userIncomeAmount(1000L)
                        .userName("kkm")
                .build());
    }
}
