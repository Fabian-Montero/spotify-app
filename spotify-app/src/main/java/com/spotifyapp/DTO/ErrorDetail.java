package com.spotifyapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorDetail {

    private String title;

    private Integer status;

    private String detail;

    private long timestamp;

    private String erorMessage;

}
