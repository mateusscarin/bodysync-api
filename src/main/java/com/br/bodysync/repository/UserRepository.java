package com.br.bodysync.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.bodysync.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
