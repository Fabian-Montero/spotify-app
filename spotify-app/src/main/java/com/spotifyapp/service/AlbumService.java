package com.spotifyapp.service;

import com.spotifyapp.entity.Album;
import com.spotifyapp.exception.ResourceNotFoundException;

import java.util.List;

public interface AlbumService {

    Album getAlbum(Long albumId) throws ResourceNotFoundException;

    List<Album> getAlbumsByName(String name);

    List<Album> getAlbums();

    Album saveAlbum(Album album);

    Album updateAlbum(Album albumm, Long albumId) throws ResourceNotFoundException;

    void deleteAlbum(Long albumId) throws ResourceNotFoundException;


}
