package br.com.tiagodev.retrogamearchive.service;

import br.com.tiagodev.retrogamearchive.domain.dto.GameGenreDTO;
import br.com.tiagodev.retrogamearchive.domain.model.GameGenre;
import br.com.tiagodev.retrogamearchive.exception.ResourceNotFoundException;
import br.com.tiagodev.retrogamearchive.repository.GameGenreRepository;
import br.com.tiagodev.retrogamearchive.repository.GameRepository;
import br.com.tiagodev.retrogamearchive.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameGenreService {

    private final GameGenreRepository gameGenreRepository;
    private final GameRepository gameRepository;
    private final GenreRepository genreRepository;

    public GameGenreDTO createGameGenre(GameGenreDTO dto) {
        GameGenre gameGenre = toEntity(dto);
        GameGenre saved = gameGenreRepository.save(gameGenre);
        return toDTO(saved);
    }

    public List<GameGenreDTO> getAllGameGenres() {
        return gameGenreRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public GameGenreDTO getGameGenreById(Long id) {
        GameGenre gameGenre = gameGenreRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("GameGenre not found with id: " + id)
                );
        return toDTO(gameGenre);
    }

    public void deleteGameGenre(Long id) {
        if (!gameGenreRepository.existsById(id)) {
            throw new ResourceNotFoundException("GameGenre not found with id: " + id);
        }
        gameGenreRepository.deleteById(id);
    }

    private GameGenreDTO toDTO(GameGenre gameGenre) {
        return GameGenreDTO.builder()
                .id(gameGenre.getId())
                .gameId(gameGenre.getGame().getId())
                .genreId(gameGenre.getGenre().getId())
                .build();
    }

    private GameGenre toEntity(GameGenreDTO dto) {
        return GameGenre.builder()
                .game(gameRepository.findById(dto.getGameId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Game not found with id: " + dto.getGameId())
                        ))
                .genre(genreRepository.findById(dto.getGenreId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Genre not found with id: " + dto.getGenreId())
                        ))
                .build();
    }
}