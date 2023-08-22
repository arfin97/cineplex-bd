package com.cineplexbd.cineplex.service;


import com.cineplexbd.cineplex.domain.MovieRequest;
import com.cineplexbd.cineplex.domain.MovieResponse;

import java.util.List;

public interface MovieService {

    MovieResponse getMovieById(Long id);

    MovieResponse createMovie(MovieRequest movieRequest);

    List<MovieResponse> getAllMovies();
}
