package com.event.app.listener;

import com.event.app.event.UserRegisteredEvent;
import com.event.app.event.UserValidatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserListener {

    @EventListener
    public UserValidatedEvent listenUserRegisteredEvent(UserRegisteredEvent userRegisteredEvent){
        System.out.println("LISTENING: UserRegisteredEvent Event has been listened ->".concat(userRegisteredEvent.toString()));
        return new UserValidatedEvent(userRegisteredEvent.getUsername(), true);
    }

    @EventListener
    public void listenUserValidatedEvent(UserValidatedEvent userValidatedEvent){
        System.out.println("LISTENING: UserValidatedEvent Event has been listened ->".concat(userValidatedEvent.toString()));
    }
}
