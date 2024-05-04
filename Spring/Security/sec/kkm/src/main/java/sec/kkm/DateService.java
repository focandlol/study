package sec.kkm;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


import org.springframework.security.access.prepost.PostAuthorize;

@Service
public class DateService {

    @PreAuthorize(value = "")
    public String getUser() {
        return "user";
    }
    @PostAuthorize(value = "")
    public Account getOwner(String name) {
        return new Account(name, false);
    }
    public String display() {
        return "display";
    }
}
