package focandlol.reservation.auth.service;

import focandlol.reservation.global.exception.CustomException;
import focandlol.reservation.auth.dto.CustomUserDetails;
import focandlol.reservation.auth.dto.UserDetailsDto;
import focandlol.reservation.customer.entity.CustomerEntity;
import focandlol.reservation.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static focandlol.reservation.global.exception.ErrorCode.*;

/**
 * custom 인증 요청 시 사용되는 UserDetailService
 */
@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    /**
     * customerRepository에서 customer 정보 조회
     * 값이 없을 경우 exception
     * 값이 있을 경우 CustomUserDetails 만들어서 리턴
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomerEntity> findCustomer = customerRepository.findByUsername(username);

        if(findCustomer.isPresent()) {
            CustomerEntity customer = findCustomer.get();
            return new CustomUserDetails(UserDetailsDto.from(customer));
        }

        throw new CustomException(CUSTOMER_NOT_FOUND);
    }

}
