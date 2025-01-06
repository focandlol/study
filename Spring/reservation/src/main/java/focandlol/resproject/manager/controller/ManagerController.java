package focandlol.resproject.manager.controller;

import focandlol.resproject.manager.dto.ManagerSignUpDto;
import focandlol.resproject.manager.service.ManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 매니저(가게 주인) 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;

    /**
     * 매니저 회원가입 요청을 처리
     * - 회원가입 요청 정보를 받아 매니저 계정을 생성
     * - 현재 고객과 매니저의 회원가입 로직이 동일하지만, 역할별 확장을 고려해 분리된 구조로 설계
     *
     * @param request 회원가입 요청 정보
     * @return 회원가입 결과 응답
     */
    @PostMapping("/signUp")
    public ResponseEntity<ManagerSignUpDto.Response> signUp(@RequestBody @Valid ManagerSignUpDto.Request request) {
        return ResponseEntity.ok().body(managerService.managerSignUp(request));
    }
}
