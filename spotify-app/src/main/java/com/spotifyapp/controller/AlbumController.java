package com.spotifyapp.controller;

import com.spotifyapp.entity.Album;
import com.spotifyapp.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("spotify-app/albums")
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @GetMapping()
    public String getAlbums(Model model){
        model.addAttribute("albums",albumService.getAlbums());
        return "albums";
    }

    @GetMapping("/create")
    public String createAlbum(Model model){
        model.addAttribute("album", new Album());
        return "create_album";
    }
    @PostMapping("/save")
    public String saveAlbum(@ModelAttribute Album album){
        albumService.saveAlbum(album);
        return "redirect:/spotify-app/albums";
    }

    @GetMapping("/{albumId}")
    public String getAlbum(@PathVariable("albumId") Long albumId, Model model){
        Album album = albumService.getAlbum(albumId);
        model.addAttribute("album", album);
        return "albums_individual";
    }
    @GetMapping("/albumName/{albumName}")
    public String getAlbumsByName(@PathVariable("albumName") String albumName, Model model){
        List<Album> albums = albumService.getAlbumsByName(albumName);
        model.addAttribute("albums", albums);
        return "albums";
    }

    @GetMapping("/update/form/{albumId}")
    public String updateAlbumForm(@PathVariable("albumId")Long albumId, Model model){
        Album album = albumService.getAlbum(albumId);
        model.addAttribute("album", album);
        return "edit_album";
    }
    @PostMapping("/update/{albumId}")
    public String updateAlbum(@PathVariable("albumId") Long albumId,@ModelAttribute Album album){
        albumService.updateAlbum(album, albumId);
        return "redirect:/spotify-app/albums";
    }

    @GetMapping("/delete/{albumId}")
    public String deleteAlbum(@PathVariable("albumId")Long artistId){
        albumService.deleteAlbum(artistId);
        return "redirect:/spotify-app/albums";
    }
}



