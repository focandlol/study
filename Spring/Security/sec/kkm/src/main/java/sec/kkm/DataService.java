package sec.kkm;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataService {

    @PreFilter("filterObject.owner == authentication.name")
    public List<Account> writeList(List<Account> accounts) {
        return accounts;
    }

    @PreFilter("filterObject.value.owner == authentication.name")
    public Map<String,Account> writeMap(Map<String,Account> accounts) {
        return accounts;
    }
}
