package com.example.spotifyplaylistapp.model;

import com.example.spotifyplaylistapp.model.dtos.SongDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component("loggedUser")
@SessionScope
public class AppLoggedUser implements Serializable {

    private long id;

    private String userName;

    private boolean isLogged;

    private List<SongDTO> playList;

    public AppLoggedUser() {
        this.playList = new ArrayList<>();
    }

    public long getId() {
        return id;
    }


    public AppLoggedUser setId(long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public AppLoggedUser setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public AppLoggedUser setLogged(boolean logged) {
        isLogged = logged;
        return this;
    }

    public List<SongDTO> getPlayList() {
        return playList;
    }

    public AppLoggedUser setPlayList(List<SongDTO> playList) {
        this.playList = playList;
        return this;
    }

    public void logout() {
        setUserName(null);
        setLogged(false);

    }


}
