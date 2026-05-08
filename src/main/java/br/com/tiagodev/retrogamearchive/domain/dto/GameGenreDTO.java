package br.com.tiagodev.retrogamearchive.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameGenreDTO {
    private Long id;

    @NotNull(message = "Game id is required")
    private Long gameId;

    @NotNull(message = "Genre id is required")
    private Long genreId;
}