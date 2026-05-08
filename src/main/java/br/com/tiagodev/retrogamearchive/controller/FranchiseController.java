package br.com.tiagodev.retrogamearchive.controller;

import br.com.tiagodev.retrogamearchive.domain.dto.FranchiseDTO;
import br.com.tiagodev.retrogamearchive.service.FranchiseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/franchises")
@RequiredArgsConstructor
public class FranchiseController {

    private final FranchiseService franchiseService;

    @PostMapping
    public ResponseEntity<FranchiseDTO> createFranchise(@Valid @RequestBody FranchiseDTO franchiseDTO) {
        FranchiseDTO saved = franchiseService.createFranchise(franchiseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<FranchiseDTO>> getAllFranchises() {
        List<FranchiseDTO> franchises = franchiseService.getAllFranchises();
        return ResponseEntity.ok(franchises);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FranchiseDTO> getFranchiseById(@PathVariable Long id) {
        FranchiseDTO franchise = franchiseService.getFranchiseById(id);
        return ResponseEntity.ok(franchise);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FranchiseDTO> updateFranchise(@PathVariable Long id, @Valid @RequestBody FranchiseDTO franchiseDTO) {
        FranchiseDTO updated = franchiseService.updateFranchise(id, franchiseDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFranchise(@PathVariable Long id) {
        franchiseService.deleteFranchise(id);
        return ResponseEntity.noContent().build();
    }
}