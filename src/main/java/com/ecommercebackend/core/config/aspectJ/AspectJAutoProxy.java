package com.ecommercebackend.core.config.aspectJ;


import com.ecommercebackend.core.aspect.LoginsAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AspectJAutoProxy {


    @Bean
    public LoginsAspect wingBotAspect() {
        System.out.println("EnableAspectJAutoProxy Bean BotAspect");
        return new LoginsAspect();
    }

    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster() {
        SimpleApplicationEventMulticaster applicationEventMulticaster = new SimpleApplicationEventMulticaster();
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.initialize();
        applicationEventMulticaster.setTaskExecutor(threadPoolTaskExecutor);
        return applicationEventMulticaster;
    }
}
