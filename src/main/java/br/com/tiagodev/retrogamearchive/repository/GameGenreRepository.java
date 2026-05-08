package br.com.tiagodev.retrogamearchive.repository;

import br.com.tiagodev.retrogamearchive.domain.model.GameGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameGenreRepository extends JpaRepository<GameGenre, Long> {}