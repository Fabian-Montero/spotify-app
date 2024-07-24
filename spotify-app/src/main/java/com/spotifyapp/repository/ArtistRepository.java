package com.spotifyapp.repository;

import com.spotifyapp.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    List<Artist> findArtistsByNameContainingIgnoreCase(String name);

}




