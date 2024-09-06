package com.br.bodysync.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.bodysync.model.Question;
import com.br.bodysync.model.dto.QuestionDTO;
import com.br.bodysync.model.mapper.CustomObjectMapper;
import com.br.bodysync.repository.QuestionRepository;
import com.br.bodysync.service.QuestionService;
import com.br.bodysync.service.util.ApiResponse;

/**
 *
 * @author Carlos
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionsRepository;

    @Autowired
    private CustomObjectMapper<Question, QuestionDTO> questionsMapper;

    @Override
    public ResponseEntity<Object> save(QuestionDTO objectDTO) throws Exception {

        if (questionsRepository.existsByDescription(objectDTO.getDescription())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(
                    "Não é possivel cadastrar o Pergunta. Já existe outra Pergunta com o mesmo nome."));
        }

        Question questions = new Question();
        questions.setDescription(objectDTO.getDescription());

        questions.setStatus(objectDTO.isStatus());

        Question objetoCriado = questionsRepository.saveAndFlush(questions);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(objetoCriado.getId())
                        .toUri())
                .build();
    }

    @Override
    public ResponseEntity<Object> edit(Long idObject, QuestionDTO object) throws Exception {

        Question dadosDto = questionsMapper.convertToEntity(object);
        Question paraEditar = questionsRepository.findById(idObject)
                .orElseThrow(
                        () -> new NoSuchElementException("A Pergunta com ID " + idObject + " não foi encontrada!"));

        if (questionsRepository.existsByDescription(object.getDescription())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(
                    "Não é possivel cadastrar a Pergunta. Já existe outra Pergunta com o mesmo nome."));
        }

        LocalDateTime dataCriacaoObjeto = paraEditar.getCreatedDate();
        boolean statusObjeto = paraEditar.isStatus();

        dadosDto.setCreatedDate(dataCriacaoObjeto);
        dadosDto.setStatus(statusObjeto);
        dadosDto.setUpdateDate(LocalDateTime.now());
        dadosDto.setId(idObject);
        BeanUtils.copyProperties(dadosDto, paraEditar, "id", "createdDate", "status");
        Question objetoAtualizado = questionsRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(questionsMapper.convertToDto(objetoAtualizado)));
    }

    @Override
    public ResponseEntity<Object> findById(Long idObject) throws Exception {

        Question questions = questionsRepository.findById(idObject)
                .orElseThrow(
                        () -> new NoSuchElementException("A pergunta com ID " + idObject + " não foi encontrada!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(questionsMapper.convertToDto(questions)));
    }

    @Override
    public ResponseEntity<Object> findAll() throws Exception {

        List<QuestionDTO> questionsDTOs = questionsMapper.convertToDtoList(questionsRepository.findAll());
        if (questionsDTOs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Não existe Perguntas cadastrados no Sistemas"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(questionsDTOs));
    }

    @Override
    public ResponseEntity<Object> delete(Long idObject) throws Exception {

        questionsRepository.findById(idObject)
                .orElseThrow(
                        () -> new NoSuchElementException("A Pergunta com ID " + idObject + " não foi encontrada!"));

        questionsRepository.deleteById(idObject);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("A Pergunta foi excluída com sucesso."));

    }

    @Override
    public ResponseEntity<Object> changeStatus(Long idObject) throws Exception {

        Question objeto = questionsRepository.findById(idObject)
                .orElseThrow(
                        () -> new NoSuchElementException("A Pergunta com ID " + idObject + " não foi encontrada!"));

        objeto.setStatus(!objeto.isStatus());
        objeto.setUpdateDate(LocalDateTime.now());
        Question objetoAtualizado = questionsRepository.saveAndFlush(objeto);
        QuestionDTO objetoDTO = questionsMapper.convertToDto(objetoAtualizado);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objetoDTO));
    }

}
