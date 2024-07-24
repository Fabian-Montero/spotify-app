package com.spotifyapp.service;

import com.spotifyapp.DTO.SavingSongIdDTO;
import com.spotifyapp.entity.Song;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface SongService {

    Song getSong(Long songId);

    Page<Song> getSongsByName(String songName, Pageable pageable);

    Page<Song> getSongs(Pageable pageable);

    SavingSongIdDTO saveSong(SavingSongIdDTO songDTO);

    SavingSongIdDTO editSong(SavingSongIdDTO songDTO, Long songId);

    void deleteSong(Long songId);
}
