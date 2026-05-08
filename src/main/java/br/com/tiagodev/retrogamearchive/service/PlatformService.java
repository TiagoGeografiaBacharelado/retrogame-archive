package br.com.tiagodev.retrogamearchive.service;

import br.com.tiagodev.retrogamearchive.domain.dto.PlatformDTO;
import br.com.tiagodev.retrogamearchive.domain.model.Platform;
import br.com.tiagodev.retrogamearchive.exception.ResourceNotFoundException;
import br.com.tiagodev.retrogamearchive.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlatformService {

    private final PlatformRepository platformRepository;

    public PlatformDTO createPlatform(PlatformDTO dto) {
        Platform platform = toEntity(dto);
        Platform saved = platformRepository.save(platform);
        return toDTO(saved);
    }

    public List<PlatformDTO> getAllPlatforms() {
        return platformRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PlatformDTO getPlatformById(Long id) {
        Platform platform = platformRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Platform not found with id: " + id)
                );
        return toDTO(platform);
    }

    public PlatformDTO updatePlatform(Long id, PlatformDTO dto) {
        Platform existing = platformRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Platform not found with id: " + id)
                );
        existing.setName(dto.getName());
        Platform updated = platformRepository.save(existing);
        return toDTO(updated);
    }

    public void deletePlatform(Long id) {
        if (!platformRepository.existsById(id)) {
            throw new ResourceNotFoundException("Platform not found with id: " + id);
        }
        platformRepository.deleteById(id);
    }

    private PlatformDTO toDTO(Platform Platform) {
        return PlatformDTO.builder()
                .id(Platform.getId())
                .name(Platform.getName())
                .build();
    }

    private Platform toEntity(PlatformDTO dto) {
        return Platform.builder()
                .name(dto.getName())
                .build();
    }
}

