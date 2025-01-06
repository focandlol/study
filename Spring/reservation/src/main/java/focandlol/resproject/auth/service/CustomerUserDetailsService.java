package focandlol.resproject.auth.service;

import focandlol.resproject.auth.dto.CustomUserDetails;
import focandlol.resproject.auth.dto.UserDetailsDto;
import focandlol.resproject.customer.entity.CustomerEntity;
import focandlol.resproject.customer.repository.CustomerRepository;
import focandlol.resproject.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static focandlol.resproject.global.exception.ErrorCode.CUSTOMER_NOT_FOUND;

/**
 * Customer 인증 요청을 처리하기 위한 UserDetailsService 구현체
 * - username을 기준으로 CustomerRepository에서 사용자 정보를 조회
 * - 조회된 정보를 기반으로 CustomUserDetails를 생성하여 반환
 */
@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    /**
     * username을 기준으로 Customer 정보를 조회
     * - username에 해당하는 사용자가 없으면 CustomException
     * - 조회된 사용자 정보를 기반으로 CustomUserDetails를 생성하여 반환
     *
     * @param username 사용자 이름
     * @return 인증된 사용자의 UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerEntity customer = customerRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(CUSTOMER_NOT_FOUND));
        return new CustomUserDetails(UserDetailsDto.from(customer));
    }
}
