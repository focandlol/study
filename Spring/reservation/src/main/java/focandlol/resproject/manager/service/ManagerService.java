package focandlol.resproject.manager.service;

import focandlol.resproject.manager.dto.ManagerSignUpDto;

/**
 * 매니저 서비스 인터페이스
 */
public interface ManagerService {

    /**
     * 매니저 회원가입 처리
     *
     * @param request 회원가입 요청 정보
     * @return 가입된 회원 정보
     */
    ManagerSignUpDto.Response managerSignUp(ManagerSignUpDto.Request request);
}
