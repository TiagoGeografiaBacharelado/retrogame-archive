package br.com.tiagodev.retrogamearchive.repository;

import br.com.tiagodev.retrogamearchive.domain.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {}
