package com.example.spotifyplaylistapp.model.dtos;

import java.util.ArrayList;
import java.util.List;

public class SongsByStyleDTO {

    private List<SongDTO> pop;
    private List<SongDTO> rock;

    private List<SongDTO> jazz;

    public SongsByStyleDTO() {
        this.pop = new ArrayList<>();
        this.rock = new ArrayList<>();
        this.jazz = new ArrayList<>();
    }

    public List<SongDTO> getPop() {
        return pop;
    }

    public SongsByStyleDTO setPop(List<SongDTO> pop) {
        this.pop = pop;
        return this;
    }

    public List<SongDTO> getRock() {
        return rock;
    }

    public SongsByStyleDTO setRock(List<SongDTO> rock) {
        this.rock = rock;
        return this;
    }

    public List<SongDTO> getJazz() {
        return jazz;
    }

    public SongsByStyleDTO setJazz(List<SongDTO> jazz) {
        this.jazz = jazz;
        return this;
    }
}
