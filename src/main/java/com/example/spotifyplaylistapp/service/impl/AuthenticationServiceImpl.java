package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.AppLoggedUser;
import com.example.spotifyplaylistapp.model.dtos.LoginRequest;
import com.example.spotifyplaylistapp.model.dtos.RegisterRequest;
import com.example.spotifyplaylistapp.model.dtos.SongDTO;
import com.example.spotifyplaylistapp.model.entity.SongEntity;
import com.example.spotifyplaylistapp.model.entity.UserEntity;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.service.AuthenticationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AppLoggedUser appLoggedUser;


    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AppLoggedUser appLoggedUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.appLoggedUser = appLoggedUser;
    }

    @Override
    public boolean register(RegisterRequest registerRequest) {

        this.userRepository.save(map(registerRequest));

        return true;
    }

    @Override
    public boolean login(LoginRequest loginRequest) {

        Optional<UserEntity> optionalUser = this.userRepository
                .findByUserName(loginRequest.getUsername());
        if (optionalUser.isPresent()) {
            String loginPassword = loginRequest.getPassword();
            String userPassword = optionalUser.get().getPassword();

            boolean matches = passwordEncoder.matches(loginPassword, userPassword);
            if (userPassword != null && matches) {
                this.appLoggedUser
                        .setId(optionalUser.get().getId())
                        .setUserName(optionalUser.get().getUserName())
                        .setLogged(true)
                        .setPlayList(map(optionalUser.get().getPlayList()));
                return true;
            } else {
                this.appLoggedUser
                        .logout();
            }
        }

        return false;
    }

    private UserEntity map(RegisterRequest registerRequest) {

        return new UserEntity().setUserName(registerRequest.getUsername())
                .setEmail((registerRequest.getEmail()).toLowerCase())
                .setPassword(passwordEncoder.encode(registerRequest.getPassword()));

    }

    private List<SongDTO> map(List<SongEntity> songEntities){
        return  Collections.unmodifiableList(songEntities
                .stream()
                .map(this::map)
                .toList());
    }


    private SongDTO map(SongEntity s){
        return new SongDTO()
                .setPerformer(s.getPerformer())
                .setTitle(s.getTitle())
                .setDuration(s.getDuration())
                .setStyle(s.getStyle().getName())
                .setReleaseDate(s.getReleaseDate());
    }
}
