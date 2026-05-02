package br.com.tiagodev.retrogamearchive.domain.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Min(value = 1970, message = "Release year must be 1970 or later")
    @Max(value = 2030, message = "Release year must be 2030 or earlier")
    private Integer releaseYear;

    private String description;

    @Min(value = 1, message = "Number of players must be at least 1")
    private Integer numberOfPlayers;

    private Long developerId;
    private Long publisherId;
    private Long franchiseId;
}