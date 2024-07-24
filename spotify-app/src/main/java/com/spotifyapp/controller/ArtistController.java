package com.spotifyapp.controller;

import com.spotifyapp.entity.Artist;
import com.spotifyapp.service.ArtistService;
import com.spotifyapp.service.impl.ArtistServiceImpl;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path ="/spotify-app/artists")
public class ArtistController {
    @Autowired
    ArtistService artistService;

    @GetMapping()
    public String getArtists(Model model){
        model.addAttribute("artists",artistService.getArtists());
        return "artists";
    }
    @GetMapping("/create")
    public String createArtist(Model model){
        model.addAttribute("artist", new Artist());
        return "create_artist";
    }
    @PostMapping("/save")
    public String saveArtist(@ModelAttribute Artist artist){
        artistService.saveArtist(artist);
        return "redirect:/spotify-app/artists";
    }
    @GetMapping("/{artistId}")
    public String getArtist(@PathVariable("artistId") Long artistId, Model model){
        model.addAttribute("artist", artistService.getArtist(artistId));
        return "artists_individual";
    }
    @GetMapping("/artistName/{artistName}")
    public String getArtist(@PathVariable("artistName") String artistName, Model model){
        List<Artist> artists = artistService.getArtistsByName(artistName);
        model.addAttribute("artists", artists);
        return "artists";
    }
    @GetMapping("/update/form/{artistId}")
    public String updateArtistForm(@PathVariable("artistId") Long artistId, Model model){
        model.addAttribute("artist", artistService.getArtist(artistId));
        return "edit_artist";
    }
    @PostMapping("/update/{artistId}")
    public String updateArtist(@ModelAttribute Artist artist, @PathVariable("artistId") Long artistId){
        artistService.updateArtist(artist, artistId);
        return "redirect:/spotify-app/artists";
    }
    @GetMapping("/delete/{artistId}")
    public String deleteArtist(@PathVariable("artistId") Long artistId){
        artistService.deleteArtist(artistId);
        return "redirect:/spotify-app/artists";
    }
}
