/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.bodysync.service.impl;

import com.br.bodysync.model.Questions;
import com.br.bodysync.model.dto.QuestionsDTO;
import com.br.bodysync.model.mapper.CustomObjectMapper;
import com.br.bodysync.repository.QuestionsRepository;
import com.br.bodysync.service.QuestionsService;
import com.br.bodysync.service.util.ApiResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Carlos
 */
@Service
public class QuestionsServiceImpl implements QuestionsService {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private CustomObjectMapper<Questions, QuestionsDTO> questionsMapper;

    @Override
    public ResponseEntity<Object> save(QuestionsDTO objectDTO) throws Exception {

        if (questionsRepository.existsByDescription(objectDTO.getDescription())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar o Pergunta. Já existe outro Pergunta com o mesmo nome."));
        }

        Questions questions = new Questions();
        questions.setDescription(objectDTO.getDescription());

        questions.setStatus(objectDTO.isStatus());

        Questions objetoCriado = questionsRepository.saveAndFlush(questions);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(objetoCriado.getId())
                        .toUri())
                .build();
    }

    @Override
    public ResponseEntity<Object> edit(Long idObject, QuestionsDTO object) throws Exception {

        Questions dadosDto = questionsMapper.convertToEntity(object);
        Questions paraEditar = questionsRepository.findById(idObject)
                .orElseThrow(() -> new NoSuchElementException("A Pergunta com ID " + idObject + " não foi encontrada!"));

        if (questionsRepository.existsByDescription(object.getDescription())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar a Pergunta. Já existe outro Pergunta com o mesmo nome."));
        }

        LocalDateTime dataCriacaoObjeto = paraEditar.getCreatedDate();
        boolean statusObjeto = paraEditar.isStatus();

        dadosDto.setCreatedDate(dataCriacaoObjeto);
        dadosDto.setStatus(statusObjeto);
        dadosDto.setUpdateDate(LocalDateTime.now());
        dadosDto.setId(idObject);
        BeanUtils.copyProperties(dadosDto, paraEditar, "id", "CreatedDate", "status");
        Questions objetoAtualizado = questionsRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(questionsMapper.convertToDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> findById(Long idObject) throws Exception {

        Questions questions = questionsRepository.findById(idObject)
                .orElseThrow(() -> new NoSuchElementException("A pergunta com ID " + idObject + " não foi encontrada!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(questionsMapper.convertToDto(questions)));
    }

    @Override
    public ResponseEntity<Object> findAll() throws Exception {

        List<QuestionsDTO> questionsDTOs = questionsMapper.convertToDtoList(questionsRepository.findAll());
        if (questionsDTOs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Perguntas cadastrados no Sistemas"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(questionsDTOs));
    }

    @Override
    public ResponseEntity<Object> delete(Long idObject) throws Exception {

        questionsRepository.findById(idObject)
                .orElseThrow(() -> new NoSuchElementException("A Pergunta com ID " + idObject + " não foi encontrada!"));

        questionsRepository.deleteById(idObject);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("A Pergunta foi excluída com sucesso."));

    }

    @Override
    public ResponseEntity<Object> changeStatus(Long idObject) throws Exception {

        Questions objeto = questionsRepository.findById(idObject)
                .orElseThrow(() -> new NoSuchElementException("A Pergunta com ID " + idObject + " não foi encontrada!"));

        objeto.setStatus(!objeto.isStatus());
        objeto.setUpdateDate(LocalDateTime.now());
        Questions objetoAtualizado = questionsRepository.saveAndFlush(objeto);
        QuestionsDTO objetoDTO =  questionsMapper.convertToDto(objetoAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));
    }

}
