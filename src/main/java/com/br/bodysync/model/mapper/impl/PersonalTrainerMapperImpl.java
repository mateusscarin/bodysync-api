package com.br.bodysync.model.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.br.bodysync.model.PersonalTrainer;
import com.br.bodysync.model.dto.PersonalTrainerDTO;
import com.br.bodysync.model.mapper.CustomObjectMapper;

@Component
public class PersonalTrainerMapperImpl implements CustomObjectMapper<PersonalTrainer, PersonalTrainerDTO> {

    @Override
    public PersonalTrainerDTO convertToDto(PersonalTrainer entity) {
        return new PersonalTrainerDTO(
                entity.getFullName(),
                entity.getCpf(),
                null,
                entity.getBirthDate(),
                entity.getSex(),
                entity.getEmail());
    }

    @Override
    public PersonalTrainer convertToEntity(PersonalTrainerDTO dto) {
        PersonalTrainer personal = new PersonalTrainer();
        personal.setBirthDate(dto.birthDate());
        personal.setCpf(dto.cpf());
        personal.setPassword(dto.password());
        personal.setEmail(dto.email());
        personal.setFullName(dto.fullName());
        personal.setSex(dto.sex());
        return personal;
    }

    @Override
    public List<PersonalTrainerDTO> convertToDtoList(List<PersonalTrainer> entityList) {
        return entityList.stream()
                .map(objective -> convertToDto(objective))
                .collect(Collectors.toList());
    }

}
