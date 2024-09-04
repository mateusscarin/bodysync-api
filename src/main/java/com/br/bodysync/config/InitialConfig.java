package com.br.bodysync.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.br.bodysync.model.Administrator;
import com.br.bodysync.repository.AdministratorRepository;

@Configuration
public class InitialConfig {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @EventListener
    public void appReady(ApplicationReadyEvent event) throws Exception {

        Administrator administrador = new Administrator();
        administrador.setBirthDate(LocalDate.now());
        administrador.setBirthDate(LocalDate.now());
        administrador.setCpf("08854272035");
        administrador.setEmail("admin@bodysync.com.br");
        administrador.setPassword(passwordEncoder.encode("123Mudar*"));
        administrador.setFullName("Admin");
        administrador.setSex("Masculino");

        administratorRepository.save(administrador);
    }

}
