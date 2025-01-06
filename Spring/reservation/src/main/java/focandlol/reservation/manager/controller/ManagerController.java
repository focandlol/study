package focandlol.reservation.manager.controller;

import focandlol.reservation.manager.service.ManagerService;
import focandlol.reservation.manager.dto.ManagerSignUpDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 매니저 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;

    /**
     * manager 회원 가입
     * 회원가입을 customer 와 manager로 나눈 이유
     * 1. customer, manager은 애플리케이션에서 수행하는 role이 다르기 때문에 회원 가입 시 필요 데이터와 처리 로직에서 차이가 있을 수 있어서 분리
     * 2. customer, manager 회원 가입 요청 dto가 다를 수 있음
     * 3. 추후 새로운 role이 생겼을 때도 구조를 확장하기 편함
     * 현재는 회원가입 시 customer와 manager의 로직이나 dto, entitiy에 차이가 없지만 추후 변화가 생겼을 시를 대비해 분리했습니다.
     * @param request : manager 회원 가입 시 필요한 정보
     * @return
     */
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody @Valid ManagerSignUpDto.Request request) {
        return ResponseEntity.ok().body(managerService.managerSignUp(request));
    }
}
