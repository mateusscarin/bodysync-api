package com.br.bodysync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.bodysync.model.dto.PersonalTrainerDTO;
import com.br.bodysync.service.PersonalTrainerService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/personal-trainer")
public class PersonalTrainerController {

        @Autowired
        private PersonalTrainerService personalTrainerService;

        @PostMapping
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = PersonalTrainerDTO.class)) }),
                        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
                        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
                        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
        })
        public ResponseEntity<Object> save(@RequestBody @Valid PersonalTrainerDTO object) throws Exception {
                return personalTrainerService.save(object);
        }

        @GetMapping
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = PersonalTrainerDTO.class)) }),
                        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
                        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
                        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
        })
        public ResponseEntity<Object> findAll() throws Exception {
                return personalTrainerService.findAll();
        }

        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = PersonalTrainerDTO.class)) }),
                        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
                        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
                        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
        })
        @GetMapping("/{email}")
        public ResponseEntity<Object> findById(@PathVariable("email") String email)
                        throws Exception {
                return personalTrainerService.findByEmail(email);
        }

        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = PersonalTrainerDTO.class)) }),
                        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
                        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
                        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
        })
        @PatchMapping("/{email}/status")
        public ResponseEntity<Object> changeStatus(@PathVariable("email") String email)
                        throws Exception {
                return personalTrainerService.changeStatus(email);
        }

        @PutMapping("/{email}")
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = PersonalTrainerDTO.class)) }),
                        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
                        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
                        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
        })
        public ResponseEntity<Object> edit(@PathVariable("email") String email,
                        @RequestBody @Valid PersonalTrainerDTO object) throws Exception {
                return personalTrainerService.edit(email, object);
        }

}
