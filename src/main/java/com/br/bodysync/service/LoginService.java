package com.br.bodysync.service;

import org.springframework.http.ResponseEntity;

import com.br.bodysync.model.dto.LoginDTO;

public interface LoginService {

    public ResponseEntity<Object> login(LoginDTO dto);

}
