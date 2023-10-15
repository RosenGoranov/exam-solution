package com.example.spotifyplaylistapp.model.dtos;

import com.example.spotifyplaylistapp.validation.FieldMatch;
import com.example.spotifyplaylistapp.validation.UniqueUserEmail;
import com.example.spotifyplaylistapp.validation.UniqueUserName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch(first = "password",
        second = "confirmPassword",
        message = "Password does not match")
public class RegisterRequest {

    @NotNull
    @Size(min = 3, max = 20)
    @UniqueUserName
    private String username;

    @NotNull
    @Size(min = 3, max = 20)
    private String password;

    @NotNull
    private String confirmPassword;

    @NotNull
    @UniqueUserEmail
    private String email;


    public String getUsername() {
        return username;
    }

    public RegisterRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public RegisterRequest setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterRequest setEmail(String email) {
        this.email = email;
        return this;
    }
}
