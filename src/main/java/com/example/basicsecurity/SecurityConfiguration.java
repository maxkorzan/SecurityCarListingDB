package com.example.basicsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.net.Authenticator;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private SSUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUserDetailsService(userRepository);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                //4.03 USING ROLES FOR PAGE PERMISSIONS
                .antMatchers( "/", "/h2-console/**", "/style.css", "/images/**",
                        "/detail/**", "/category/**", "/register").permitAll() //.access("hasAnyAuthority('USER','ADMIN')")   //4.04 ADDING H2 DATABASE

                .antMatchers("/admin").access("hasAuthority('ADMIN')")

                .anyRequest().authenticated()
                .and().formLogin()/*remove this for default login template >> */.loginPage("/login")/* << */.permitAll() //4.02 ADD CUSTOM LOGIN PAGE USING: .loginPage("/login").permitAll();

                //4.03 (CONTINUED)
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll().permitAll()
                .and()
                .httpBasic();
                http.csrf().disable();
                http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("password")).authorities("USER")

                //4.03 USING ROLES FOR PAGE PERMISSIONS
//                .and().withUser("dave").password(passwordEncoder().encode("begreat")).authorities("ADMIN");

        //4.04 DATABASE AUTHENTICATION
        auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(passwordEncoder());
    }

}
