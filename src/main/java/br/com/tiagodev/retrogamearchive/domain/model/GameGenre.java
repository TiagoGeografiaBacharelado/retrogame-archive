package br.com.tiagodev.retrogamearchive.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_genres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
}