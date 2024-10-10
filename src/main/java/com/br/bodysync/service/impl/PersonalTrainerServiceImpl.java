package com.br.bodysync.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.bodysync.model.PersonalTrainer;
import com.br.bodysync.model.dto.PersonalTrainerDTO;
import com.br.bodysync.model.mapper.CustomObjectMapper;
import com.br.bodysync.repository.PersonalTrainerRepository;
import com.br.bodysync.service.PersonalTrainerService;
import com.br.bodysync.service.util.ApiResponse;

@Service
public class PersonalTrainerServiceImpl implements PersonalTrainerService {

        @Autowired
        private PersonalTrainerRepository personalTrainerRepository;

        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        @Autowired
        private CustomObjectMapper<PersonalTrainer, PersonalTrainerDTO> customObjectMapper;

        @Override
        public ResponseEntity<Object> save(PersonalTrainerDTO dto) throws Exception {
                if (personalTrainerRepository.existsByEmail(dto.email())) {
                        return ResponseEntity.badRequest().body(new ApiResponse<>(
                                        "Não é possivel cadastrar o personal. Já existe outro personal com o mesmo e-mail."));
                }
                PersonalTrainer toSave = customObjectMapper.convertToEntity(dto);
                toSave.setPassword(bCryptPasswordEncoder.encode(dto.password()));
                PersonalTrainer result = personalTrainerRepository
                                .saveAndFlush(toSave);
                return ResponseEntity.created(
                                ServletUriComponentsBuilder
                                                .fromCurrentRequest()
                                                .path("/{id}")
                                                .buildAndExpand(result.getId())
                                                .toUri())
                                .build();
        }

        @Override
        public ResponseEntity<Object> edit(String email, PersonalTrainerDTO dto) throws Exception {
                PersonalTrainer dadosDto = customObjectMapper.convertToEntity(dto);
                PersonalTrainer paraEditar = personalTrainerRepository.findByEmail(email)
                                .orElseThrow(() -> new NoSuchElementException("Personal não encontrado!"));
                BeanUtils.copyProperties(dadosDto, paraEditar, "id", "createdDate", "status");
                PersonalTrainer objetoAtualizado = personalTrainerRepository.saveAndFlush(dadosDto);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(new ApiResponse<>(customObjectMapper.convertToDto(objetoAtualizado)));
        }

        @Override
        public ResponseEntity<Object> findByEmail(String email) throws Exception {
                PersonalTrainer personalTrainer = personalTrainerRepository.findByEmail(email)
                                .orElseThrow(() -> new NoSuchElementException("Personal não encontrado!"));
                return ResponseEntity.status(HttpStatus.OK)
                                .body(new ApiResponse<>(customObjectMapper.convertToDto(personalTrainer)));
        }

        @Override
        public ResponseEntity<Object> findAll() throws Exception {
                List<PersonalTrainerDTO> questionsDTOs = customObjectMapper
                                .convertToDtoList(personalTrainerRepository.findAll());
                if (questionsDTOs.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                        .body(new ApiResponse<>("Não existe personais cadastrados no Sistemas"));
                }
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(questionsDTOs));
        }

        @Override
        public ResponseEntity<Object> changeStatus(String email) throws Exception {
                PersonalTrainer personalTrainer = personalTrainerRepository.findByEmail(email)
                                .orElseThrow(() -> new NoSuchElementException("Personal não encontrado!"));
                personalTrainer.setStatus(!personalTrainer.isStatus());
                personalTrainerRepository.saveAndFlush(personalTrainer);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(new ApiResponse<>(customObjectMapper.convertToDto(personalTrainer)));
        }

}
