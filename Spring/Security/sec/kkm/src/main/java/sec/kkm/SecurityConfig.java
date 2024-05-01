package sec.kkm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultHttpSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

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
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
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

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withUsername("user").password("{noop}1111").roles("USER").build();
        UserDetails manager = User.withUsername("manager").password("{noop}1111").roles("MANAGER").build();
        UserDetails admin = User.withUsername("admin").password("{noop}1111").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user,manager,admin);
       // return new CustomUserDetailService();
    }
}
