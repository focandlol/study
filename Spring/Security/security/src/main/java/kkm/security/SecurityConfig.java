package kkm.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
//                .formLogin(form -> form//.loginPage("/loginPage")
//                        .loginProcessingUrl("/loginProc")
//                        .defaultSuccessUrl("/",false)
//                        .failureUrl("/failed")
//                        .usernameParameter("userId")
//                        .passwordParameter("passwd")
//                        .successHandler(new AuthenticationSuccessHandler() {
//                            @Override
//                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                                System.out.println("authentication : " + authentication);
//                                response.sendRedirect("/home");
//                            }
//                        })
//                        .failureHandler((request, response, exception) -> {
//                            System.out.println("exception : "+ exception.getMessage());
//                            response.sendRedirect("login");
//                        })
//                        .permitAll()
//                );

        http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .httpBasic(basic -> basic.authenticationEntryPoint(new CustomAuthEntryPoint()));

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user = User.withUsername("user")
                .password("{noop}2222")
                .roles("USER").build();

        return new InMemoryUserDetailsManager(user);
    }
}
