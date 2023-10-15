package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.AppLoggedUser;
import com.example.spotifyplaylistapp.model.dtos.SongDTO;
import com.example.spotifyplaylistapp.model.enums.StyleTypeEnum;
import com.example.spotifyplaylistapp.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("songs")
public class SongController {

    private final SongService songService;
    private final AppLoggedUser appLoggedUser;

    public SongController(SongService songService, AppLoggedUser appLoggedUser) {
        this.songService = songService;
        this.appLoggedUser = appLoggedUser;
    }


    @ModelAttribute("song")
    public SongDTO initSongDTO() {
        return new SongDTO();
    }

    @ModelAttribute("style")
    public StyleTypeEnum[] initStyle() {
        return StyleTypeEnum.values();
    }

    @GetMapping("/add")
    public String addSong() {
        if(!appLoggedUser.isLogged()){
            return "redirect:/auth/login";
        }
        return "song-add";
    }

    @PostMapping("/add")
    public String addValidSong(@Valid SongDTO song,
                               BindingResult bindingResult,
                               RedirectAttributes attributes) {
        if (!appLoggedUser.isLogged()){
            return "redirect:/auth/login";
        }

        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("song", song)
                    .addFlashAttribute("org.springframework.validation.BindingResult.song", bindingResult);

            return "redirect:/songs/add";
        }

        this.songService.addSong(song);

        return "redirect:/users/home";
    }



}
