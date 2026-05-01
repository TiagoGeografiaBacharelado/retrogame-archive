package br.com.tiagodev.retrogamearchive.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "franchises")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}