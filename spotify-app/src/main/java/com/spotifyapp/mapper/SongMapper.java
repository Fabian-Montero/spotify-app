package com.spotifyapp.mapper;

import com.spotifyapp.DTO.SavingSongIdDTO;
import com.spotifyapp.entity.Song;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SongMapper {
    public Song mapFromSongIdDTO(SavingSongIdDTO songIdDTO){
        Song song = new Song();
        BeanUtils.copyProperties(songIdDTO, song);
        return song;
    }

}
