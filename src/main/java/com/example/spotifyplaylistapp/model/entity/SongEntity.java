package com.example.spotifyplaylistapp.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "songs")
@NamedEntityGraph(
        name = "songsByUsers",
        attributeNodes = @NamedAttributeNode("users")
)

public class SongEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String performer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int duration;

    private LocalDate releaseDate;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST)
    private StyleEntity style;

    @ManyToMany(mappedBy = "playList",
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private List<UserEntity> users;

    public long getId() {
        return id;
    }

    public SongEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public SongEntity setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SongEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getDuration() {
        return duration;
    }

    public SongEntity setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public SongEntity setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public StyleEntity getStyle() {
        return style;
    }

    public SongEntity setStyle(StyleEntity style) {
        this.style = style;
        return this;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public SongEntity setUsers(List<UserEntity> usres) {
        this.users = usres;
        return this;
    }
}
