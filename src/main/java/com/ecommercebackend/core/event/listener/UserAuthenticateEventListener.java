package com.ecommercebackend.core.event.listener;

import com.ecommercebackend.core.dao.SuccessLoginDao;
import com.ecommercebackend.core.event.UserAuthenticateEvent;
import com.ecommercebackend.core.model.map.ModelMap;
import com.ecommercebackend.core.util.SystemDateUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticateEventListener implements ApplicationListener<UserAuthenticateEvent> {
    private static final Logger log = LoggerFactory.getLogger(UserAuthenticateEventListener.class);

    @Autowired
    private SuccessLoginDao successLoginDao;

    @Override
    public void onApplicationEvent(UserAuthenticateEvent event) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ModelMap data = (ModelMap) event.getSource();
            ModelMap device = data.getModelMap("deviceInfo");
            String userName = data.getString("userName");
            String networkIp = data.getString("networkIP");

            if (userName != null) {
                String date = SystemDateUtil.getDateFormat("yyyy-MM-dd hh:mm:ss");
                ModelMap input = new ModelMap();
                input.setString("userAgent", device.getString("userAgent"));
                input.setString("browser", device.getString("browser"));
                input.setString("os", device.getString("os"));
                input.setString("device", device.getString("device"));
                input.setString("osVersion", device.getString("os_version"));
                input.setString("browserVersion", device.getString("browser_version"));
                input.setString("deviceType", device.getString("deviceType"));
                input.setString("orientation", device.getString("orientation"));
                input.setString("networkIP", networkIp);
                input.setString("userName", userName);
                input.setString("date", date);

                log.info("User Login Success Info "+objectMapper.writeValueAsString(input));
                successLoginDao.save(input);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
