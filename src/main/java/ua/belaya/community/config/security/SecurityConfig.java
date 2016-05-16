package ua.belaya.community.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Anna Belaya
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/resources/**").permitAll();

        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/web/**").hasAnyAuthority("USER", "ADMIN")
                //.antMatchers("/web/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin().loginPage("/auth/login")
                .defaultSuccessUrl("/web/students")
                .permitAll()
            .and()
                .logout().logoutSuccessUrl("/").permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(customUserDetailService);
    }
}
