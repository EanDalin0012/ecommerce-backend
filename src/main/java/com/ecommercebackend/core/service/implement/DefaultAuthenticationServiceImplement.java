package com.ecommercebackend.core.service.implement;

import com.ecommercebackend.core.dao.DefaultAuthenticationDao;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;
import com.ecommercebackend.core.model.security.Authority;
import com.ecommercebackend.core.service.DefaultAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

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
        log.info("===========================");
        try {
            ModelMap input = new ModelMap();
            input.setString("user_name", username);
            ModelMap userInfo = defaultAuthenticationProviderDao.getUserByName(input);
//            com.ecommercebackend.core.model.security.User user1 = defaultAuthenticationProviderDao.getUserByName1(input);
            ObjectMapper objectMapper = new ObjectMapper();
//            log.info("user==="+objectMapper.writeValueAsString(user1));
//            if (user1 != null ) {
//                return  user1;
//            }
            log.info("===========================");
            String userName = userInfo.getString("userName");
            String password = userInfo.getString("password");
            ArrayList<LinkedHashMap<String, Object>> arr = new ArrayList<>();
            MultiModelMap authList = userInfo.getMultiModelMap("authorities");

            Collection<Authority> authorities = new ArrayList<>(); //userInfo.getModelMap("authorities");

            for (ModelMap data: authList.toListData()) {
                Authority authority = new Authority();
                authority.setId(data.getInt("id"));
                authority.setName(data.getString("name"));
                authorities.add(authority);
            }

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
            e.printStackTrace();
        }
        return null;
    }
}
