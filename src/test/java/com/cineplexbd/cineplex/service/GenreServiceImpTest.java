package com.cineplexbd.cineplex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cineplexbd.cineplex.domain.GenreResponse;
import com.cineplexbd.cineplex.entities.Genre;
import com.cineplexbd.cineplex.repository.GenreRepository;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
class GenreServiceImpTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreServiceImp genreServiceImp;


    @Test
    @DisplayName("Test Get Genres Empty List")
    void testGetGenresEmptyList() {
        when(genreRepository.findAll()).thenReturn(new ArrayList<>());

        assertTrue(genreServiceImp.getGenres().isEmpty());

        verify(genreRepository).findAll();
    }

    @Test
    @DisplayName("Test Get Genres")
    void testGetGenresWithItems() {
        Genre genre = new Genre();
        genre.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        genre.setId(1L);
        genre.setName("Name");
        genre.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());

        ArrayList<Genre> genreList = new ArrayList<>();
        genreList.add(genre);
        when(genreRepository.findAll()).thenReturn(genreList);
        List<GenreResponse> actualGenres = genreServiceImp.getGenres();

        assertEquals(1, actualGenres.size());
        assertEquals("Name", actualGenres.get(0).getName());

        verify(genreRepository).findAll();
    }
}

