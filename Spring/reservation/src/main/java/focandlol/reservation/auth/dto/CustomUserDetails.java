package focandlol.reservation.auth.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 사용자 인증과 권한 관리를 위한 사용자 세부 정보 클래스
 */
public class CustomUserDetails implements UserDetails {

    private final UserDetailsDto userDetailsDto;

    /**
     * 유저 정보 dto를 생성자로 주입
     * @param userDetailsdto : 관리할 유저 정보
     */
    public CustomUserDetails(UserDetailsDto userDetailsdto) {
        this.userDetailsDto = userDetailsdto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetailsDto.getRoles().stream()
                .map(a -> new SimpleGrantedAuthority(a))
                .collect(Collectors.toList());
    }

    public Long getId(){
        return userDetailsDto.getId();
    }

    @Override
    public String getPassword() {
        return userDetailsDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userDetailsDto.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserDetailsDto getUserDetailsDto() {
        return userDetailsDto;
    }

    public void setPassword(String password) {
        userDetailsDto.setPassword(password);
    }
}
