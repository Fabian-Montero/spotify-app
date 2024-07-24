package com.spotifyapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long songId;

    private String songName;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne()
    @JoinColumn(name = "artist_id")
    private Artist artist;


}
