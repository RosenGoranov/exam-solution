package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.AppLoggedUser;
import com.example.spotifyplaylistapp.model.dtos.SongDTO;
import com.example.spotifyplaylistapp.model.dtos.SongsByStyleDTO;
import com.example.spotifyplaylistapp.service.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final AppLoggedUser appLoggedUser;
    private final HomeService homeService;


    public HomeController(AppLoggedUser appLoggedUser, HomeService homeService) {
        this.appLoggedUser = appLoggedUser;
        this.homeService = homeService;
    }

    @ModelAttribute("songs")
    public SongsByStyleDTO initGyStyle() {

        return this.homeService.songsByStyle();
    }

    @ModelAttribute("userList")
    public List<SongDTO> initUserList() {
        List<SongDTO> songDTOS = Collections.unmodifiableList(this.homeService.findSongByUserId(appLoggedUser.getId()));
        return songDTOS;
    }

    @ModelAttribute("totalDuration")
    public Integer getTotalDurationOfUserPlayList() {
        return this.homeService.getDuration();

    }

    @GetMapping("/")
    public String index() {
        if (appLoggedUser.isLogged()) {
            return "redirect:/users/home";
        }
        return "/index";
    }

    @GetMapping("/users/home")
    public String home() {
        if (!appLoggedUser.isLogged()) {
            return "redirect:/auth/login";
        }
        return "home";
    }

    @GetMapping("home/add-song-to-playlist/{id}")
    public String addSongToPlayList(@PathVariable("id") long songId) {

        if (appLoggedUser.isLogged()) {

            this.homeService.addSongToUser(songId);

            return "redirect:/users/home";
        }
        return "redirect:/";
    }

    @GetMapping("/home/delete")
    public String deletePlayList() {
        this.homeService.deleteAll(this.appLoggedUser.getId());
        return"redirect:/users/home";
    }
}
