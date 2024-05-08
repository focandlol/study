package sec.kkm;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultHttpSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcherEntry;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import sec.kkm.event.CustomAuthenticationProvider;
import sec.kkm.event.CustomAuthenticationProvider2;
import sec.kkm.event.CustomAuthenticationSuccessEvent;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final ApplicationEventPublisher eventPublisher;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, ApplicationContext context) throws Exception {

        //CookieCsrfTokenRepository csrfTokenRepository = new CookieCsrfTokenRepository();
        //XorCsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new XorCsrfTokenRequestAttributeHandler();

//        http.authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/csrf","/csrfToken").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
                //.csrf(csrf->csrf.csrfTokenRepository(csrfTokenRepository.withHttpOnlyFalse())
                       // .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler))
                ;

        /**
         * thymeleaf csrf
         */
//        http.authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/csrf","/csrfToken","/form","/formCsrf").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .csrf(Customizer.withDefaults())
//                ;

        /**
         * javascript csrf cookie
         */
//        SpaCsrfTokenRequestHandler csrfTokenRequestHandler = new SpaCsrfTokenRequestHandler();
//
//        http.authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/csrf","/csrfToken","/cookie","/cookieCsrf").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                        .csrfTokenRequestHandler(csrfTokenRequestHandler)
//                )
//               // .addFilterBefore(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
//        ;

        /**
         * authorize
         */
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/user").hasRole("USER")
//                        .requestMatchers("/db").hasRole("DB")
//                        .requestMatchers("/admin").hasRole("ADMIN")
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable);

        /**
         * custom access authorizationManager
         */
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/user/{name}")
//                        .access(new WebExpressionAuthorizationManager("#name == authentication.name"))
//                        .requestMatchers("/admin/db")
//                        .access(new WebExpressionAuthorizationManager("hasAuthority('ROLE_DB') or hasAuthority('ROLE_ADMIN')"))
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults());


        /**
         * custom authroizationManager
         */
//        DefaultHttpSecurityExpressionHandler expressionHandler = new DefaultHttpSecurityExpressionHandler();
//        expressionHandler.setApplicationContext(context);
//
//        WebExpressionAuthorizationManager authorizationManager
//                = new WebExpressionAuthorizationManager("@customWebSecurity.check(authentication,request)");
//        authorizationManager.setExpressionHandler(expressionHandler);
//
//
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/custom").access(authorizationManager)
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults());

        /**
         * custom requestMatcher
         */
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(new CustomRequestMatcher("/admin")).hasAuthority("ROLE_ADMIN")
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults());

        /**
         * securityMatcher
         */
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults());

        /**
         * Method authorization
         * 요청 권한 먼저 다음에 메서드 권한 isAutheication
         * @PreAuthorize
         * @PostAuthorize
         */
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults());

        /**
         * Method authorization
         * @PreFilter
         * @PostFilter
         */
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable);

        /**
         * Method authorization
         * @Secured
         * JSR-250
         */
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable);

        /**
         * static resource v1
         * @bean webSecurityCustomizer
         * ignoring()
         */
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable);

        /**
         * static resource v2
         * permitAll()
         */
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/images/**").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable);

        /**
         * RoleHierarchy
         */
//        http
//                .authorizeHttpRequests(authorize -> authorize
////                        .requestMatchers("/user").hasRole("USER")
////                        .requestMatchers("/admin").hasRole("ADMIN")
////                        .requestMatchers("/db").hasRole("DB")
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable);

        /**
         * authorization
         */
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/user").hasRole("USER")
//                        .requestMatchers("/admin").hasRole("ADMIN")
//                        .requestMatchers("/db").hasRole("DB")
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable);

        /**
         * authorization class test
         */
//        http
//        .authorizeHttpRequests(authorize -> authorize
//                .requestMatchers("/user").hasRole("USER")
//                .requestMatchers("/admin").hasRole("ADMIN")
//                .requestMatchers("/db").access(new WebExpressionAuthorizationManager("hasRole('DB')"))
//                .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable);

        /**
         * Custom AuthorizationManager
         */
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/user").hasRole("USER")
//                        .requestMatchers("/admin").hasRole("ADMIN")
//                        .requestMatchers("/db").access(new WebExpressionAuthorizationManager("hasRole('DB')"))
//                        .requestMatchers("/ssecure").access(new CustomAuthorizationManager())
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable);

        /**
         * programming authorization
         * CustomRequestMatcherDelegatingAuthorizationManager
         */
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().access(authorizationManager(null)))
//                .formLogin(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable);

        /**
         * method authorization
         * PreAuthorizationManager
         */
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable);

        /**
         * authentication events
         */
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated())
                .formLogin(form -> form.successHandler(((request, response, authentication) -> {
                    eventPublisher.publishEvent(new CustomAuthenticationSuccessEvent(authentication));
                })))
                .csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(customAuthenticationProvider2());
        return http.build();
    }

    /**
     * securityMatcher filterChain
     */
//    @Bean
//    @Order(1)
//    public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
//        http
//                .securityMatchers(matchers -> matchers.requestMatchers("/api/**","/oauth/**"))
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().permitAll())
//                ;
//        return http.build();
//    }

    /**
     * static resource v1
     * use @bean ignoring
     */
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }

    /**
     * RoleHierarchy
     */
//    @Bean
//    public RoleHierarchy roleHierarchy(){
//        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
////        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_DB\n" +
////                "ROLE_DB > ROLE_USER\n" +
////                "ROLE_USER > ROLE_ANONYMOUS");
//        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_DB > ROLE_USER");
//        return roleHierarchy;
//    }

    /**
     * change ROLE_ -> KKM_
     */
//    @Bean
//    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
//        return new GrantedAuthorityDefaults("KKM_");
//    }

    /**
     * programming authorization
     * CustomRequestMatcherDelegatingAuthorizationManager
     * Add EntryPoint
     */
//    @Bean
//    public AuthorizationManager<RequestAuthorizationContext> authorizationManager(HandlerMappingIntrospector introspector){
//        List<RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>>> mappings = new ArrayList<>();
//
//        RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>> requestMatcherEntry1 =
//                new RequestMatcherEntry<>(new MvcRequestMatcher(introspector, "/user"),
//                        AuthorityAuthorizationManager.hasAuthority("ROLE_USER"));
//
//        RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>> requestMatcherEntry2 =
//                new RequestMatcherEntry<>(new MvcRequestMatcher(introspector, "/db"),
//                        AuthorityAuthorizationManager.hasAuthority("ROLE_DB"));
//
//        RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>> requestMatcherEntry3 =
//                new RequestMatcherEntry<>(new MvcRequestMatcher(introspector, "/admin"),
//                        AuthorityAuthorizationManager.hasRole("ADMIN"));
//
//        RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>> requestMatcherEntry4 =
//                new RequestMatcherEntry<>(AnyRequestMatcher.INSTANCE, new AuthenticatedAuthorizationManager<>());
//
//        mappings.add(requestMatcherEntry1);
//        mappings.add(requestMatcherEntry2);
//        mappings.add(requestMatcherEntry3);
//        mappings.add(requestMatcherEntry4);
//
//        return new CustomRequestMatcherDelegatingAuthorizationManager(mappings);
//    }

    /**
     * CustomAuthenticationProvider2
     * use DefaultAuthenticationEventPublisher
     * @return
     */
    @Bean
    public CustomAuthenticationProvider2 customAuthenticationProvider2(){
        return new CustomAuthenticationProvider2(authenticationEventPublisher(null));
    }
    @Bean
    public DefaultAuthenticationEventPublisher authenticationEventPublisher(ApplicationEventPublisher applicationEventPublisher){
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }
    @Bean
    public UserDetailsService userDetailsService(){

        /**
         * change GrantedAuthorityDefaults
         */
        //UserDetails user = User.withUsername("user").password("{noop}1111").authorities("KKM_USER").build();

        UserDetails user = User.withUsername("user").password("{noop}1111").roles("USER").build();
        UserDetails manager = User.withUsername("db").password("{noop}1111").roles("DB").build();
        UserDetails admin = User.withUsername("admin").password("{noop}1111").roles("ADMIN","SECURE").build();
        return new InMemoryUserDetailsManager(user,manager,admin);
       // return new CustomUserDetailService();
    }
}
