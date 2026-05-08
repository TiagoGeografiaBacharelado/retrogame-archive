package br.com.tiagodev.retrogamearchive.controller;

import br.com.tiagodev.retrogamearchive.domain.dto.GamePlatformDTO;
import br.com.tiagodev.retrogamearchive.service.GamePlatformService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game_platforms")
@RequiredArgsConstructor
public class GamePlatformController {

    private final GamePlatformService gamePlatformService;

    @PostMapping
    public ResponseEntity<GamePlatformDTO> createGamePlatform(@Valid @RequestBody GamePlatformDTO gamePlatformDTO) {
        GamePlatformDTO saved = gamePlatformService.createGamePlatform(gamePlatformDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<GamePlatformDTO>> getAllGamePlatforms() {
        List<GamePlatformDTO> gamePlatforms = gamePlatformService.getAllGamePlatforms();
        return ResponseEntity.ok(gamePlatforms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GamePlatformDTO> getGamePlatformById(@PathVariable Long id) {
        GamePlatformDTO gamePlatform = gamePlatformService.getGamePlatformById(id);
        return ResponseEntity.ok(gamePlatform);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGamePlatform(@PathVariable Long id) {
        gamePlatformService.deleteGamePlatform(id);
        return ResponseEntity.noContent().build();
    }
}