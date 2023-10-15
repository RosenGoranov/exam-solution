package com.example.spotifyplaylistapp.model.dtos;

import com.example.spotifyplaylistapp.validation.UniqueUserName;

import javax.validation.constraints.Size;

public class LoginRequest {

    @Size(min = 3,max = 20)
    private String username;

    @Size(min = 3,max = 20)
    private String password;

    public String getUsername() {
        return username;
    }

    public LoginRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
