package focandlol.reservation.customer.service;

import focandlol.reservation.auth.type.UserType;
import focandlol.reservation.customer.dto.CustomerSignUpDto;
import focandlol.reservation.customer.entity.CustomerEntity;
import focandlol.reservation.customer.repository.CustomerRepository;
import focandlol.reservation.global.exception.CustomException;
import focandlol.reservation.manager.dto.ManagerSignUpDto;
import focandlol.reservation.manager.entity.ManagerEntity;
import focandlol.reservation.manager.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static focandlol.reservation.global.exception.ErrorCode.USERNAME_ALREADY_USE;

/**
 * 고객 서비스
 * customer 와 manager로 나눈 이유
 * 1. customer, manager은 애플리케이션에서 수행하는 role이 다르기 때문에 회원 가입 시 필요 데이터와 처리 로직에서 차이가 있을 수 있어서 분리
 * 2. customer, manager 회원 가입 요청 dto가 다를 수 있음
 * 3. 추후 새로운 role이 생겼을 때도 구조를 확장하기 편함
 * customer, manager repository를 분리한 이유는
 * 현재는 회원가입 시 customer와 manager의 로직이나 dto, entity에 차이가 없지만 추후 변화가 생겼을 시를 대비해 분리했습니다.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * customer 회원가입
     * @param request : customer 회원 가입 시 필요한 정보
     * @return
     */
    @Override
    public CustomerSignUpDto.Response customerSignUp(CustomerSignUpDto.Request request) {

        if(customerRepository.existsByUsername(request.getUsername())) {
            throw new CustomException(USERNAME_ALREADY_USE);
        }

        return CustomerSignUpDto.Response.from(customerRepository.save(CustomerEntity.builder()
                        .username(request.getUsername())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .phoneNumber(request.getPhoneNumber())
                        .userType(UserType.CUSTOMER)
                        .build()));
    }

}
