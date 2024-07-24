package com.spotifyapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SavingSongIdDTO {

    private String songName;

    private Long artistId;

    private Long albumId;
}
