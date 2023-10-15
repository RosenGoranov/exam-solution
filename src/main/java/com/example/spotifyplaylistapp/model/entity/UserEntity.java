package com.example.spotifyplaylistapp.model.entity;

import com.example.spotifyplaylistapp.model.dtos.SongDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<SongEntity> playList;


    public long getId() {
        return id;
    }

    public UserEntity() {
        this.playList = new ArrayList<>();
    }


    public UserEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserEntity setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<SongEntity> getPlayList() {
        return playList;
    }

    public UserEntity setPlayList(List<SongEntity> playList) {
        this.playList = playList;
        return this;
    }

    public void addSongToPlayList(SongEntity song) {
        this.playList.add(song);
    }

    public void removeAllSong(){
        this.playList.clear();
    }
    public int getTotalPlayListMin() {
        if (this.playList.isEmpty()){
            return 0;
        }

        int sum = 0;

        for (SongEntity s : this.getPlayList()) sum += s.getDuration();
        return sum;
    }
    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", playList=" + playList.toString() +
                '}';
    }
}
