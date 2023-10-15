package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.dtos.SongDTO;
import com.example.spotifyplaylistapp.model.enums.StyleTypeEnum;

import java.util.List;

public interface SongService {

    void addSong(SongDTO songDTO);

}
