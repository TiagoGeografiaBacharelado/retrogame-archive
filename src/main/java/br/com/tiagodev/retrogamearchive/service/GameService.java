package br.com.tiagodev.retrogamearchive.service;

import br.com.tiagodev.retrogamearchive.domain.dto.GameDTO;
import br.com.tiagodev.retrogamearchive.domain.model.Game;
import br.com.tiagodev.retrogamearchive.exception.ResourceNotFoundException;
import br.com.tiagodev.retrogamearchive.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

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
    public List<GameDTO> getAllGames() {
        return gameRepository.findAll()       // 1. busca todos do banco
                .stream()                     // 2. transforma em stream
                .map(this::toDTO)             // 3. converte cada Game → GameDTO
                .collect(Collectors.toList()); // 4. coleta como List
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
    private Game toEntity(GameDTO dto) {
        return Game.builder()
                .name(dto.getName())
                .releaseYear(dto.getReleaseYear())
                .description(dto.getDescription())
                .numberOfPlayers(dto.getNumberOfPlayers())
                // developer, publisher, franchise → mapear depois
                // quando essas entidades estiverem prontas
                .build();
    }

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
}