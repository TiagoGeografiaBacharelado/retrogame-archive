package br.com.tiagodev.retrogamearchive.service;

import br.com.tiagodev.retrogamearchive.domain.dto.DeveloperDTO;
import br.com.tiagodev.retrogamearchive.domain.model.Developer;
import br.com.tiagodev.retrogamearchive.exception.ResourceNotFoundException;
import br.com.tiagodev.retrogamearchive.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperDTO createDeveloper(DeveloperDTO dto) {
        Developer developer = toEntity(dto);
        Developer saved = developerRepository.save(developer);
        return toDTO(saved);
    }

    public List<DeveloperDTO> getAllDevelopers() {
        return developerRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public DeveloperDTO getDeveloperById(Long id) {
        Developer developer = developerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Developer not found with id: " + id)
                );
        return toDTO(developer);
    }

    public DeveloperDTO updateDeveloper(Long id, DeveloperDTO dto) {
        Developer existing = developerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Developer not found with id: " + id)
                );
        existing.setName(dto.getName());
        Developer updated = developerRepository.save(existing);
        return toDTO(updated);
    }

    public void deleteDeveloper(Long id) {
        if (!developerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Developer not found with id: " + id);
        }
        developerRepository.deleteById(id);
    }

    private DeveloperDTO toDTO(Developer developer) {
        return DeveloperDTO.builder()
                .id(developer.getId())
                .name(developer.getName())
                .build();
    }

    private Developer toEntity(DeveloperDTO dto) {
        return Developer.builder()
                .name(dto.getName())
                .build();
    }
}