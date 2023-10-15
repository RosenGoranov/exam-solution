package com.example.spotifyplaylistapp.repository;

import com.example.spotifyplaylistapp.model.dtos.SongDTO;
import com.example.spotifyplaylistapp.model.entity.SongEntity;
import com.example.spotifyplaylistapp.model.enums.StyleTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, Long> {

    List<SongEntity> findByStyleName(StyleTypeEnum name);

    List<SongEntity> findSongByUsersId(long id);
}

