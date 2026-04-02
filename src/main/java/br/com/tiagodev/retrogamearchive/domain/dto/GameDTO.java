package br.com.tiagodev.retrogamearchive.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDTO {
    private Long id;                 
    private String name;
    private Integer releaseYear;
    private String description;
    private Integer numberOfPlayers;

    private Long developerId;
    private Long publisherId;
    private Long franchiseId;
}