package com.br.bodysync.model.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.br.bodysync.model.Objective;
import com.br.bodysync.model.PersonalTrainer;
import com.br.bodysync.model.Trainee;
import com.br.bodysync.model.dto.TraineeDTO;
import com.br.bodysync.model.mapper.CustomObjectMapper;

@Component
public class TraineeMapperImpl implements CustomObjectMapper<Trainee, TraineeDTO> {

    @Override
    public TraineeDTO convertToDto(Trainee entity) {
        return new TraineeDTO(
                entity.getFullName(),
                entity.getCpf(),
                null,
                entity.getBirthDate(),
                entity.getSex(),
                entity.getEmail(),
                entity.isHasAlreadyTrained(),
                entity.getTime(),
                entity.getObjective() == null ? null : entity.getObjective().getId(),
                entity.getPersonalTrainer() == null ? null : entity.getPersonalTrainer().getId());
    }

    @Override
    public Trainee convertToEntity(TraineeDTO dto) {
        Trainee trainee = new Trainee();
        trainee.setBirthDate(dto.birthDate());
        trainee.setCpf(dto.cpf());
        trainee.setPassword(dto.password());
        trainee.setEmail(dto.email());
        trainee.setFullName(dto.fullName());
        trainee.setSex(dto.sex());
        Objective objective = new Objective();
        objective.setId(dto.objectiveId());
        trainee.setObjective(objective);
        trainee.setHasAlreadyTrained(dto.hasAlreadyTrained());
        trainee.setTime(dto.time());
        PersonalTrainer personalTrainer = new PersonalTrainer();
        personalTrainer.setId(dto.personalTrainerId());
        trainee.setPersonalTrainer(personalTrainer);
        return trainee;
    }

    @Override
    public List<TraineeDTO> convertToDtoList(List<Trainee> entityList) {
        return entityList.stream()
                .map(objective -> convertToDto(objective))
                .collect(Collectors.toList());
    }

}
