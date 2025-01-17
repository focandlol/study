package focandlol.oauth2.controller;

import focandlol.oauth2.dto.CustomOauth2User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/my")
    public String myAPI(@AuthenticationPrincipal CustomOauth2User user){
        return user.getName() + " " + user.getUsername();
    }
}
