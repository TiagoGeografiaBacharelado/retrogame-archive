package br.com.tiagodev.retrogamearchive.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "developers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}