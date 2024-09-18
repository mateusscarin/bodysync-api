package com.br.bodysync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.bodysync.model.dto.UserDTO;
import com.br.bodysync.service.PersonalTrainerService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 *
 * @author Carlos
 */
@RestController
@RequestMapping(value = "/personal-trainer")
public class PersonalTrainerController {

        @Autowired
        private PersonalTrainerService questionsService;

        @PostMapping
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
                        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
                        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
                        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
        })
        public ResponseEntity<Object> save(@RequestBody UserDTO object) throws Exception {
                return questionsService.save(object);
        }

        @GetMapping
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
                        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
                        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
                        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
        })
        public ResponseEntity<Object> findAll() throws Exception {
                return questionsService.findAll();
        }

        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
                        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
                        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
                        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
        })
        @GetMapping("/{idObjeto}")
        public ResponseEntity<Object> findById(@PathVariable("idObjeto") Long idObject)
                        throws Exception {
                return questionsService.findById(idObject);
        }

        @PutMapping("/{idObjeto}")
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
                        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
                        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
                        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
        })
        public ResponseEntity<Object> edit(@PathVariable("idObjeto") Long idObject, @RequestBody UserDTO object)
                        throws Exception {
                return questionsService.edit(idObject, object);
        }

        @DeleteMapping("/{idObjeto}")
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Criado (Created)", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
                        @ApiResponse(responseCode = "401", description = "Não Autorizado (Unauthorized)"),
                        @ApiResponse(responseCode = "404", description = "Não Encontrado (Not Found)"),
                        @ApiResponse(responseCode = "500", description = "Erro interno (Internal Server Error)")
        })
        public ResponseEntity<Object> delete(@PathVariable("idObjeto") Long idObject) throws Exception {
                return questionsService.delete(idObject);
        }

}
