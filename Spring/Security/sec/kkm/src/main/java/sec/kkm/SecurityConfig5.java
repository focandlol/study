package sec.kkm;//package kkm.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//@Configuration
//public class SecurityConfig5 {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/csrf").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(Customizer.withDefaults())
//                //.csrf(csrf->csrf.ignoringRequestMatchers("/csrf"));
//;
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user = User.withUsername("user").password("{noop}1111").roles("USER").build();
//        return  new InMemoryUserDetailsManager(user);
//    }
//}
