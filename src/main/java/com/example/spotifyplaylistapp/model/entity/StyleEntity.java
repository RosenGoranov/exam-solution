package com.example.spotifyplaylistapp.model.entity;

import com.example.spotifyplaylistapp.model.enums.StyleTypeEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "styles")
public class StyleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,unique = true)
    private StyleTypeEnum name;

    @Column(columnDefinition = "text")
    private String description;


    public long getId() {
        return id;
    }

    public StyleEntity setId(long id) {
        this.id = id;
        return this;
    }

    public StyleTypeEnum getName() {
        return name;
    }

    public StyleEntity setName(StyleTypeEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public StyleEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
