package br.com.tiagodev.retrogamearchive.controller;

import br.com.tiagodev.retrogamearchive.domain.dto.GameGenreDTO;
import br.com.tiagodev.retrogamearchive.service.GameGenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game_genres")
@RequiredArgsConstructor
public class GameGenreController {

    private final GameGenreService gameGenreService;

    @PostMapping
    public ResponseEntity<GameGenreDTO> createGameGenre(@Valid @RequestBody GameGenreDTO gameGenreDTO) {
        GameGenreDTO saved = gameGenreService.createGameGenre(gameGenreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<GameGenreDTO>> getAllGameGenres() {
        List<GameGenreDTO> gameGenres = gameGenreService.getAllGameGenres();
        return ResponseEntity.ok(gameGenres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameGenreDTO> getGameGenreById(@PathVariable Long id) {
        GameGenreDTO gameGenre = gameGenreService.getGameGenreById(id);
        return ResponseEntity.ok(gameGenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGameGenre(@PathVariable Long id) {
        gameGenreService.deleteGameGenre(id);
        return ResponseEntity.noContent().build();
    }
}