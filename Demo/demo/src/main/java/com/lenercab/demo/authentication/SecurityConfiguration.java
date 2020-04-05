package com.lenercab.demo.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Lener")
                .password("123456")
                .roles("ADMIN")
                .and()
                .withUser("User")
                .password("123456")
                .roles("USER");
    }

    @Bean
    public PasswordEncoder  getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/Admin").hasRole("ADMIN")
//                .antMatchers("/User").hasRole("USER")
//                .and().formLogin()
//                .loginProcessingUrl("/perform_login");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers(HttpMethod.GET, "/Demo/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/Demo/**").hasRole("ADMIN")
                .and().httpBasic();

        http.csrf().disable();
        http.headers().frameOptions().disable();

    }
}
