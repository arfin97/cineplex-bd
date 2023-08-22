package com.cineplexbd.cineplex.controller;

import com.cineplexbd.cineplex.domain.GenreResponse;
import com.cineplexbd.cineplex.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }
    @Operation(summary = "Get all genres")
    @GetMapping("/api/genres")
    public ResponseEntity<List<GenreResponse>> getGenres(){
        List<GenreResponse> genreResponses = genreService.getGenres();
        return ResponseEntity.ok(genreResponses);
    }
}
