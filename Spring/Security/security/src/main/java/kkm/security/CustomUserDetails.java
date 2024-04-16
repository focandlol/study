package kkm.security;

import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class CustomUserDetails implements UserDetails {

    private final AccountDto accountDto;

    public CustomUserDetails(AccountDto accountDto) {
        this.accountDto = accountDto;
    }

    public AccountDto getAccountDto() {
        return accountDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return accountDto.getAuthorities();
    }

    @Override
    public String getPassword() {
        return accountDto.getPassword();
    }

    @Override
    public String getUsername() {
        return accountDto.getUsername();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomUserDetails that = (CustomUserDetails) o;
        return Objects.equals(getAccountDto(), that.getAccountDto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountDto());
    }
}