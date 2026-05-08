package br.com.tiagodev.retrogamearchive.service;

import br.com.tiagodev.retrogamearchive.domain.dto.GamePlatformDTO;
import br.com.tiagodev.retrogamearchive.domain.model.GamePlatform;
import br.com.tiagodev.retrogamearchive.exception.ResourceNotFoundException;
import br.com.tiagodev.retrogamearchive.repository.GamePlatformRepository;
import br.com.tiagodev.retrogamearchive.repository.GameRepository;
import br.com.tiagodev.retrogamearchive.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GamePlatformService {

    private final GamePlatformRepository gamePlatformRepository;
    private final GameRepository gameRepository;
    private final PlatformRepository platformRepository;

    public GamePlatformDTO createGamePlatform(GamePlatformDTO dto) {
        GamePlatform gamePlatform = toEntity(dto);
        GamePlatform saved = gamePlatformRepository.save(gamePlatform);
        return toDTO(saved);
    }

    public List<GamePlatformDTO> getAllGamePlatforms() {
        return gamePlatformRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public GamePlatformDTO getGamePlatformById(Long id) {
        GamePlatform gamePlatform = gamePlatformRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("GamePlatform not found with id: " + id)
                );
        return toDTO(gamePlatform);
    }

    public void deleteGamePlatform(Long id) {
        if (!gamePlatformRepository.existsById(id)) {
            throw new ResourceNotFoundException("GamePlatform not found with id: " + id);
        }
        gamePlatformRepository.deleteById(id);
    }

    private GamePlatformDTO toDTO(GamePlatform gamePlatform) {
        return GamePlatformDTO.builder()
                .id(gamePlatform.getId())
                .gameId(gamePlatform.getGame().getId())
                .platformId(gamePlatform.getPlatform().getId())
                .build();
    }

    private GamePlatform toEntity(GamePlatformDTO dto) {
        return GamePlatform.builder()
                .game(gameRepository.findById(dto.getGameId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Game not found with id: " + dto.getGameId())
                        ))
                .platform(platformRepository.findById(dto.getPlatformId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Platform not found with id: " + dto.getPlatformId())
                        ))
                .build();
    }
}