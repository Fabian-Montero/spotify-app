package com.spotifyapp.controller;

import com.spotifyapp.DTO.SavingSongIdDTO;
import com.spotifyapp.entity.Song;
import com.spotifyapp.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@Controller
//@RestController
@RequestMapping("/spotify-app/songs")
public class SongController {
    @Autowired
    SongService songService;

    @GetMapping("/{songId}")
    public String getSongById(@PathVariable("songId") Long songId, Model model){
        Song song = songService.getSong(songId);
        model.addAttribute("song", song);
        return "songs_individual";
    }

    @GetMapping("/songName/{songName}")
    public String getSongByName(@PageableDefault(size = 3 , page = 0)Pageable pageable, @PathVariable("songName") String songName, Model model){
        Page<Song> songList = songService.getSongsByName(songName, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        model.addAttribute("songs", songList);

        //Auxiliar method
        loadPaginationAttributes(model, songList);
        return "songs";
    }

    @GetMapping()
    public String getSongs(@PageableDefault(size = 3, page = 0) Pageable pageable, Model model){
        Song song = new Song();
        model.addAttribute("song", new Song());
        //Pages
        Page<Song> songPages = songService.getSongs(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        model.addAttribute("songs", songPages);

        //Auxiliar method
        loadPaginationAttributes(model, songPages);

        return "songs";
    }

    @GetMapping("/create")
    public String createSong(Model model){
        model.addAttribute("song", new SavingSongIdDTO());
        return "create_song";
    }

    @PostMapping("/save")
    public String saveSong(@ModelAttribute SavingSongIdDTO songDTO){
        songService.saveSong(songDTO);
        return "redirect:/spotify-app/songs";
    }

    @GetMapping("/update/form/{songId}")
    public String updateSongForm(@PathVariable("songId") Long songId, Model model){
        Song song2 = songService.getSong(songId);
        SavingSongIdDTO songDTO = new SavingSongIdDTO();
        songDTO.setSongName(song2.getSongName());
        songDTO.setArtistId(song2.getArtist().getArtistId());
        songDTO.setAlbumId(song2.getAlbum().getAlbumId());
        model.addAttribute("song", songDTO);
        return "edit_song";
    }
    @PostMapping("/update/{songId}")
    public String updateSong(@ModelAttribute SavingSongIdDTO songDTO, @PathVariable("songId")Long songId){
        songService.editSong(songDTO, songId);
        return "redirect:/spotify-app/songs";
    }
    @GetMapping("/delete/{songId}")
    public String deleteSong(@PathVariable("songId")Long songId){
        songService.deleteSong(songId);
        return "redirect:/spotify-app/songs";
    }

    private void loadPaginationAttributes(Model model, Page<Song> songPages) {
        var totalPages = songPages.getTotalPages();
        var currentPage = songPages.getNumber();

        var start = Math.max(1, currentPage);
        var end = Math.min(currentPage + 5, totalPages);

        if (totalPages > 0) {
            List<Integer> pageNumbers = new ArrayList<>();
            for (int i = start; i <= end; i++) {
                pageNumbers.add(i);
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }

        List<Integer> pageSizeOptions = Arrays.asList(3, 5, 7);
        model.addAttribute("pageSizeOptions", pageSizeOptions);
    }

}
