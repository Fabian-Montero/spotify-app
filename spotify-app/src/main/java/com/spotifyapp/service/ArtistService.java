package com.spotifyapp.service;

import com.spotifyapp.entity.Artist;
import com.spotifyapp.exception.ResourceNotFoundException;

import java.util.List;

public interface ArtistService {

    Artist getArtist(Long artistId) throws ResourceNotFoundException;

    List<Artist> getArtistsByName(String artistName);
    List<Artist> getArtists();

    Artist saveArtist(Artist artist);

    public void deleteArtist(Long artistId) throws ResourceNotFoundException;

    Artist updateArtist(Artist artist, Long artistId) throws ResourceNotFoundException;


}
