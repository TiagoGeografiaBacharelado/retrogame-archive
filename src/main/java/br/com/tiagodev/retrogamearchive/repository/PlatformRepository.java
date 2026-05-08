package br.com.tiagodev.retrogamearchive.repository;

import br.com.tiagodev.retrogamearchive.domain.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {}