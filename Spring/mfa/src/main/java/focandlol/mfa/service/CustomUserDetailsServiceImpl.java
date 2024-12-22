package focandlol.mfa.service;

import focandlol.mfa.datas.dto.CustomUserDetails;
import focandlol.mfa.datas.entity.UserEntity;
import focandlol.mfa.exception.OtpNotApproveException;
import focandlol.mfa.util.OTPUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserService userService;
    private final MfaService mfaService;
    private String otp;
    @Override
    public UserDetails loadUserByUsername(String username, String otp) throws UsernameNotFoundException, OtpNotApproveException {
        this.otp = otp;
        if(otp != null){
            String secretKey = mfaService.getMfaSecretKey(username).getSecretKey();
            if(!OTPUtil.checkCode(otp, secretKey)){
                throw new OtpNotApproveException("otp number didn't approve");
            }
        }
        return loadUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userService.getUser(username);

        UserEntity user = userEntity.orElseThrow(() -> new UsernameNotFoundException("user not exist"));

        return CustomUserDetails.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true).build();


    }
}
