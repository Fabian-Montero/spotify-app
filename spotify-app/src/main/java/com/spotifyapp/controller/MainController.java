package com.spotifyapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "spotify-app")
public class MainController {

    @GetMapping("/home")
    public String  home(Model model){
        return "home";
    }
}
