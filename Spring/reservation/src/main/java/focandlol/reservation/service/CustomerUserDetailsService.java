package focandlol.reservation.service;

import focandlol.reservation.exception.CustomException;
import focandlol.reservation.dto.CustomUserDetails;
import focandlol.reservation.dto.UserDetailsDto;
import focandlol.reservation.entity.auth.CustomerEntity;
import focandlol.reservation.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static focandlol.reservation.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("cusotmerUserDetailService");

        Optional<CustomerEntity> findCustomer = customerRepository.findByUsername(username);

        if(findCustomer.isPresent()) {
            CustomerEntity customer = findCustomer.get();
            return new CustomUserDetails(UserDetailsDto.from(customer));
        }

        throw new CustomException(CUSTOMER_NOT_FOUND);
    }

}
