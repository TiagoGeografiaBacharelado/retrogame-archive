package br.com.tiagodev.retrogamearchive.service;

import br.com.tiagodev.retrogamearchive.domain.dto.GameDTO;
import br.com.tiagodev.retrogamearchive.domain.model.Game;
import br.com.tiagodev.retrogamearchive.exception.ResourceNotFoundException;
import br.com.tiagodev.retrogamearchive.repository.DeveloperRepository;
import br.com.tiagodev.retrogamearchive.repository.FranchiseRepository;
import br.com.tiagodev.retrogamearchive.repository.GameRepository;
import br.com.tiagodev.retrogamearchive.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final DeveloperRepository developerRepository;
    private final PublisherRepository publisherRepository;
    private final FranchiseRepository franchiseRepository;

    // -------------------------
    // CREATE
    // -------------------------
    public GameDTO createGame(GameDTO dto) {
        Game game = toEntity(dto);           // 1. converte DTO → Entity
        Game saved = gameRepository.save(game); // 2. salva no banco
        return toDTO(saved);                 // 3. converte Entity → DTO e retorna
    }

    // -------------------------
    // READ ALL
    // -------------------------
    public Page<GameDTO> getAllGames(String name, Integer releaseYear, Pageable pageable) {

        if (name != null && releaseYear != null) {
            return gameRepository.findByNameContainingIgnoreCaseAndReleaseYear(name, releaseYear, pageable)
                    .map(this::toDTO);
        }

        if (name != null) {
            return gameRepository.findByNameContainingIgnoreCase(name, pageable)
                    .map(this::toDTO);
        }

        if (releaseYear != null) {
            return gameRepository.findByReleaseYear(releaseYear, pageable)
                    .map(this::toDTO);
        }

        return gameRepository.findAll(pageable)
                .map(this::toDTO);
    }

    // -------------------------
    // READ ONE
    // -------------------------
    public GameDTO getGameById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Game not found with id: " + id)
                );
        return toDTO(game);
    }

    // -------------------------
    // UPDATE
    // -------------------------
    public GameDTO updateGame(Long id, GameDTO dto) {
        Game existing = gameRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Game not found with id: " + id)
                );

        // atualiza só os campos que vieram no DTO
        existing.setName(dto.getName());
        existing.setReleaseYear(dto.getReleaseYear());
        existing.setDescription(dto.getDescription());
        existing.setNumberOfPlayers(dto.getNumberOfPlayers());

        Game updated = gameRepository.save(existing); // salva as alterações
        return toDTO(updated);
    }

    // -------------------------
    // DELETE
    // -------------------------
    public void deleteGame(Long id) {
        if (!gameRepository.existsById(id)) {
            throw new ResourceNotFoundException("Game not found with id: " + id);
        }
        gameRepository.deleteById(id);
    }

    // -------------------------
    // Conversores privados
    // -------------------------
    private GameDTO toDTO(Game game) {
        return GameDTO.builder()
                .id(game.getId())
                .name(game.getName())
                .releaseYear(game.getReleaseYear())
                .description(game.getDescription())
                .numberOfPlayers(game.getNumberOfPlayers())
                .developerId(game.getDeveloper() != null ? game.getDeveloper().getId() : null)
                .publisherId(game.getPublisher() != null ? game.getPublisher().getId() : null)
                .franchiseId(game.getFranchise() != null ? game.getFranchise().getId() : null)
                .build();
    }

    private Game toEntity(GameDTO dto) {
        return Game.builder()
                .name(dto.getName())
                .releaseYear(dto.getReleaseYear())
                .description(dto.getDescription())
                .numberOfPlayers(dto.getNumberOfPlayers())
                .developer(
                        dto.getDeveloperId() != null
                                ? developerRepository.findById(dto.getDeveloperId())
                                .orElseThrow(() -> new ResourceNotFoundException("Developer not found with id: " + dto.getDeveloperId()))
                                : null
                )
                .publisher(
                        dto.getPublisherId() != null
                                ? publisherRepository.findById(dto.getPublisherId())
                                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found with id: " + dto.getPublisherId()))
                                : null
                )
                .franchise(
                        dto.getFranchiseId() != null
                                ? franchiseRepository.findById(dto.getFranchiseId())
                                .orElseThrow(() -> new ResourceNotFoundException("Franchise not found with id: " + dto.getFranchiseId()))
                                : null
                )
                .build();
    }
}