package com.cineplexbd.cineplex.service;

import com.cineplexbd.cineplex.domain.MovieRequest;
import com.cineplexbd.cineplex.domain.MovieResponse;
import com.cineplexbd.cineplex.entities.Movie;
import com.cineplexbd.cineplex.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImp implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImp(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public MovieResponse getMovieById(Long id) {
        MovieResponse movieResponse = new MovieResponse();
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));

        movieResponse.setTitle(movie.getTitle());
        movieResponse.setReleaseYear(movie.getReleaseYear());
        movieResponse.setDescription(movie.getDescription());
        movieResponse.setGenres(List.of(movie.getGenres().split(",")));

        return movieResponse;
    }

    @Override
    public MovieResponse createMovie(MovieRequest movieRequest) {
        Movie movie = new Movie();
        movie.setTitle(movieRequest.getTitle());
        movie.setReleaseYear(movieRequest.getReleaseYear());
        movie.setDescription(movieRequest.getDescription());
        movie.setGenres(String.join(",", movieRequest.getGenres()));

        Movie savedMovie = movieRepository.save(movie);

        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setTitle(savedMovie.getTitle());
        movieResponse.setReleaseYear(savedMovie.getReleaseYear());
        movieResponse.setDescription(savedMovie.getDescription());
        movieResponse.setGenres(List.of(savedMovie.getGenres().split(",")));

        return movieResponse;
    }
}
