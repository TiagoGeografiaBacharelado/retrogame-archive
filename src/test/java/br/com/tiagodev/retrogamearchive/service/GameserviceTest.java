package br.com.tiagodev.retrogamearchive.service;

import br.com.tiagodev.retrogamearchive.domain.dto.GameDTO;
import br.com.tiagodev.retrogamearchive.domain.model.Game;
import br.com.tiagodev.retrogamearchive.exception.ResourceNotFoundException;
import br.com.tiagodev.retrogamearchive.repository.DeveloperRepository;
import br.com.tiagodev.retrogamearchive.repository.FranchiseRepository;
import br.com.tiagodev.retrogamearchive.repository.GameRepository;
import br.com.tiagodev.retrogamearchive.repository.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    private DeveloperRepository developerRepository;

    @Mock
    private PublisherRepository publisherRepository;

    @Mock
    private FranchiseRepository franchiseRepository;

    @InjectMocks
    private GameService gameService;

    private Game game;
    private GameDTO gameDTO;

    @BeforeEach
    void setUp() {
        game = Game.builder()
                .id(1L)
                .name("Sonic the Hedgehog")
                .releaseYear(1991)
                .description("Plataforma clássico da Sega")
                .numberOfPlayers(1)
                .build();

        gameDTO = GameDTO.builder()
                .name("Sonic the Hedgehog")
                .releaseYear(1991)
                .description("Plataforma clássico da Sega")
                .numberOfPlayers(1)
                .build();
    }

    // -------------------------
    // CREATE
    // -------------------------
    @Test
    void createGame_shouldReturnSavedGameDTO() {
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        GameDTO result = gameService.createGame(gameDTO);

        assertNotNull(result);
        assertEquals("Sonic the Hedgehog", result.getName());
        assertEquals(1991, result.getReleaseYear());
        verify(gameRepository, times(1)).save(any(Game.class));
    }

    // -------------------------
    // READ ALL
    // -------------------------
    @Test
    void getAllGames_shouldReturnPageOfGameDTOs() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Game> gamePage = new PageImpl<>(List.of(game));

        when(gameRepository.findAll(pageable)).thenReturn(gamePage);

        Page<GameDTO> result = gameService.getAllGames(null, null, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Sonic the Hedgehog", result.getContent().get(0).getName());
        verify(gameRepository, times(1)).findAll(pageable);
    }

    // -------------------------
    // READ ONE
    // -------------------------
    @Test
    void getGameById_shouldReturnGameDTO_whenGameExists() {
        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        GameDTO result = gameService.getGameById(1L);

        assertNotNull(result);
        assertEquals("Sonic the Hedgehog", result.getName());
        verify(gameRepository, times(1)).findById(1L);
    }

    @Test
    void getGameById_shouldThrowException_whenGameNotFound() {
        when(gameRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> gameService.getGameById(999L));

        verify(gameRepository, times(1)).findById(999L);
    }

    // -------------------------
    // DELETE
    // -------------------------
    @Test
    void deleteGame_shouldDeleteGame_whenGameExists() {
        when(gameRepository.existsById(1L)).thenReturn(true);
        doNothing().when(gameRepository).deleteById(1L);

        assertDoesNotThrow(() -> gameService.deleteGame(1L));

        verify(gameRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteGame_shouldThrowException_whenGameNotFound() {
        when(gameRepository.existsById(999L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> gameService.deleteGame(999L));

        verify(gameRepository, never()).deleteById(any());
    }

    // -------------------------
// UPDATE
// -------------------------
    @Test
    void updateGame_shouldReturnUpdatedGameDTO_whenGameExists() {
        GameDTO updateDTO = GameDTO.builder()
                .name("Sonic 2")
                .releaseYear(1992)
                .description("Sequência do Sonic")
                .numberOfPlayers(2)
                .build();

        Game updatedGame = Game.builder()
                .id(1L)
                .name("Sonic 2")
                .releaseYear(1992)
                .description("Sequência do Sonic")
                .numberOfPlayers(2)
                .build();

        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));
        when(gameRepository.save(any(Game.class))).thenReturn(updatedGame);

        GameDTO result = gameService.updateGame(1L, updateDTO);

        assertNotNull(result);
        assertEquals("Sonic 2", result.getName());
        assertEquals(1992, result.getReleaseYear());
        assertEquals(2, result.getNumberOfPlayers());
        verify(gameRepository, times(1)).findById(1L);
        verify(gameRepository, times(1)).save(any(Game.class));
    }

    @Test
    void updateGame_shouldThrowException_whenGameNotFound() {
        when(gameRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> gameService.updateGame(999L, gameDTO));

        verify(gameRepository, never()).save(any(Game.class));
    }

    // -------------------------
// CREATE com Developer
// -------------------------
    @Test
    void createGame_shouldThrowException_whenDeveloperNotFound() {
        GameDTO dtoWithInvalidDeveloper = GameDTO.builder()
                .name("Sonic")
                .releaseYear(1991)
                .developerId(999L)
                .build();

        when(developerRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> gameService.createGame(dtoWithInvalidDeveloper));

        verify(gameRepository, never()).save(any(Game.class));
    }
}