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

import com.br.bodysync.model.dto.TraineeDTO;
import com.br.bodysync.service.TraineeService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/trainee")
public class TraineeController {

        @Autowired
        private TraineeService traineeService;

        @PostMapping
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = TraineeDTO.class)) }),
                        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
                        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
                        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
        })
        public ResponseEntity<Object> save(@RequestBody @Valid TraineeDTO object) throws Exception {
                return traineeService.save(object);
        }

        @GetMapping
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = TraineeDTO.class)) }),
                        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
                        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
                        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
        })
        public ResponseEntity<Object> findAll() throws Exception {
                return traineeService.findAll();
        }

        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = TraineeDTO.class)) }),
                        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
                        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
                        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
        })
        @GetMapping("/{email}")
        public ResponseEntity<Object> findById(@PathVariable("email") String email)
                        throws Exception {
                return traineeService.findByEmail(email);
        }

        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = TraineeDTO.class)) }),
                        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
                        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
                        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
        })
        @PatchMapping("/{email}/status")
        public ResponseEntity<Object> changeStatus(@PathVariable("email") String email)
                        throws Exception {
                return traineeService.changeStatus(email);
        }

        @PutMapping("/{email}")
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = TraineeDTO.class)) }),
                        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
                        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
                        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
        })
        public ResponseEntity<Object> edit(@PathVariable("email") String email,
                        @RequestBody @Valid TraineeDTO object) throws Exception {
                return traineeService.edit(email, object);
        }

}
