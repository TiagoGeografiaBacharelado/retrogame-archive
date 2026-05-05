package br.com.tiagodev.retrogamearchive.service;

import br.com.tiagodev.retrogamearchive.domain.dto.FranchiseDTO;
import br.com.tiagodev.retrogamearchive.domain.model.Franchise;
import br.com.tiagodev.retrogamearchive.exception.ResourceNotFoundException;
import br.com.tiagodev.retrogamearchive.repository.FranchiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FranchiseService {

    private final FranchiseRepository franchiseRepository;

    public FranchiseDTO createFranchise(FranchiseDTO dto) {
        Franchise franchise = toEntity(dto);
        Franchise saved = franchiseRepository.save(franchise);
        return toDTO(saved);
    }

    public List<FranchiseDTO> getAllFranchises() {
        return franchiseRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public FranchiseDTO getFranchiseById(Long id) {
        Franchise franchise = franchiseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Franchise not found with id: " + id)
                );
        return toDTO(franchise);
    }

    public FranchiseDTO updateFranchise(Long id, FranchiseDTO dto) {
        Franchise existing = franchiseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Franchise not found with id: " + id)
                );
        existing.setName(dto.getName());
        Franchise updated = franchiseRepository.save(existing);
        return toDTO(updated);
    }

    public void deleteFranchise(Long id) {
        if (!franchiseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Franchise not found with id: " + id);
        }
        franchiseRepository.deleteById(id);
    }

    private FranchiseDTO toDTO(Franchise franchise) {
        return FranchiseDTO.builder()
                .id(franchise.getId())
                .name(franchise.getName())
                .build();
    }

    private Franchise toEntity(FranchiseDTO dto) {
        return Franchise.builder()
                .name(dto.getName())
                .build();
    }
}