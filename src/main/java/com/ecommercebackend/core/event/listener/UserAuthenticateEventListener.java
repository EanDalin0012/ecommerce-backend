package com.ecommercebackend.core.event.listener;

import com.ecommercebackend.core.event.UserAuthenticateEvent;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticateEventListener implements ApplicationListener<UserAuthenticateEvent> {
    private static final Logger log = LoggerFactory.getLogger(UserAuthenticateEventListener.class);

    @Override
    public void onApplicationEvent(UserAuthenticateEvent event) {
        try {
            Thread.sleep(100);
            ObjectMapper objectMapper = new ObjectMapper();
            log.info("User Authenticate Event Listener "+objectMapper.writeValueAsString(event));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
