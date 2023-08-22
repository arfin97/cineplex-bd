package com.cineplexbd.cineplex.controller;

import com.cineplexbd.cineplex.domain.MovieDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {

    @GetMapping("/api/movies/{movieId}")
    public MovieDto getMovieById(@PathVariable String movieId){

        return MovieDto.builder()
                .title("Harry Potter")
                .description("Harry Potter is a series of seven fantasy novels written by British author J. K. Rowling.")
                .releaseYear(2001)
                .genres(List.of("Fantasy", "Adventure", "Family"))
                .build();
    }
}
