package org.example.userauthenticationservice_sept2024;

public class UserAlreadyExistException extends Exception {
    String msg;
    public UserAlreadyExistException(String msg) {
        this.msg = msg;
    }
}
