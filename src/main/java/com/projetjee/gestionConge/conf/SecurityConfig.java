package com.projetjee.gestionConge.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }

/*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder.encode("password"))
                .roles("STANDARD")
                .and()
                .withUser("manager")
                .password(passwordEncoder.encode("manager"))
                .roles("CHEFPROJET")
                .and()
                .withUser("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("RESPONSABLERH");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.formLogin().loginPage("/login").permitAll();
        http
                .authorizeRequests()
                .antMatchers("/RH**/**", "/salarie**/**", "/groupe**/**").hasAuthority("RESPONSABLERH")
                .antMatchers("/salarie/groupe/**").hasAnyAuthority("CHEFPROJET", "RESPONSABLERH")
                .antMatchers("/salarie/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll();
    }
}
