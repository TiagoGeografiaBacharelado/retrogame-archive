package br.com.tiagodev.retrogamearchive.repository;

import br.com.tiagodev.retrogamearchive.domain.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Page<Game> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Game> findByReleaseYear(Integer releaseYear, Pageable pageable);

    Page<Game> findByNameContainingIgnoreCaseAndReleaseYear(String name, Integer releaseYear, Pageable pageable);
}