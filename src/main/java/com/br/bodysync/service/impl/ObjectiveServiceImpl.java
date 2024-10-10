package com.br.bodysync.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.bodysync.model.Objective;
import com.br.bodysync.model.dto.ObjectiveDTO;
import com.br.bodysync.model.mapper.CustomObjectMapper;
import com.br.bodysync.repository.ObjectiveRepository;
import com.br.bodysync.service.ObjectiveService;
import com.br.bodysync.service.util.ApiResponse;

@Service
public class ObjectiveServiceImpl implements ObjectiveService {

        @Autowired
        private ObjectiveRepository objectiveRepository;

        @Autowired
        private CustomObjectMapper<Objective, ObjectiveDTO> objectiveMapper;

        @Override
        public ResponseEntity<Object> save(ObjectiveDTO dto) throws Exception {
                if (objectiveRepository.existsByName(dto.getName())) {
                        return ResponseEntity.badRequest().body(new ApiResponse<>(
                                        "Não é possivel cadastrar objetivo. Já existe outra objetivo com o mesmo nome."));
                }
                Objective result = objectiveRepository.saveAndFlush(objectiveMapper.convertToEntity(dto));
                return ResponseEntity.created(
                                ServletUriComponentsBuilder
                                                .fromCurrentRequest()
                                                .path("/{id}")
                                                .buildAndExpand(result.getId())
                                                .toUri())
                                .build();
        }

        @Override
        public ResponseEntity<Object> edit(Long idObject, ObjectiveDTO object) throws Exception {
                Objective sourceData = objectiveMapper.convertToEntity(object);
                Objective toEdit = objectiveRepository.findById(idObject)
                                .orElseThrow(() -> new NoSuchElementException("O objetivo com ID " + idObject
                                                + " não foi encontrado!"));
                Optional<Objective> optObjective = objectiveRepository.findByName(object.getName());
                if (optObjective.isPresent() && !(optObjective.get().getId().equals(idObject))) {
                        return ResponseEntity.badRequest().body(new ApiResponse<>(
                                        "Não é possivel salvar objetivo. Já existe outro objetivo com o mesmo nome."));
                }
                BeanUtils.copyProperties(sourceData, toEdit, "id", "createdDate", "status");
                sourceData.setId(idObject);
                Objective result = objectiveRepository.saveAndFlush(toEdit);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(new ApiResponse<>(objectiveMapper.convertToDto(result)));
        }

        @Override
        public ResponseEntity<Object> findById(Long idObject) throws Exception {
                Objective objective = objectiveRepository.findById(idObject)
                                .orElseThrow(() -> new NoSuchElementException(
                                                "O objetivo com ID " + idObject + " não foi encontrado!"));
                return ResponseEntity.status(HttpStatus.OK)
                                .body(new ApiResponse<>(objectiveMapper.convertToDto(objective)));
        }

        @Override
        public ResponseEntity<Object> findAll() throws Exception {

                List<ObjectiveDTO> objectiveDTOs = objectiveMapper.convertToDtoList(objectiveRepository.findAll());
                if (objectiveDTOs.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                        .body(new ApiResponse<>("Não existe objetivos cadastrados no sistema"));
                }
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(objectiveDTOs));
        }

        @Override
        public ResponseEntity<Object> delete(Long idObject) throws Exception {
                objectiveRepository.findById(idObject)
                                .orElseThrow(
                                                () -> new NoSuchElementException("O objetivo com ID " + idObject
                                                                + " não foi encontrado!"));
                objectiveRepository.deleteById(idObject);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(new ApiResponse<>("O objetivo foi excluído com sucesso."));
        }

        @Override
        public ResponseEntity<Object> changeStatus(Long idObject) throws Exception {
                Objective objeto = objectiveRepository.findById(idObject)
                                .orElseThrow(
                                                () -> new NoSuchElementException("O objetivo com ID " + idObject
                                                                + " não foi encontrado!"));
                objeto.setStatus(!objeto.isStatus());
                objeto.setUpdateDate(LocalDateTime.now());
                Objective objetoAtualizado = objectiveRepository.saveAndFlush(objeto);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(new ApiResponse<>(objectiveMapper.convertToDto(objetoAtualizado)));
        }

}
