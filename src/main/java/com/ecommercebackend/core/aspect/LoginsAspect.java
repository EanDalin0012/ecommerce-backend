package com.ecommercebackend.core.aspect;

import com.ecommercebackend.event.NotifyEvent;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import javax.inject.Inject;

@Aspect
@Component
public class LoginsAspect {

    @Inject
    private ApplicationEventPublisher eventPublisher;

    @Pointcut("execution(* com.ecommercebackend.core.provider.DefaultAuthenticationProvider.authenticate(..)))" )
    private void execute(){
        System.out.println("BotAspect Component execute");
    }

    @Before("execute()")
    public void beforeExecute() throws InterruptedException {
        System.out.println("BotAspect Component beforeExecute");
        eventPublisher.publishEvent(new NotifyEvent( "Testing"));
    }

    @AfterReturning(pointcut = "execute()", argNames = "request, response", returning = "response")
    public void afterExecute(JoinPoint request, Object response) {
        System.out.println("BotAspect Component afterExecute");
//        eventPublisher.publishEvent(new WingBotEvent(getRequest(request, response)));
    }
}
