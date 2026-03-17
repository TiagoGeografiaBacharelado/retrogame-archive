package br.com.tiagodev.retrogamearchive.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer releaseYear;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer numberOfPlayers;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Relacionamento com Developer
    @ManyToOne
    @JoinColumn(name = "developer_id")
    private Developer developer;

    // Relacionamento com Publisher
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    // Relacionamento com Franchise
    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}