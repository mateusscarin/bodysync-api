package com.br.bodysync.service;

import org.springframework.http.ResponseEntity;

import com.br.bodysync.model.dto.TraineeDTO;

public interface TraineeService {

    ResponseEntity<Object> save(TraineeDTO userDTO) throws Exception;

    ResponseEntity<Object> edit(String email, TraineeDTO userDTO) throws Exception;

    ResponseEntity<Object> changeStatus(String email) throws Exception;

    ResponseEntity<Object> findByEmail(String email) throws Exception;

    ResponseEntity<Object> findAll() throws Exception;

}
