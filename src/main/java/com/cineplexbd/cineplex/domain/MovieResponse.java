package com.cineplexbd.cineplex.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponse {
    private String title;
    private Integer releaseYear;
    private String description;
    private List<String> genres;
}
