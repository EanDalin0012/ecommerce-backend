package com.ecommercebackend.core.provider;

import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.model.map.MultiModelMap;
import com.ecommercebackend.core.service.implement.DefaultAuthenticationServiceImplement;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class DefaultAuthenticationProvider implements AuthenticationProvider {
    private static final Logger log = LoggerFactory.getLogger(DefaultAuthenticationProvider.class);

    @Autowired
    private DefaultAuthenticationServiceImplement userService;

    public DefaultAuthenticationProvider(DefaultAuthenticationServiceImplement userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("<<<<<==============Start Authorization ===============>>>>>>>>>>>>\n");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            log.info("<<<<<==============Start Authorization"+objectMapper.writeValueAsString(authentication));
            ModelMap input = new ModelMap();
            input.setString("user_name", authentication.getName());
            ModelMap userInfo = userService.getUserByName(input);
            if (userInfo == null) {
                log.info("<<<<<==============Authorization user not found===============>>>>>>>>>>>>");
                throw new UsernameNotFoundException("User Not found");
            }

            if (userInfo.getBoolean("accountLocked")) {
                log.info("<<<<<==============user account is locked===============>>>>>>>>>>>>");
                throw new UsernameNotFoundException("Your user account locked");
            }

            if (!userInfo.getBoolean("enabled")) {
                log.info("<<<<<==============user enabled false===============>>>>>>>>>>>>");
                throw new UsernameNotFoundException("Your user account enabled");
            }

            if (userInfo.getBoolean("accountExpired")) {
                log.info("<<<<<==============user account expired===============>>>>>>>>>>>>");
                throw new UsernameNotFoundException("Your user account account expired");
            }

            if (userInfo.getBoolean("credentialsExpired")) {
                log.info("<<<<<==============user account credentials expired ===============>>>>>>>>>>>>");
                throw new UsernameNotFoundException("Your user account credentials expired");
            }

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String _password = userInfo.getString("password");
            String password = (String) authentication.getCredentials();
            boolean isPasswordMatch = passwordEncoder.matches(password, _password);
            System.out.println(isPasswordMatch);
            if (!isPasswordMatch) {
//                int count = trackLockUser(authentication.getName(), "User input wrong password");
//                if (count >= 5) {
//                    throw new UsernameNotFoundException("Your account is locked. Please contact to admin for unlocked you account");
//                }
                throw new UsernameNotFoundException("Password invalid " + 1 + " time");
            }

            List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
            MultiModelMap authorities = userInfo.getMultiModelMap("authorities");
            for (ModelMap authority : authorities.toListData()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(authority.getString("name")));
            }

            ModelMap param = new ModelMap();
            param.setString("user_name", authentication.getName());
//            userService.updateLoginSuccess(param);
//            isLoginSuccess(param);
            log.info("\n<<<<<==============End Authorization ===============>>>>>>>>>>>>\n");
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(
                    userInfo.getString("username"),
                    userInfo.getString("password"),
                    grantedAuthorities);
//

            return usernamePasswordAuthenticationToken;

        } catch (UsernameNotFoundException ex) {
            log.info("\n<<<<<============== get error user name not found exception ===============>>>>>>>>>>>>\n" + ex);
            throw ex;
        } catch (Exception e) {
            log.error("\n ==>> ***get error class default authentication exception ***<<==\n", e);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
