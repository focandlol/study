package focandlol.reservation.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final UserDetailsDto userDetailsDto;

    public CustomUserDetails(UserDetailsDto userDetailsdto) {
        this.userDetailsDto = userDetailsdto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return List.of(new SimpleGrantedAuthority(userDetailsDto.getRole()));
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
