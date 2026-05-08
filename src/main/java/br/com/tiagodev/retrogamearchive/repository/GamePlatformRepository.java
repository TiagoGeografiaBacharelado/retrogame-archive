package br.com.tiagodev.retrogamearchive.repository;

import br.com.tiagodev.retrogamearchive.domain.model.GamePlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePlatformRepository extends JpaRepository<GamePlatform, Long> {}