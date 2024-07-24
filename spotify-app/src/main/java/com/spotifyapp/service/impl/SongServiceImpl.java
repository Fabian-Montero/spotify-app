package com.spotifyapp.service.impl;

import com.spotifyapp.DTO.SavingSongIdDTO;
import com.spotifyapp.entity.Album;
import com.spotifyapp.entity.Artist;
import com.spotifyapp.entity.Song;
import com.spotifyapp.exception.ResourceNotFoundException;
import com.spotifyapp.mapper.SongMapper;
import com.spotifyapp.repository.AlbumRepository;
import com.spotifyapp.repository.ArtistRepository;
import com.spotifyapp.repository.SongRepository;
import com.spotifyapp.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {
    @Autowired
    SongRepository songRepository;

    @Autowired
    SongMapper songMapper;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Override
    public Song getSong(Long songId) {
        return songRepository.findById(songId).orElseThrow(
                () -> new ResourceNotFoundException("The song with the id " + songId + " was not found"));
    }

    @Override
    public Page<Song> getSongsByName(String songName, Pageable pageable) {
        return songRepository.getSongsBySongNameContainingIgnoreCase(songName, pageable);
    }

    @Override
    public Page<Song> getSongs(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    @Override
    public SavingSongIdDTO saveSong(SavingSongIdDTO songDTO) {
        Song song = songMapper.mapFromSongIdDTO(songDTO);
        song.setAlbum(getAlbum(songDTO.getAlbumId()));
        song.setArtist(getArtist(songDTO.getArtistId()));
        songRepository.save(song);
        return songDTO;
    }

    @Override
    public SavingSongIdDTO editSong(SavingSongIdDTO songDTO, Long songId) {
        if (songRepository.existsById(songId)) {
            Song existingSong = songRepository.findById(songId).get();
            existingSong.setSongName(songDTO.getSongName());
            existingSong.setArtist(getArtist(songDTO.getArtistId()));
            existingSong.setAlbum(getAlbum(songDTO.getAlbumId()));

            songRepository.save(existingSong);
            return songDTO;
        } else {
            throw new ResourceNotFoundException("The song with the id " + songId + " was not found");
        }
    }

    @Override
    public void deleteSong(Long songId) {
        if (songRepository.existsById(songId)) {
            songRepository.deleteById(songId);
        } else {
            throw new ResourceNotFoundException("The song with the id " + songId + " was not found");
        }
    }

    private Album getAlbum(Long albumId) {
        return albumRepository.findById(albumId).orElseThrow(
                () -> new ResourceNotFoundException("The album with the id " + albumId + " was not found"));
    }

    private Artist getArtist(Long artistID) {
        return artistRepository.findById(artistID).orElseThrow(
                () -> new ResourceNotFoundException("The artist with the id " + artistID + " was not found"));
    }
}
