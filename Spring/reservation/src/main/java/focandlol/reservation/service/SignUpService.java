package focandlol.reservation.service;

import focandlol.reservation.dto.CustomerSignUpDto;
import focandlol.reservation.entity.CustomerEntity;
import focandlol.reservation.repository.CustomerRepository;
import focandlol.reservation.repository.ManagerRepository;
import focandlol.reservation.type.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
@Transactional
public class SignUpService {

    private final CustomerRepository customerRepository;
    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerSignUpDto.Response customerSignUp(CustomerSignUpDto.Request request) {

        if(customerRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username is already in use");
        }

        return CustomerSignUpDto.Response.from(customerRepository.save(CustomerEntity.builder()
                        .username(request.getUsername())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .phoneNumber(request.getPhoneNumber())
                        .userType(UserType.CUSTOMER)
                        .build()));
    }

}
