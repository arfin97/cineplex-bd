package com.cineplexbd.cineplex.service;

import com.cineplexbd.cineplex.domain.GenreResponse;
import com.cineplexbd.cineplex.entities.Genre;
import com.cineplexbd.cineplex.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreServiceImp implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImp(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<GenreResponse> getGenres() {
        List<Genre> genres = genreRepository.findAll();

        List<GenreResponse> genreResponses = genres.stream()
                .map(this::mapToGenreResponse)
                .collect(Collectors.toList());

        return genreResponses;
    }

    private GenreResponse mapToGenreResponse(Genre genre) {
        GenreResponse genreResponse = new GenreResponse();
        genreResponse.setName(genre.getName());

        return genreResponse;
    }
}
