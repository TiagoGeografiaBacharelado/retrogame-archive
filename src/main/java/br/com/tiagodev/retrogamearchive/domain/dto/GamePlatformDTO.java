package br.com.tiagodev.retrogamearchive.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GamePlatformDTO {
    private Long id;

    @NotNull(message = "Game id is required")
    private Long gameId;

    @NotNull(message = "Platform id is required")
    private Long platformId;
}