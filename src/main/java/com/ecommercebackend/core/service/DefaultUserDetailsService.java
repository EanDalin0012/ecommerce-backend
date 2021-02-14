package com.ecommercebackend.core.service;

import com.ecommercebackend.core.dao.DefaultAuthenticationDao;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    private DefaultAuthenticationDao appUserRepository;

    public DefaultUserDetailsService(DefaultAuthenticationDao appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ModelMap input = new ModelMap();
        input.setString("username", username);

        ModelMap userInfo = appUserRepository.getUserByName(input);

        if (userInfo != null) {
            MultiModelMap _authorities = userInfo.getMultiModelMap("authorities");
            Collection<GrantedAuthority> authorities = new ArrayList<>();

            for (ModelMap _authority : _authorities.toListData()) {
                authorities.add(new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return _authority.getString("name");
                    }
                });
            }

            return new User("admin",
                    "",
                    authorities);
        }
        return null;
    }
}
