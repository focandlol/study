package focandlol.jwt.controller;

import focandlol.jwt.dto.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin")
    public String adminP(@AuthenticationPrincipal CustomUserDetails user){
        return "admin Controller" + user.getUsername();
    }
}
