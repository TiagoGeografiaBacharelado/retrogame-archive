package br.com.tiagodev.retrogamearchive.service;

import br.com.tiagodev.retrogamearchive.domain.dto.GenreDTO;
import br.com.tiagodev.retrogamearchive.domain.model.Genre;
import br.com.tiagodev.retrogamearchive.exception.ResourceNotFoundException;
import br.com.tiagodev.retrogamearchive.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreDTO createGenre(GenreDTO dto) {
        Genre genre = toEntity(dto);
        Genre saved = genreRepository.save(genre);
        return toDTO(saved);
    }

    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public GenreDTO getGenreById(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("genre not found with id: " + id)
                );
        return toDTO(genre);
    }

    public GenreDTO updateGenre(Long id, GenreDTO dto) {
        Genre existing = genreRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("genre not found with id: " + id)
                );
        existing.setName(dto.getName());
        Genre updated = genreRepository.save(existing);
        return toDTO(updated);
    }

    public void deleteGenre(Long id) {
        if (!genreRepository.existsById(id)) {
            throw new ResourceNotFoundException("genre not found with id: " + id);
        }
        genreRepository.deleteById(id);
    }

    private GenreDTO toDTO(Genre genre) {
        return GenreDTO.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }

    private Genre toEntity(GenreDTO dto) {
        return Genre.builder()
                .name(dto.getName())
                .build();
    }
}
