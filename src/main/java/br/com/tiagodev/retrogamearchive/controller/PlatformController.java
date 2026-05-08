package br.com.tiagodev.retrogamearchive.controller;

import br.com.tiagodev.retrogamearchive.domain.dto.PlatformDTO;
import br.com.tiagodev.retrogamearchive.service.PlatformService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/platforms")
@RequiredArgsConstructor
public class PlatformController {

    private final PlatformService platformService;

    @PostMapping
    public ResponseEntity<PlatformDTO> createPlatform(@Valid @RequestBody PlatformDTO platformDTO) {
        PlatformDTO saved = platformService.createPlatform(platformDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<PlatformDTO>> getAllPlatforms() {
        List<PlatformDTO> platforms = platformService.getAllPlatforms();
        return ResponseEntity.ok(platforms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatformDTO> getPlatformById(@PathVariable Long id) {
        PlatformDTO platform = platformService.getPlatformById(id);
        return ResponseEntity.ok(platform);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlatformDTO> updatePlatform(@PathVariable Long id, @Valid @RequestBody PlatformDTO platformDTO) {
        PlatformDTO updated = platformService.updatePlatform(id, platformDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlatform(@PathVariable Long id) {
        platformService.deletePlatform(id);
        return ResponseEntity.noContent().build();
    }
}
