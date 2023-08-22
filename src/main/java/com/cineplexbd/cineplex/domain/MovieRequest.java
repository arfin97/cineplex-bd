package com.cineplexbd.cineplex.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {

    @NotBlank(message = "Title is required")
    private String title;
    @NotNull(message = "Release year is required")
    private Integer releaseYear;
    @Size(min = 10, message = "Description must be at least 10 characters long")
    private String description;
    @NotNull(message = "Genres are required")
    @NotEmpty(message = "Genres are required")
    private List<String> genres;
}
