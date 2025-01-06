package focandlol.reservation.auth.service;

import focandlol.reservation.global.exception.CustomException;
import focandlol.reservation.auth.dto.CustomUserDetails;
import focandlol.reservation.auth.dto.UserDetailsDto;
import focandlol.reservation.manager.entity.ManagerEntity;
import focandlol.reservation.manager.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static focandlol.reservation.global.exception.ErrorCode.*;

/**
 * manager 인증 요청 시 사용되는 UserDetailService
 */
@Service
@RequiredArgsConstructor
public class ManagerUserDetailsService implements UserDetailsService {

    private final ManagerRepository managerRepository;

    /**
     * managerRepositoey에서 manager 정보 조회
     * 값이 없을 경우 exception
     * 값이 있을 경우 CustomUserDetails 만들어서 리턴
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<ManagerEntity> findManager = managerRepository.findByUsername(username);

        if(findManager.isPresent()) {
            ManagerEntity manager = findManager.get();
            return new CustomUserDetails(UserDetailsDto.from(manager));
        }

        throw new CustomException(MANAGER_NOT_FOUND);
    }

}
