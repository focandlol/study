package focandlol.reservation.controller;

import focandlol.reservation.dto.CustomerSignUpDto;
import focandlol.reservation.dto.ManagerSignUpDto;
import focandlol.reservation.service.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 회원 가입 컨트롤러
 * customer 와 manager로 나눈 이유
 * 1. customer, manager은 애플리케이션에서 수행하는 role이 다르기 때문에 회원 가입 시 필요 데이터와 처리 로직에서 차이가 있을 수 있어서 분리
 * 2. customer, manager 회원 가입 요청 dto가 다를 수 있음
 * 3. 추후 새로운 role이 생겼을 때도 구조를 확장하기 편함
 * 현재는 회원가입 시 customer와 manager의 로직이나 dto에 차이가 없지만 추후 변화가 생겼을 시를 대비해 분리했습니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/signUp")
public class SignUpController {

    private final SignUpService signUpService;

    /**
     * customer 회원 가입
     * @param request : customer 회원 가입 시 필요한 정보
     * @return
     */
    @PostMapping("/customer")
    public ResponseEntity<?> signUp(@RequestBody @Valid CustomerSignUpDto.Request request) {
        return ResponseEntity.ok().body(signUpService.customerSignUp(request));
    }

    /**
     * manager 회원 가입
     * @param request : manager 회원 가입 시 필요한 정보
     * @return
     */
    @PostMapping("/manager")
    public ResponseEntity<?> signUp(@RequestBody @Valid ManagerSignUpDto.Request request) {
        return ResponseEntity.ok().body(signUpService.managerSignUp(request));
    }
}
