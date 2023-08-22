package com.cineplexbd.cineplex.service;

import com.cineplexbd.cineplex.domain.MovieRequest;
import com.cineplexbd.cineplex.domain.MovieResponse;
import com.cineplexbd.cineplex.entities.Genre;
import com.cineplexbd.cineplex.entities.Movie;
import com.cineplexbd.cineplex.repository.GenreRepository;
import com.cineplexbd.cineplex.repository.MovieRepository;
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

    public MovieServiceImp(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
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
                .map(genreName -> genreRepository.findByName(genreName))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        movie.setGenres(genres);
        return movie;
    }

    private MovieResponse convertToMovieResponse(Movie movie) {
        MovieResponse movieResponse = new MovieResponse();
        movieResponse.setTitle(movie.getTitle());
        movieResponse.setReleaseYear(movie.getReleaseYear());
        movieResponse.setDescription(movie.getDescription());

        List<String> genreNames = movie.getGenres().stream()
                .map(Genre::getName)
                .collect(Collectors.toList());

        movieResponse.setGenres(genreNames);
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
