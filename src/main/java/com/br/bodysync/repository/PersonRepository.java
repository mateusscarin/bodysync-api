package com.br.bodysync.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.bodysync.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);
}
