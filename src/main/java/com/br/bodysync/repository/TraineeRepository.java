package com.br.bodysync.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.bodysync.model.Trainee;

public interface TraineeRepository extends JpaRepository<Trainee, Long> {

    boolean existsByEmail(String email);

    Optional<Trainee> findByEmail(String email);

    boolean existsByCpf(String cpf);

    Optional<Trainee> findByCpf(String cpf);

}
