package br.com.tiagodev.retrogamearchive.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_platforms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GamePlatform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;
}