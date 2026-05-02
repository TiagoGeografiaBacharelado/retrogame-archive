package br.com.tiagodev.retrogamearchive.repository;

import br.com.tiagodev.retrogamearchive.domain.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {}