package focandlol.mfa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers("/i18n/**")
                .mvcMatchers("/resources/**");
    }

    private String permitallUrl = "/login,/,/prelogin,/mfator,/purelogin";

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();

        http.headers().frameOptions().sameOrigin()
                .and().authorizeRequests().antMatchers(permitallUrl.split(",")).permitAll()
                .and().formLogin().loginPage("/login")
                .and().logout().logoutUrl("/logout").invalidateHttpSession(false).permitAll()
                .and().authorizeRequests().anyRequest().authenticated();
    }
}
