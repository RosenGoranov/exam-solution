package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.AppLoggedUser;
import com.example.spotifyplaylistapp.model.dtos.LoginRequest;
import com.example.spotifyplaylistapp.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class LoginController {

    private final AuthenticationService authenticationService;
    private final AppLoggedUser appLoggedUser;

    public LoginController(AuthenticationService authenticationService, AppLoggedUser appLoggedUser) {
        this.authenticationService = authenticationService;
        this.appLoggedUser = appLoggedUser;
    }

    @ModelAttribute("loginRequest")
    public LoginRequest initLoginDTO() {
        return new LoginRequest();
    }

    @GetMapping("/auth/login")
    public String loginForm() {
        if (appLoggedUser.isLogged()){
            return "redirect:/users/home";
        }
        return "login";
    }


    @PostMapping("/auth/login")
    public String login(@Valid LoginRequest loginRequest,
                        BindingResult bindingResult,
                        RedirectAttributes attributes) {

        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("loginRequest", loginRequest)
                    .addFlashAttribute("org.springframework.validation.BindingResult.loginRequest", bindingResult);

            return "redirect:/auth/login";
        }

        boolean login = this.authenticationService.login(loginRequest);

        if (!login) {
            attributes.addFlashAttribute("bad_credential", true);
            return "redirect:/auth/login";
        }

        return "redirect:/users/home";
    }

    @GetMapping("/auth/logout")
    public String logOut(){
        if (!appLoggedUser.isLogged()){
            return "redirect:/auth/login";
        }
        appLoggedUser.logout();
        return "redirect:/";
    }
}