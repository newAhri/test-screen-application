package com.example.test_window_application.login;

public class LoginHeaders {
    private String username,
    password;

    public LoginHeaders(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
