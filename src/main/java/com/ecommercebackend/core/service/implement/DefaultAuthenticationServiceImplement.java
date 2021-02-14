package com.ecommercebackend.core.service.implement;

import com.ecommercebackend.core.dao.DefaultAuthenticationDao;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.service.DefaultAuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DefaultAuthenticationServiceImplement implements UserDetailsService,DefaultAuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(DefaultAuthenticationServiceImplement.class);

    @Autowired
    private DefaultAuthenticationDao defaultAuthenticationProviderDao;

    @Override
    public ModelMap getUserByName(ModelMap param) throws Exception {
        return defaultAuthenticationProviderDao.getUserByName(param);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            ModelMap input = new ModelMap();
            input.setString("user_name", username);
            ModelMap userInfo = defaultAuthenticationProviderDao.getUserByName(input);

            String userName = userInfo.getString("userName");
            String password = userInfo.getString("password");
            Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) userInfo.getModelMap("authorities");
            if (userInfo != null) {
                UserDetails userDetails = User.builder()
                        .username(userName)
                        .password(password)
                        .authorities(authorities)
                        .build();
                return userDetails;
            }
            throw new UsernameNotFoundException("User not found_0");

        } catch (Exception e) {
            log.error("get error exception service loadUserByUsername:", e);
            throw e;
        }
    }
}
