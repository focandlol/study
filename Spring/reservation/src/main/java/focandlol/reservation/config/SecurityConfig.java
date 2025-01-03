package focandlol.reservation.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import focandlol.reservation.jwt.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
                .requestMatchers("/login/**","/", "/signUp/**","/hello").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated());

        http.exceptionHandling(handler -> handler.authenticationEntryPoint(entryPoint));
        http.addFilterBefore(new JwtFilter(jwtUtil,objectMapper), ManagerLoginFilter.class);
        //http.addFilterAt(new LoginFilter(objectMapper,authenticationManager(configuration),jwtUtil), UsernamePasswordAuthenticationFilter.class);

        http.addFilterAt(new CustomerLoginFilter("/login/customer",objectMapper,customerAuthenticationManager(),jwtUtil), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(new ManagerLoginFilter("/login/manager",objectMapper,managerAuthenticationManager(),jwtUtil), UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();

    }

    public AuthenticationManager managerAuthenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(managerUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(Collections.singletonList(provider));
    }

    public AuthenticationManager customerAuthenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customerUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(Collections.singletonList(provider));
    }
}
