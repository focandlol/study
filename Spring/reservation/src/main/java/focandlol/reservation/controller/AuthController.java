package focandlol.reservation.controller;

import focandlol.reservation.dto.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @GetMapping("/hello")
    public String hello(@AuthenticationPrincipal CustomUserDetails user) {
        return "hello "  + user.getUsername() + " " + user.getAuthorities();
    }
}
