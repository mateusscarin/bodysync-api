package com.br.bodysync.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.bodysync.model.PersonalTrainer;

public interface PersonalTrainerRepository extends JpaRepository<PersonalTrainer, Long> {

    boolean existsByEmail(String email);

    Optional<PersonalTrainer> findByEmail(String email);

    boolean existsByCpf(String cpf);

    Optional<PersonalTrainer> findByCpf(String cpf);

}
