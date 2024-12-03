package focandlol.cms.user.service;

import focandlol.cms.user.domain.SignUpForm;
import focandlol.cms.user.domain.model.Customer;
import focandlol.cms.user.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpCustomerService {

    private final CustomerRepository customerRepository;

    public Customer signUp(SignUpForm form){
        return customerRepository.save(Customer.from(form));
    }
}
