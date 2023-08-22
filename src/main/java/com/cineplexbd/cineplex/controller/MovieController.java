package com.cineplexbd.cineplex.controller;

import com.cineplexbd.cineplex.domain.MovieRequest;
import com.cineplexbd.cineplex.domain.MovieResponse;
import com.cineplexbd.cineplex.entities.Movie;
import com.cineplexbd.cineplex.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Get a movie by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the movie",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Movie not found",
                    content = @Content) })
    @GetMapping("/api/movies/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id){
        MovieResponse movieResponse = movieService.getMovieById(id);
        if(movieResponse == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(movieResponse);
    }

    @Operation(summary = "Get all movies")
    @GetMapping("/api/movies")
    public ResponseEntity<List<MovieResponse>> getAllMovies(){
        List<MovieResponse> movieResponses = movieService.getAllMovies();
        return ResponseEntity.ok(movieResponses);
    }

    @Operation(summary = "Create a movie")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/movies")
    public ResponseEntity<MovieResponse> createMovie(@Valid @RequestBody MovieRequest movieRequest){
        MovieResponse savedMovie = movieService.createMovie(movieRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }
}
