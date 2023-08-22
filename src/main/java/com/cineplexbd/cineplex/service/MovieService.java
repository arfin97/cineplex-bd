package com.cineplexbd.cineplex.service;


import com.cineplexbd.cineplex.domain.MovieResponse;

public interface MovieService {

    MovieResponse getMovieById(Long id);
}
