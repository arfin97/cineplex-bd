package com.cineplexbd.cineplex.service;

import com.cineplexbd.cineplex.domain.MovieRequest;
import com.cineplexbd.cineplex.domain.MovieResponse;
import com.cineplexbd.cineplex.entities.Genre;
import com.cineplexbd.cineplex.entities.Movie;
import com.cineplexbd.cineplex.exception.ResourceNotFoundException;
import com.cineplexbd.cineplex.repository.GenreRepository;
import com.cineplexbd.cineplex.repository.MovieRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieServiceImp implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    @Value("${movie.not.found.error.message}")
    private String MOVIE_NOT_FOUND_ERROR_MESSAGE;

    public MovieServiceImp(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public MovieResponse getMovieById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MOVIE_NOT_FOUND_ERROR_MESSAGE));
        return convertToMovieResponse(movie);
    }

    @Override
    public List<MovieResponse> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        List<MovieResponse> movieResponses = movies.stream()
                .map(this::convertToMovieResponse)
                .collect(Collectors.toList());

        return movieResponses;
    }

    @Override
    @Transactional
    public MovieResponse createMovie(MovieRequest movieRequest) {
        Movie movie = convertToMovieEntity(movieRequest);
        movie = movieRepository.save(movie);
        return convertToMovieResponse(movie);
    }

    private Movie convertToMovieEntity(MovieRequest movieRequest) {
        Movie movie = new Movie();
        movie.setTitle(movieRequest.getTitle());
        movie.setReleaseYear(movieRequest.getReleaseYear());
        movie.setDescription(movieRequest.getDescription());

        Set<Genre> genres = movieRequest.getGenres().stream()
                .map(genreRepository::findByName)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        movie.setGenres(genres);
        return movie;
    }

    private MovieResponse convertToMovieResponse(Movie movie) {
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setId(movie.getId());
        movieResponse.setTitle(movie.getTitle());
        movieResponse.setReleaseYear(movie.getReleaseYear());
        movieResponse.setDescription(movie.getDescription());

        List<String> genreNames = movie.getGenres().stream()
                .map(Genre::getName)
                .collect(Collectors.toList());

        movieResponse.setGenres(genreNames);
        return movieResponse;
    }
}
