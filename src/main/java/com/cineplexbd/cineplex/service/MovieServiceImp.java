package com.cineplexbd.cineplex.service;

import com.cineplexbd.cineplex.domain.MovieRequest;
import com.cineplexbd.cineplex.domain.MovieResponse;
import com.cineplexbd.cineplex.entities.Genre;
import com.cineplexbd.cineplex.entities.Movie;
import com.cineplexbd.cineplex.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        List<String> genres = movie.getGenres().stream()
                .map(Genre::getName)
                .collect(Collectors.toList());
        movieResponse.setGenres(genres);

        return movieResponse;
    }

    @Override
    public MovieResponse createMovie(MovieRequest movieRequest) {
        Movie movie = new Movie();
        movie.setTitle(movieRequest.getTitle());
        movie.setReleaseYear(movieRequest.getReleaseYear());
        movie.setDescription(movieRequest.getDescription());
        //TODO need to change movie.setGenres(String.join(",", movieRequest.getGenres()));

        Movie savedMovie = movieRepository.save(movie);

        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setTitle(savedMovie.getTitle());
        movieResponse.setReleaseYear(savedMovie.getReleaseYear());
        movieResponse.setDescription(savedMovie.getDescription());
        //TODO need to change movieResponse.setGenres(List.of(savedMovie.getGenres().split(",")));

        return movieResponse;
    }

    @Override
    public List<MovieResponse> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();

        List<MovieResponse> movieResponses = movies.stream()
                .map(this::mapToMovieResponse)
                .collect(Collectors.toList());

        return movieResponses;
    }

    private MovieResponse mapToMovieResponse(Movie movie) {
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setTitle(movie.getTitle());
        movieResponse.setReleaseYear(movie.getReleaseYear());
        movieResponse.setDescription(movie.getDescription());

        if (movie.getGenres() != null && !movie.getGenres().isEmpty()) {
            List<String> genres = movie.getGenres().stream()
                    .map(Genre::getName)
                    .collect(Collectors.toList());
            movieResponse.setGenres(genres);
        }

        return movieResponse;
    }
}
