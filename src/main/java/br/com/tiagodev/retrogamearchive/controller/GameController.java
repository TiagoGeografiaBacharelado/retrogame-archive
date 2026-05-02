package br.com.tiagodev.retrogamearchive.controller;

import br.com.tiagodev.retrogamearchive.domain.dto.GameDTO;
import br.com.tiagodev.retrogamearchive.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @PostMapping
    public ResponseEntity<GameDTO> createGame(@Valid @RequestBody GameDTO gameDTO) {
        GameDTO saved = gameService.createGame(gameDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<GameDTO>> getAllGames() {
        List<GameDTO> games = gameService.getAllGames();
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable Long id) {
        GameDTO game = gameService.getGameById(id);
        return ResponseEntity.ok(game);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDTO> updateGame(@PathVariable Long id, @Valid @RequestBody GameDTO gameDTO) {
        GameDTO updated = gameService.updateGame(id, gameDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}