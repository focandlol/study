package focandlol.resproject.customer.service;

import focandlol.resproject.customer.dto.CustomerSignUpDto;

/**
 * 고객 서비스 인터페이스
 */
public interface CustomerService {
    /**
     * 고객 회원가입 처리
     *
     * @param request 회원가입 요청 정보
     * @return 가입된 회원 정보
     */
    CustomerSignUpDto.Response customerSignUp(CustomerSignUpDto.Request request);
}
