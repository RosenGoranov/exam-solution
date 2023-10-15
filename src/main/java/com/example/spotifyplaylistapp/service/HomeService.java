package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dtos.SongDTO;
import com.example.spotifyplaylistapp.model.dtos.SongsByStyleDTO;

import java.util.List;

public interface HomeService {
    SongsByStyleDTO songsByStyle();

    void addSongToUser(long id);

    void deleteAll(long id);

    List<SongDTO> findSongByUserId(long id);

    Integer getDuration();

}

