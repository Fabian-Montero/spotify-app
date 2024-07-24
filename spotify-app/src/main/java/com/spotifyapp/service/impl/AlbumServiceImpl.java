package com.spotifyapp.service.impl;


import com.spotifyapp.entity.Album;
import com.spotifyapp.exception.ResourceNotFoundException;
import com.spotifyapp.repository.AlbumRepository;
import com.spotifyapp.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;
    @Override
    public Album getAlbum(Long albumId) throws ResourceNotFoundException{
        return albumRepository.findById(albumId).orElseThrow(
                () -> new ResourceNotFoundException("The album with the id " + " was not found"));
    }

    @Override
    public List<Album> getAlbumsByName(String name) {
        return albumRepository.findAlbumsByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }

    @Override
    public Album saveAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Album updateAlbum(Album album, Long albumId) {
        if (albumRepository.existsById(albumId)){

            Album existingAlbum = albumRepository.findById(albumId).get();
            existingAlbum.setName(album.getName());
            existingAlbum.setRelease_date(album.getRelease_date());

            return albumRepository.save(existingAlbum);
        }else {
            throw new ResourceNotFoundException("The album with the id  " + albumId + " was not found");

        }
    }
    @Override
    public void deleteAlbum(Long albumId) {
        if (albumRepository.existsById(albumId)){
            albumRepository.deleteById(albumId);
        }else {
            throw new ResourceNotFoundException("The album with the id  " + albumId + " was not found");
        }
    }
}
