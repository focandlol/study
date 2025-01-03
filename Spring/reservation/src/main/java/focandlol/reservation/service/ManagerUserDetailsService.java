package focandlol.reservation.service;

import focandlol.reservation.exception.CustomException;
import focandlol.reservation.dto.CustomUserDetails;
import focandlol.reservation.dto.UserDetailsDto;
import focandlol.reservation.entity.auth.ManagerEntity;
import focandlol.reservation.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static focandlol.reservation.exception.ErrorCode.*;

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
