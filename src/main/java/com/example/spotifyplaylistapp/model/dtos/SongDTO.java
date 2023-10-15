package com.example.spotifyplaylistapp.model.dtos;

import com.example.spotifyplaylistapp.model.entity.StyleEntity;
import com.example.spotifyplaylistapp.model.enums.StyleTypeEnum;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;


public class SongDTO {

    private long id;

    @NotNull
    @Size(min = 3, max = 20)
    private String performer;

    @NotNull
    @Size(min = 2, max = 20)
    private String title;

    @Positive
    private int duration;

    @NotNull(message = "You must select a astyle")
    private StyleTypeEnum style;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    public long getId() {
        return id;
    }

    public SongDTO setId(long id) {
        this.id = id;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public SongDTO setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SongDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public SongDTO setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public StyleTypeEnum getStyle() {
        return style;
    }

    public SongDTO setStyle(StyleTypeEnum style) {
        this.style = style;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public SongDTO setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }


}
