package com.ecommercebackend.event.listener;

import com.ecommercebackend.event.NotifyEvent;
import org.springframework.context.ApplicationListener;

public class NotifyEventListener implements ApplicationListener<NotifyEvent> {
    @Override
    public void onApplicationEvent(NotifyEvent event) {
        try {
            Thread.sleep(10000);
            System.out.println("WingNotifyEventListener Work");
//            System.out.println(wingBotEvent.getSource());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
