package com.cineplexbd.cineplex.controller;

import com.cineplexbd.cineplex.domain.MovieRequest;
import com.cineplexbd.cineplex.domain.MovieResponse;
import com.cineplexbd.cineplex.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    //TODO: API   Endpoint:   POST /api/movies
    //TODO: API   Endpoint:   GET /api/movies Response   (200   OK):
    //TODO API   Endpoint:   GET /api/movies/{movieId} Response   (200   OK):
    //TODO: Get   All   Genres:   GET /api/genres

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/api/movies/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id){
        MovieResponse movieResponse = movieService.getMovieById(id);
        return ResponseEntity.ok(movieResponse);
    }

    @PostMapping("/api/movies")
    public ResponseEntity<MovieResponse> createMovie(@RequestBody MovieRequest movieRequest){
        MovieResponse savedMovie = movieService.createMovie(movieRequest);
        return ResponseEntity.ok(savedMovie);
    }

    @GetMapping("/api/movies")
    public ResponseEntity<List<MovieResponse>> getAllMovies(){
        List<MovieResponse> movieResponses = movieService.getAllMovies();
        return ResponseEntity.ok(movieResponses);
    }
}
