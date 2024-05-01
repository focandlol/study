package sec.kkm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("customWebSecurity")
public class CustomWebSecurity {

    public boolean check(Authentication authentication, HttpServletRequest request) {
        System.out.println("authentication = " + authentication.isAuthenticated());
        System.out.println("authentication = " + authentication.getName());
        return authentication.isAuthenticated();
    }
}
