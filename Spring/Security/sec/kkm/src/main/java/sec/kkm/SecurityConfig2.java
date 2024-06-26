package sec.kkm;//package kkm.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AnonymousAuthenticationProvider;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.util.List;
//
//@EnableWebSecurity
//@Configuration
//public class SecurityConfig2 {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http,
//                                                   AuthenticationManagerBuilder builder,
//                                                   AuthenticationConfiguration configuration) throws Exception {
//
////        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
////                .formLogin(form -> form//.loginPage("/loginPage")
////                        .loginProcessingUrl("/loginProc")
////                        //.defaultSuccessUrl("/",false)
////                        .failureUrl("/failed")
////                        .usernameParameter("userId")
////                        .passwordParameter("passwd")
////                        /*.successHandler(new AuthenticationSuccessHandler() {
////                            @Override
////                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
////                                System.out.println("authentication : " + authentication);
////                                response.sendRedirect("/?con");
////                            }
////                        })*/
////                        .failureHandler((request, response, exception) -> {
////                            System.out.println("exception : "+ exception.getMessage());
////                            response.sendRedirect("login");
////                        })
////                        .permitAll()
////                );
//
//        /*http
//                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
//                .httpBasic(basic -> basic.authenticationEntryPoint(new CustomAuthEntryPoint()));*/
//
//       /* http
//                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .rememberMe(rememberMe -> rememberMe
//                        //.alwaysRemember(true)
//                        .tokenValiditySeconds(3600)
//                        .userDetailsService(userDetailsService())
//                        .rememberMeParameter("remember123")
//                        .rememberMeCookieName("remember")
//                        .key("security")
//                );*/
//
////        http
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/anonymous").hasRole("GUEST")
////                        .requestMatchers("/anonymousContext","/authentication").permitAll()
////                        .anyRequest().authenticated())
////                .formLogin(Customizer.withDefaults())
////                .anonymous(anonymous -> anonymous
////                        .principal("guest")
////                        .authorities("ROLE_GUEST")
////                );
////        http
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/logoutSuccess").permitAll()
////                        .anyRequest().authenticated())
////                .formLogin(Customizer.withDefaults())
////                //.csrf(csrf -> csrf.disable())
////                .logout(logout -> logout.logoutUrl("/logout")
////                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
////                        .logoutSuccessUrl("/logoutSuccess")
////                        .logoutSuccessHandler(((request, response, authentication) -> {
////                            response.sendRedirect("/logoutSuccess");
////                        }))
////                        .deleteCookies("JSESSIONID","remember-me")
////                        .invalidateHttpSession(true)
////                        .clearAuthentication(true)
////                        .addLogoutHandler(((request, response, authentication) -> {
////                            HttpSession session = request.getSession();
////                            session.invalidate();
////                            SecurityContextHolder.getContextHolderStrategy().getContext().setAuthentication(null);
////                            SecurityContextHolder.getContextHolderStrategy().clearContext();
////                        }))
////                        .permitAll()
////                );
//
//
////        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
////        requestCache.setMatchingRequestParameterName("customParam=y");
////        http
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/logoutSuccess").permitAll()
////                        .anyRequest().authenticated())
////                .formLogin(form -> form
////                        .successHandler(((request, response, authentication) -> {
////                            SavedRequest savedRequest = requestCache.getRequest(request, response);
////                            String redirectUrl = savedRequest.getRedirectUrl();
////                            response.sendRedirect(redirectUrl);
////                        }))
////
////                )
////                .requestCache(cache -> cache.requestCache(requestCache));
//
//
////        http
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/","api/login").permitAll()
////                        .anyRequest().authenticated())
////                .formLogin(Customizer.withDefaults()
////                )
////                .addFilterBefore(customAuthenticationFilter(http)
////                        , UsernamePasswordAuthenticationFilter.class);
//
//        AuthenticationManagerBuilder managerBuilder= http.getSharedObject(AuthenticationManagerBuilder.class);
//        managerBuilder.authenticationProvider(CustomAuthenticationProvider());
//
//        ProviderManager providerManager = (ProviderManager)configuration.getAuthenticationManager();
//        providerManager.getProviders().remove(0);
//
//        builder.authenticationProvider(new DaoAuthenticationProvider());
//
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/","api/login").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults());
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationProvider CustomAuthenticationProvider(){
//        return new CustomAuthenticationProvider();
//    }
//
//
////    public CustomAuthenticationFilter customAuthenticationFilter(HttpSecurity http){
////        List<AuthenticationProvider> list1 = List.of(new DaoAuthenticationProvider());
////        ProviderManager parent = new ProviderManager(list1);
////        List<AuthenticationProvider> list2 = List.of(new AnonymousAuthenticationProvider("key"),
////                new CustomAuthenticationProvider());
////        ProviderManager providerManager = new ProviderManager(list2,parent);
////
////        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(http);
////        customAuthenticationFilter.setAuthenticationManager(providerManager);
////        return customAuthenticationFilter;
////    }
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails user = User.withUsername("kkm")
//                .password("{noop}2222")
//                .roles("USER").build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
//}
