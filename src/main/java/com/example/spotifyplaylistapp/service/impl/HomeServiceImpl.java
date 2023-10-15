package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.AppLoggedUser;
import com.example.spotifyplaylistapp.model.dtos.SongDTO;
import com.example.spotifyplaylistapp.model.dtos.SongsByStyleDTO;
import com.example.spotifyplaylistapp.model.entity.SongEntity;
import com.example.spotifyplaylistapp.model.entity.UserEntity;
import com.example.spotifyplaylistapp.model.enums.StyleTypeEnum;
import com.example.spotifyplaylistapp.repository.SongRepository;
import com.example.spotifyplaylistapp.repository.UserRepository;
import com.example.spotifyplaylistapp.service.HomeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class HomeServiceImpl implements HomeService {

    private final SongRepository songRepository;
    private final UserRepository userRepository;

    private final AppLoggedUser appLoggedUser;

    public HomeServiceImpl(SongRepository songRepository, UserRepository userRepository, AppLoggedUser appLoggedUser) {
        this.songRepository = songRepository;
        this.userRepository = userRepository;
        this.appLoggedUser = appLoggedUser;
    }

    @Override
    public SongsByStyleDTO songsByStyle() {
        SongsByStyleDTO songs = new SongsByStyleDTO();
        songs.setRock(this.songRepository
                .findByStyleName(StyleTypeEnum.ROCK)
                .stream().map(this::map)
                .toList());
        songs.setPop(this.songRepository
                .findByStyleName(StyleTypeEnum.POP)
                .stream().map(this::map)
                .toList());
        songs.setJazz(this.songRepository
                .findByStyleName(StyleTypeEnum.JAZZ)
                .stream().map(this::map)
                .toList());


        return songs;
    }

    @Override
    @Transactional
    public void addSongToUser(long id) {
        UserEntity user = this.userRepository.getById(appLoggedUser.getId());
        Optional<SongEntity> optional = user.getPlayList().stream().filter(song -> song.getId() == id).findFirst();
        if (optional.isEmpty()) {


            SongEntity song = this.songRepository.getById(id);

            user.addSongToPlayList(song);

            this.userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void deleteAll(long id) {
        UserEntity user = this.userRepository.getById(id);
        user.removeAllSong();
        this.userRepository.save(user);
    }

    @Override
    public List<SongDTO> findSongByUserId(long id) {
        return this.songRepository.findSongByUsersId(id).stream().map(this::map).toList();
    }

    @Override
    @Transactional
    public Integer getDuration() {
        if (appLoggedUser.isLogged()) {
            return this.userRepository.getById(appLoggedUser.getId()).getTotalPlayListMin();
        }
        return 0;
    }

    private SongDTO map(SongEntity s) {
        return new SongDTO()
                .setId(s.getId())
                .setTitle(s.getTitle())
                .setPerformer(s.getPerformer())
                .setDuration(s.getDuration())
                .setReleaseDate(s.getReleaseDate())
                .setStyle(s.getStyle().getName());

    }
}