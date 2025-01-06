package focandlol.resproject.manager.service;

import focandlol.resproject.auth.type.UserType;
import focandlol.resproject.global.exception.CustomException;
import focandlol.resproject.manager.dto.ManagerSignUpDto;
import focandlol.resproject.manager.entity.ManagerEntity;
import focandlol.resproject.manager.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static focandlol.resproject.global.exception.ErrorCode.USERNAME_ALREADY_USE;

/**
 * 매니저 서비스 구현체.
 * - 고객과 매니저를 분리한 이유:
 * 1. 고객(customer)과 매니저(manager)의 역할이 다르기 때문에 회원가입 시 필요한 데이터와 처리 로직이 다를 수 있음
 * 2. 회원가입 요청 dto가 다를 가능성을 고려하여 구조를 분리
 * 3. 새로운 역할(role)이 추가될 경우 확장성을 높이기 위해 설계
 * - 현재는 로직, dto, 엔티티에 차이가 없으나, 향후 변화를 대비해 구조 분리
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * manager 회원가입
     *
     * @param request : manager 회원 가입 시 필요한 정보
     * @return 가입된 회원 정보 dto
     */
    @Override
    public ManagerSignUpDto.Response managerSignUp(ManagerSignUpDto.Request request) {

        if (managerRepository.existsByUsername(request.getUsername())) {
            throw new CustomException(USERNAME_ALREADY_USE);
        }

        return ManagerSignUpDto.Response.from(managerRepository.save(ManagerEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .userType(UserType.MANAGER)
                .build()));
    }

}
