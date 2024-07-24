package com.spotifyapp.service.impl;

import com.spotifyapp.entity.Artist;
import com.spotifyapp.exception.ResourceNotFoundException;
import com.spotifyapp.repository.ArtistRepository;
import com.spotifyapp.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepository artistRepository;
    @Override
    public Artist getArtist(Long artistId) throws ResourceNotFoundException {
            return artistRepository.findById(artistId).orElseThrow(
                    () -> new ResourceNotFoundException("The artist with the id: " + artistId + " was not found"));
    }

    @Override
    public List<Artist> getArtistsByName(String artistName) {
        return artistRepository.findArtistsByNameContainingIgnoreCase(artistName);
    }

    @Override
    public List<Artist> getArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist saveArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public void deleteArtist(Long artistId) {
        if (artistRepository.existsById(artistId)){
            artistRepository.deleteById(artistId);
        }else{
            throw new ResourceNotFoundException("The Artist you want to delete was not found");
        }
    }
    @Override
    public Artist updateArtist(Artist artist, Long artistId) {
        if (artistRepository.existsById(artistId)){
            Artist existingArtist = artistRepository.findById(artistId).get();

            existingArtist.setName(artist.getName());
            existingArtist.setListeners(artist.getListeners());
            existingArtist.setDescription(artist.getDescription());

            return artistRepository.save(existingArtist);
        }else {
            throw new ResourceNotFoundException("The artist you want to edit was not found");
        }
    }
}
