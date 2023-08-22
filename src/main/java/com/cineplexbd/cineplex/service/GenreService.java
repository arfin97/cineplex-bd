package com.cineplexbd.cineplex.service;

import com.cineplexbd.cineplex.domain.GenreResponse;

import java.util.List;

public interface GenreService {

    List<GenreResponse> getGenres();
}
