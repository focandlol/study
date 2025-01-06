package focandlol.resproject.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import focandlol.resproject.auth.security.CustomerLoginFilter;
import focandlol.resproject.auth.security.JwtFilter;
import focandlol.resproject.auth.security.JwtUtil;
import focandlol.resproject.auth.security.ManagerLoginFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

/**
 * spring security 설정 클래스
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;
    private final UserDetailsService managerUserDetailsService;
    private final UserDetailsService customerUserDetailsService;
    private final AuthenticationEntryPoint entryPoint;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(auth -> auth.disable());
        http.formLogin(auth -> auth.disable());
        http.httpBasic(auth -> auth.disable());

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/login/**", "/", "/customer/signUp", "/manager/signUp").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated());

        // 인증 실패 시 CustomAuthenticationEntryPoint 사용
        http.exceptionHandling(handler -> handler.authenticationEntryPoint(entryPoint));

        // JWT 필터를 커스텀 로그인 필터 이전에 추가
        http.addFilterBefore(new JwtFilter(jwtUtil), ManagerLoginFilter.class);

        // 고객 로그인 필터를 UsernamePasswordAuthenticationFilter 위치에 추가
        http.addFilterAt(new CustomerLoginFilter(
                "/login/customer", // 고객 로그인 엔드포인트
                objectMapper, // JSON 매핑에 사용
                customerAuthenticationManager(), // 고객 인증 매니저
                jwtUtil // JWT 유틸리티
        ), UsernamePasswordAuthenticationFilter.class);

        // 매니저 로그인 필터를 UsernamePasswordAuthenticationFilter 위치에 추가
        http.addFilterAt(new ManagerLoginFilter(
                "/login/manager", // 매니저 로그인 엔드포인트
                objectMapper, // JSON 매핑에 사용
                managerAuthenticationManager(), // 매니저 인증 매니저
                jwtUtil // JWT 유틸리티
        ), UsernamePasswordAuthenticationFilter.class);

        // 세션 관리 설정: STATELESS로 설정하여 세션을 사용하지 않음 (JWT 기반 인증)
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build(); // SecurityFilterChain 빌드 후 반환
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 비밀번호 인코딩을 위한 BCryptPasswordEncoder 사용
        return new BCryptPasswordEncoder();
    }

    public AuthenticationManager managerAuthenticationManager() {
        // 매니저 인증을 처리하기 위한 AuthenticationManager 생성
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(managerUserDetailsService); // 매니저 UserDetailsService 설정
        provider.setPasswordEncoder(passwordEncoder()); // 비밀번호 인코더 설정
        return new ProviderManager(Collections.singletonList(provider));
    }

    public AuthenticationManager customerAuthenticationManager() {
        // 고객 인증을 처리하기 위한 AuthenticationManager 생성
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customerUserDetailsService); // 고객 UserDetailsService 설정
        provider.setPasswordEncoder(passwordEncoder()); // 비밀번호 인코더 설정
        return new ProviderManager(Collections.singletonList(provider));
    }
}
