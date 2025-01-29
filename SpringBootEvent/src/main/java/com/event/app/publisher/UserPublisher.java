package com.event.app.publisher;

import com.event.app.event.UserRegisteredEvent;
import com.event.app.event.UserValidatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public UserPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishUserRegisteredEvent(String username, String password, byte age){
        System.out.println("PUBLISH: Event has been published - UserRegisteredEvent");
        applicationEventPublisher.publishEvent(new UserRegisteredEvent(username, password, age));
    }

    public void publishUserValidatedEvent(String username, boolean isValid){
        System.out.println("PUBLISH: Event has been published - UserValidatedEvent");
        applicationEventPublisher.publishEvent(new UserValidatedEvent(username, isValid));
    }
}
