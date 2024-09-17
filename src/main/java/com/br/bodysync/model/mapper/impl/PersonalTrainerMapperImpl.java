package com.br.bodysync.model.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.br.bodysync.model.PersonalTrainer;
import com.br.bodysync.model.dto.UserDTO;
import com.br.bodysync.model.mapper.CustomObjectMapper;

@Component
public class PersonalTrainerMapperImpl implements CustomObjectMapper<PersonalTrainer, UserDTO> {

    @Override
    public UserDTO convertToDto(PersonalTrainer entity) {
        UserDTO dto = new UserDTO();
        dto.setBirthDate(entity.getBirthDate());
        dto.setFullName(entity.getFullName());
        dto.setId(entity.getId());
        dto.setSex(entity.getSex());
        return dto;
    }

    @Override
    public PersonalTrainer convertToEntity(UserDTO dto) {
        PersonalTrainer personal = new PersonalTrainer();
        personal.setBirthDate(dto.getBirthDate());
        personal.setCpf(dto.getCpf());
        personal.setEmail(dto.getEmail());
        personal.setFullName(dto.getFullName());
        personal.setSex(dto.getSex());
        return personal;
    }

    @Override
    public List<UserDTO> convertToDtoList(List<PersonalTrainer> entityList) {
        return entityList.stream()
                .map(objective -> convertToDto(objective))
                .collect(Collectors.toList());
    }

}
