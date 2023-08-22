package com.cineplexbd.cineplex.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {
    private String title;
    private Integer releaseYear;
    private String description;
    private String genres;
}
