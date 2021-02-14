package com.ecommercebackend.core.config.oauth2;

import com.ecommercebackend.core.encryption.Encoders;
import com.ecommercebackend.core.provider.DefaultAuthenticationProvider;
import com.ecommercebackend.core.service.implement.DefaultAuthenticationServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER)
@Import(Encoders.class)
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private DefaultAuthenticationServiceImplement appUserRepository;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new DefaultAuthenticationProvider(appUserRepository));
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                .ignoring()
                .antMatchers(
                        "/api/web/**"
                        , "/api/mobile/**"
                        , "/401.html"
                        , "/404.html"
                        , "/500.html");
    }
}
