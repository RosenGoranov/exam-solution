package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dtos.LoginRequest;
import com.example.spotifyplaylistapp.model.dtos.RegisterRequest;

public interface AuthenticationService {
    boolean register(RegisterRequest registerRequest);

    boolean login(LoginRequest loginRequest);
}
