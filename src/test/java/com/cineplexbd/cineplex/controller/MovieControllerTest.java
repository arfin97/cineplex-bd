package com.cineplexbd.cineplex.controller;

import com.cineplexbd.cineplex.domain.MovieRequest;
import com.cineplexbd.cineplex.domain.MovieResponse;
import com.cineplexbd.cineplex.service.MovieService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {
    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieService movieService;

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Test Get Movie by Valid ID")
    public void testGetMovieByIdValidId() {
        // Arrange
        Long movieId = 1L;
        MovieResponse expectedResponse = new MovieResponse(movieId, "Sample Movie", 2022, "Description", Arrays.asList("Action", "Adventure"));
        when(movieService.getMovieById(movieId)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<MovieResponse> responseEntity = movieController.getMovieById(movieId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());

        // Verify that the service method was called with the correct argument
        verify(movieService, times(1)).getMovieById(movieId);
    }

    @Test
    @DisplayName("Test Get Movie by Non-Existent ID")
    public void testGetMovieByIdNonExistentId() {
        // Arrange
        Long movieId = 999L; // A non-existent ID
        when(movieService.getMovieById(movieId)).thenReturn(null);

        // Act
        ResponseEntity<MovieResponse> responseEntity = movieController.getMovieById(movieId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());

        // Verify that the service method was called with the correct argument
        verify(movieService, times(1)).getMovieById(movieId);
    }

    @Test
    @DisplayName("Test Get All Movies")
    public void testGetAllMovies() {
        // Arrange
        List<MovieResponse> expectedResponses = Arrays.asList(
                new MovieResponse(1L, "Movie1", 2022, "Description1", Arrays.asList("Action", "Adventure")),
                new MovieResponse(2L, "Movie2", 2023, "Description2", Arrays.asList("Drama", "Romance"))
        );

        when(movieService.getAllMovies()).thenReturn(expectedResponses);

        // Act
        ResponseEntity<List<MovieResponse>> responseEntity = movieController.getAllMovies();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());

        // Verify that the service method was called
        verify(movieService, times(1)).getAllMovies();
    }

    @Test
    @DisplayName("Test Create Movie")
    public void testCreateMovie() {
        // Arrange
        MovieRequest movieRequest = new MovieRequest("New Movie", 2023, "This is a new movie.", Arrays.asList("Drama", "Romance"));
        MovieResponse expectedResponse = new MovieResponse(1L, movieRequest.getTitle(), movieRequest.getReleaseYear(), movieRequest.getDescription(), movieRequest.getGenres());

        when(movieService.createMovie(movieRequest)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<MovieResponse> responseEntity = movieController.createMovie(movieRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());

        // Verify that the service method was called with the correct argument
        verify(movieService, times(1)).createMovie(movieRequest);
    }

    @Test
    @DisplayName("Test Invalid Request")
    public void testCreateMovieWithInvalidRequest() {
        MovieRequest movieRequest = new MovieRequest();
        // Set invalid request data here
        movieRequest.setTitle(" ");
        Set<ConstraintViolation<MovieRequest>> violations = validator.validate(movieRequest);
        assertFalse(violations.isEmpty());
    }
}
