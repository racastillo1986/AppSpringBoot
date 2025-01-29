package com.event.app.event;

import lombok.Getter;

public class UserValidatedEvent {

    @Getter
    private String username;
    private boolean isValid;

    public UserValidatedEvent(String username, boolean isValid) {
        this.username = username;
        this.isValid = isValid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    @Override
    public String toString() {
        return "UserValidatedEvent{" +
                "username='" + username + '\'' +
                ", isValid=" + isValid +
                '}';
    }
}
