package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.AppLoggedUser;
import com.example.spotifyplaylistapp.model.dtos.RegisterRequest;
import com.example.spotifyplaylistapp.service.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegisterController {


    private final AuthenticationService authenticationService;
    private final AppLoggedUser appLoggedUser;

    public RegisterController(AuthenticationService authenticationService, AppLoggedUser appLoggedUser) {
        this.authenticationService = authenticationService;
        this.appLoggedUser = appLoggedUser;
    }


    @ModelAttribute(name = "registerUser")
    public RegisterRequest initRegisterDTO() {
        return new RegisterRequest();
    }

    @GetMapping("/auth/register")
    public String getRegisterForm() {
        if (appLoggedUser.isLogged()){
            return "redirect:/users/home";
        }
        return "register";
    }

    @PostMapping("auth/register")
    public String registerValidUser(@Valid RegisterRequest registerUser,
                                    BindingResult bindingResult,
                                    RedirectAttributes attributes) {


        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("registerUser", registerUser)
                    .addFlashAttribute("org.springframework.validation.BindingResult.registerUser", bindingResult);
            return "redirect:/auth/register";
        }

        this.authenticationService.register(registerUser);

        return "redirect:/auth/login";
    }

}
