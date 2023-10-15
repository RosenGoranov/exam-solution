package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.AppLoggedUser;
import com.example.spotifyplaylistapp.model.dtos.SongDTO;
import com.example.spotifyplaylistapp.model.entity.SongEntity;
import com.example.spotifyplaylistapp.model.entity.StyleEntity;
import com.example.spotifyplaylistapp.model.entity.UserEntity;
import com.example.spotifyplaylistapp.model.enums.StyleTypeEnum;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.service.SongService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final StyleRepository styleRepository;


    public SongServiceImpl(SongRepository songRepository, StyleRepository styleRepository) {
        this.songRepository = songRepository;
        this.styleRepository = styleRepository;

    }

    @Override
    @Transactional
    public void addSong(SongDTO songDTO) {
        this.songRepository.save(map(songDTO));
    }




    private SongEntity map(SongDTO songDTO) {
        StyleEntity style = this.styleRepository.findByName(songDTO.getStyle());

        return new SongEntity().setPerformer(songDTO.getPerformer())
                .setTitle(songDTO.getTitle())
                .setReleaseDate(songDTO.getReleaseDate())
                .setDuration(songDTO.getDuration())
                .setStyle(style);
    }
}
