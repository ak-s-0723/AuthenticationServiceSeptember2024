package org.example.userauthenticationservice_sept2024.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public String toString() {
        return this.getMessage();
    }
}
