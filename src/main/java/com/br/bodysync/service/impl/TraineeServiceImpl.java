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

import com.br.bodysync.model.Trainee;
import com.br.bodysync.model.dto.TraineeDTO;
import com.br.bodysync.model.mapper.CustomObjectMapper;
import com.br.bodysync.repository.TraineeRepository;
import com.br.bodysync.service.TraineeService;
import com.br.bodysync.service.util.ApiResponse;

@Service
public class TraineeServiceImpl implements TraineeService {

        @Autowired
        private TraineeRepository personalTrainerRepository;

        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        @Autowired
        private CustomObjectMapper<Trainee, TraineeDTO> customObjectMapper;

        @Override
        public ResponseEntity<Object> save(TraineeDTO dto) throws Exception {
                if (personalTrainerRepository.existsByEmail(dto.email())) {
                        return ResponseEntity.badRequest().body(new ApiResponse<>(
                                        "Não é possivel cadastrar o aluno. Já existe outra aluno com o mesmo email."));
                }
                if (personalTrainerRepository.existsByCpf(dto.cpf())) {
                        return ResponseEntity.badRequest().body(new ApiResponse<>(
                                        "Não é possivel cadastrar o aluno. Já existe outra aluno com o mesmo CPF."));
                }
                Trainee toSave = customObjectMapper.convertToEntity(dto);
                toSave.setPassword(bCryptPasswordEncoder.encode(dto.password()));
                Trainee result = personalTrainerRepository
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
        public ResponseEntity<Object> edit(String email, TraineeDTO dto) throws Exception {
                Trainee dadosDto = customObjectMapper.convertToEntity(dto);
                Trainee paraEditar = personalTrainerRepository.findByEmail(email)
                                .orElseThrow(() -> new NoSuchElementException("Aluno não encontrado!"));
                BeanUtils.copyProperties(dadosDto, paraEditar, "id", "createdDate", "status");
                Trainee objetoAtualizado = personalTrainerRepository.saveAndFlush(dadosDto);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(new ApiResponse<>(customObjectMapper.convertToDto(objetoAtualizado)));
        }

        @Override
        public ResponseEntity<Object> findByEmail(String email) throws Exception {
                Trainee personalTrainer = personalTrainerRepository.findByEmail(email)
                                .orElseThrow(() -> new NoSuchElementException("Aluno não encontrado!"));
                return ResponseEntity.status(HttpStatus.OK)
                                .body(new ApiResponse<>(customObjectMapper.convertToDto(personalTrainer)));
        }

        @Override
        public ResponseEntity<Object> findAll() throws Exception {
                List<TraineeDTO> questionsDTOs = customObjectMapper
                                .convertToDtoList(personalTrainerRepository.findAll());
                if (questionsDTOs.isEmpty()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                        .body(new ApiResponse<>("Não existe alunos cadastrados no Sistemas"));
                }
                return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(questionsDTOs));
        }

        @Override
        public ResponseEntity<Object> changeStatus(String email) throws Exception {
                Trainee personalTrainer = personalTrainerRepository.findByEmail(email)
                                .orElseThrow(() -> new NoSuchElementException("Aluno não encontrado!"));
                personalTrainer.setStatus(!personalTrainer.isStatus());
                personalTrainerRepository.saveAndFlush(personalTrainer);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(new ApiResponse<>(customObjectMapper.convertToDto(personalTrainer)));
        }

}
