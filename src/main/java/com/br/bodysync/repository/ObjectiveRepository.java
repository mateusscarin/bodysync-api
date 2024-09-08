package com.br.bodysync.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.bodysync.model.Objective;

@Repository
public interface ObjectiveRepository extends JpaRepository<Objective, Long> {

    boolean existsByName(String name);

    Optional<Objective> findByName(String name);

}
