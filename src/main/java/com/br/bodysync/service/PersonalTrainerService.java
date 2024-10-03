package com.br.bodysync.service;

import org.springframework.http.ResponseEntity;

import com.br.bodysync.model.dto.PersonalTrainerDTO;

public interface PersonalTrainerService {

    ResponseEntity<Object> save(PersonalTrainerDTO userDTO) throws Exception;

    ResponseEntity<Object> edit(String email, PersonalTrainerDTO userDTO) throws Exception;

    ResponseEntity<Object> changeStatus(String email) throws Exception;

    ResponseEntity<Object> findByEmail(String email) throws Exception;

    ResponseEntity<Object> findAll() throws Exception;

}
