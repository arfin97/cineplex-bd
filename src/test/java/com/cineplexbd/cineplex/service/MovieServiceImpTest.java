package com.cineplexbd.cineplex.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cineplexbd.cineplex.domain.MovieRequest;
import com.cineplexbd.cineplex.domain.MovieResponse;
import com.cineplexbd.cineplex.entities.Movie;
import com.cineplexbd.cineplex.exception.ResourceNotFoundException;
import com.cineplexbd.cineplex.repository.GenreRepository;
import com.cineplexbd.cineplex.repository.MovieRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
class MovieServiceImpTest {
    @Mock
    private GenreRepository genreRepository;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImp movieServiceImp;

    @Test
    @DisplayName("Get Movie by Valid ID - Successful")
    public void testGetMovieByIdValidId() {
        Movie movie = new Movie();
        movie.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        movie.setDescription("The characteristics of someone or something");
        movie.setGenres(new HashSet<>());
        movie.setId(1L);
        movie.setReleaseYear(1);
        movie.setTitle("Dr");
        movie.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Movie> ofResult = Optional.of(movie);
        when(movieRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        MovieResponse actualMovieById = movieServiceImp.getMovieById(1L);
        assertEquals("The characteristics of someone or something", actualMovieById.getDescription());
        assertEquals("Dr", actualMovieById.getTitle());
        assertEquals(1, actualMovieById.getReleaseYear().intValue());
        assertEquals(1L, actualMovieById.getId().longValue());
        assertTrue(actualMovieById.getGenres().isEmpty());
        verify(movieRepository).findById(Mockito.<Long>any());
    }

    @Test
    @DisplayName("Get Movie by Non-Existent ID - Error")
    public void testGetMovieByIdNonExistentId() {
        Optional<Movie> emptyResult = Optional.empty();
        when(movieRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(ResourceNotFoundException.class, () -> movieServiceImp.getMovieById(1L));
        verify(movieRepository).findById(Mockito.<Long>any());
    }

    @Test
    @DisplayName("Get Movie by Invalid ID - Error")
    public void testGetMovieByIdInvalidId() {
        when(movieRepository.findById(Mockito.<Long>any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> movieServiceImp.getMovieById(1L));
        verify(movieRepository).findById(Mockito.<Long>any());
    }

    @Test
    @DisplayName("Get All Movies (Empty List) - Successful")
    public void testGetAllMoviesEmptyList() {
        when(movieRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(movieServiceImp.getAllMovies().isEmpty());
        verify(movieRepository).findAll();
    }

    @Test
    @DisplayName("Get All Movies (Single Movie) - Successful")
    public void testGetAllMoviesSingleMovie() {
        Movie movie = new Movie();
        movie.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        movie.setDescription("The characteristics of someone or something");
        movie.setGenres(new HashSet<>());
        movie.setId(1L);
        movie.setReleaseYear(1);
        movie.setTitle("Dr");
        movie.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());

        ArrayList<Movie> movieList = new ArrayList<>();
        movieList.add(movie);
        when(movieRepository.findAll()).thenReturn(movieList);
        List<MovieResponse> actualAllMovies = movieServiceImp.getAllMovies();
        assertEquals(1, actualAllMovies.size());
        MovieResponse getResult = actualAllMovies.get(0);
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals("Dr", getResult.getTitle());
        assertEquals(1, getResult.getReleaseYear().intValue());
        assertEquals(1L, getResult.getId().longValue());
        assertTrue(getResult.getGenres().isEmpty());
        verify(movieRepository).findAll();
    }

    @Test
    @DisplayName("Get All Movies (Multiple Movies) - Successful")
    public void testGetAllMoviesMultipleMovies() {
        Movie movie = new Movie();
        movie.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        movie.setDescription("The characteristics of someone or something");
        movie.setGenres(new HashSet<>());
        movie.setId(1L);
        movie.setReleaseYear(1);
        movie.setTitle("Dr");
        movie.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());

        Movie movie2 = new Movie();
        movie2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        movie2.setDescription("Description");
        movie2.setGenres(new HashSet<>());
        movie2.setId(2L);
        movie2.setReleaseYear(0);
        movie2.setTitle("Mr");
        movie2.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());

        ArrayList<Movie> movieList = new ArrayList<>();
        movieList.add(movie2);
        movieList.add(movie);
        when(movieRepository.findAll()).thenReturn(movieList);
        List<MovieResponse> actualAllMovies = movieServiceImp.getAllMovies();
        assertEquals(2, actualAllMovies.size());
        MovieResponse getResult = actualAllMovies.get(0);
        assertEquals("Mr", getResult.getTitle());
        MovieResponse getResult2 = actualAllMovies.get(1);
        assertEquals("Dr", getResult2.getTitle());
        assertEquals(1, getResult2.getReleaseYear().intValue());
        assertEquals(1L, getResult2.getId().longValue());
        List<String> genres = getResult2.getGenres();
        assertTrue(genres.isEmpty());
        assertEquals("The characteristics of someone or something", getResult2.getDescription());
        assertEquals(0, getResult.getReleaseYear().intValue());
        assertEquals(2L, getResult.getId().longValue());
        assertEquals(genres, getResult.getGenres());
        assertEquals("Description", getResult.getDescription());
        verify(movieRepository).findAll();
    }

    @Test
    @DisplayName("Get All Movies - Error")
    public void testGetAllMoviesError() {
        when(movieRepository.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> movieServiceImp.getAllMovies());
        verify(movieRepository).findAll();
    }

    @Test
    @DisplayName("Create Movie (Single Movie) - Successful")
    public void testCreateMovieSingleMovie() {
        Movie movie = new Movie();
        movie.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        movie.setDescription("The characteristics of someone or something");
        movie.setGenres(new HashSet<>());
        movie.setId(1L);
        movie.setReleaseYear(1);
        movie.setTitle("Dr");
        movie.setUpdatedAt(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(movieRepository.save(Mockito.<Movie>any())).thenReturn(movie);
        MovieRequest.MovieRequestBuilder descriptionResult = MovieRequest.builder()
                .description("The characteristics of someone or something");
        ArrayList<String> genres = new ArrayList<>();
        MovieRequest movieRequest = descriptionResult.genres(genres).releaseYear(1).title("Dr").build();
        MovieResponse actualCreateMovieResult = movieServiceImp.createMovie(movieRequest);
        assertEquals("The characteristics of someone or something", actualCreateMovieResult.getDescription());
        assertEquals("Dr", actualCreateMovieResult.getTitle());
        assertEquals(1, actualCreateMovieResult.getReleaseYear().intValue());
        assertEquals(1L, actualCreateMovieResult.getId().longValue());
        assertEquals(genres, actualCreateMovieResult.getGenres());
        verify(movieRepository).save(Mockito.<Movie>any());
    }

}

