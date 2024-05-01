package sec.kkm;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    @PreFilter("filterObject.owner == authentication.name")
    public List<Account> writeList(List<Account> accounts) {
        return accounts;
    }
}
