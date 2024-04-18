package kkm.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
//                .formLogin(form -> form//.loginPage("/loginPage")
//                        .loginProcessingUrl("/loginProc")
//                        //.defaultSuccessUrl("/",false)
//                        .failureUrl("/failed")
//                        .usernameParameter("userId")
//                        .passwordParameter("passwd")
//                        /*.successHandler(new AuthenticationSuccessHandler() {
//                            @Override
//                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                                System.out.println("authentication : " + authentication);
//                                response.sendRedirect("/?con");
//                            }
//                        })*/
//                        .failureHandler((request, response, exception) -> {
//                            System.out.println("exception : "+ exception.getMessage());
//                            response.sendRedirect("login");
//                        })
//                        .permitAll()
//                );

        /*http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .httpBasic(basic -> basic.authenticationEntryPoint(new CustomAuthEntryPoint()));*/

       /* http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .rememberMe(rememberMe -> rememberMe
                        //.alwaysRemember(true)
                        .tokenValiditySeconds(3600)
                        .userDetailsService(userDetailsService())
                        .rememberMeParameter("remember123")
                        .rememberMeCookieName("remember")
                        .key("security")
                );*/

//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/anonymous").hasRole("GUEST")
//                        .requestMatchers("/anonymousContext","/authentication").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .anonymous(anonymous -> anonymous
//                        .principal("guest")
//                        .authorities("ROLE_GUEST")
//                );
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/logoutSuccess").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                //.csrf(csrf -> csrf.disable())
//                .logout(logout -> logout.logoutUrl("/logout")
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
//                        .logoutSuccessUrl("/logoutSuccess")
//                        .logoutSuccessHandler(((request, response, authentication) -> {
//                            response.sendRedirect("/logoutSuccess");
//                        }))
//                        .deleteCookies("JSESSIONID","remember-me")
//                        .invalidateHttpSession(true)
//                        .clearAuthentication(true)
//                        .addLogoutHandler(((request, response, authentication) -> {
//                            HttpSession session = request.getSession();
//                            session.invalidate();
//                            SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(null);
//                            SecurityContextHolder.getContextHolderStrategy().clearContext();
//                        }))
//                        .permitAll()
//                );


//        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
//        requestCache.setMatchingRequestParameterName("customParam=y");
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/logoutSuccess").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(form -> form
//                        .successHandler(((request, response, authentication) -> {
//                            SavedRequest savedRequest = requestCache.getRequest(request, response);
//                            String redirectUrl = savedRequest.getRedirectUrl();
//                            response.sendRedirect(redirectUrl);
//                        }))
//
//                )
//                .requestCache(cache -> cache.requestCache(requestCache));


//        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
      //AuthenticationManager authenticationManager = builder.build();
//        AuthenticationManager authenticationManager1 = builder.getObject();
//
//
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/","api/login").permitAll()
//                       .anyRequest().authenticated())
//               .formLogin(Customizer.withDefaults()
//               )
//                .authenticationManager(authenticationManager)
//                .addFilterBefore(customAuthenticationFilter(http,authenticationManager)
//                , UsernamePasswordAuthenticationFilter.class);

//        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        builder.authenticationProvider(new CustomAuthenticationProvider());
//        builder.authenticationProvider(new CustomAuthenticationProvider2());
//
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/","api/login").permitAll()
//                       .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                ;

//        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        AuthenticationManager authenticationManager = builder.build();
//
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/login").permitAll()
//                       .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                //.securityContext(securityContext -> securityContext.requireExplicitSave(false))
//                .authenticationManager(authenticationManager)
//                .addFilterBefore(customAuthenticationFilter(http,authenticationManager),
//                        UsernamePasswordAuthenticationFilter.class)
//        ;

        /**
         * 1.sessionManagement().maximumSessions
         * 2.sessionManagement().sessionFixation
         * 3.sessionManagement().sessionCreationPolicy
         */
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/invalidSessionUrl","/expiredSessionUrl").permitAll()
//                       .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .sessionManagement(session -> session
//                        .invalidSessionUrl("/invalidSessionUrl")
//                        .maximumSessions(1)
//                        .maxSessionsPreventsLogin(false)
//                        .expiredUrl("/expiredUrl")
//                ).sessionManagement(session -> session
//                        .sessionFixation(sessionFixation -> sessionFixation.none()))
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
//        ;

        /**
         * 1.SessionManagementFilter/ConcurrentSessionFilter maxSessionsPriventsLogin(false)
         * 2.SessionManagementFilter/ConcurrentSessionFilter maxSessionsPriventsLogin(true)
         */
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/invalidSessionUrl","/expiredSessionUrl").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .sessionManagement(session -> session
//                        .maximumSessions(2)
//                        .maxSessionsPreventsLogin(false));


        /**
         * exceptionHandling()
         */
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new AuthenticationEntryPoint() {
                            @Override
                            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                                System.out.println("exception" + authException.getMessage());
                                response.sendRedirect("/login");
                            }
                        })
                        .accessDeniedHandler(((request, response, accessDeniedException) -> {
                            System.out.println("accessDeniedException.getMessage() = " + accessDeniedException.getMessage());
                            response.sendRedirect("/denied");
                        }))
                )
                ;
        return http.build();
    }

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

//    public CustomAuthenticationFilter customAuthenticationFilter(HttpSecurity http, AuthenticationManager authenticationManager){
//        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(http);
//        customAuthenticationFilter.setAuthenticationManager(authenticationManager);
//        return customAuthenticationFilter;
//    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails user = User.withUsername("kkm")
//                .password("{noop}2222")
//                .roles("USER").build();
//
//        return new InMemoryUserDetailsManager(user);
//    }


    public CustomAuthenticationFilter customAuthenticationFilter(HttpSecurity http, AuthenticationManager authenticationManager){
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(http);
        customAuthenticationFilter.setAuthenticationManager(authenticationManager);
        return customAuthenticationFilter;
    }
    @Bean
    public UserDetailsService userDetailsService(){

//        UserDetails user = User.withUsername("kkm")
//                .password("{noop}2222")
//                .roles("USER").build();
//
//        return new InMemoryUserDetailsManager(user);

        return new CustomUserDetailService();
    }
}
