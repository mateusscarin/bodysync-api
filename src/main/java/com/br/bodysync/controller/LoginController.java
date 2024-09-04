package com.br.bodysync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.bodysync.model.dto.LoginDTO;
import com.br.bodysync.service.LoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class LoginController {
    
    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/token")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO dto) {
        return loginService.login(dto);
    }
    
}
