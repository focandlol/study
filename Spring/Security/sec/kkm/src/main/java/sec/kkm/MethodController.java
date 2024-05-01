package sec.kkm;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MethodController {

    @GetMapping("/adminMethod")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String adminMethod() {
        return "adminMethod";
    }

    @GetMapping("/userMethod")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public String userMethod() {
        return "userMethod";
    }

    @GetMapping("/isAuthenticated")
    @PreAuthorize("isAuthenticated()")
    public String isuthenticated() {
        return "isAuthenticated";
    }

    @GetMapping("/userMethod/{id}")
    @PreAuthorize("#id == authentication.name")
    public String authentication(@PathVariable String id) {
        return id;
    }

    @GetMapping("/owner")
    @PreAuthorize("returnObject.owner == authentication.name")
    public String authentication(@)
}
