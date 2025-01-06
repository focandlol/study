package focandlol.resproject.customer.controller;

import focandlol.resproject.customer.dto.CustomerSignUpDto;
import focandlol.resproject.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 고객 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    /**
     * 고객 회원가입 요청을 처리
     * - 회원가입 요청 정보를 받아 고객 계정을 생성
     * - 현재 고객과 매니저의 회원가입 로직이 동일하지만, 역할별 확장을 고려해 분리된 구조로 설계
     *
     * @param request 회원가입 요청 정보
     * @return 회원가입 결과 응답
     */
    @PostMapping("/signUp")
    public ResponseEntity<CustomerSignUpDto.Response> signUp(@RequestBody @Valid CustomerSignUpDto.Request request) {
        return ResponseEntity.ok().body(customerService.customerSignUp(request));
    }

}
