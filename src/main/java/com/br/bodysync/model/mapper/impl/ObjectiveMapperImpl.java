package com.br.bodysync.model.mapper.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.br.bodysync.model.Objective;
import com.br.bodysync.model.dto.ObjectiveDTO;
import com.br.bodysync.model.mapper.CustomObjectMapper;

@Component
public class ObjectiveMapperImpl implements CustomObjectMapper<Objective, ObjectiveDTO> {

    @Override
    public ObjectiveDTO convertToDto(Objective entity) {
        ObjectiveDTO dto = new ObjectiveDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    @Override
    public Objective convertToEntity(ObjectiveDTO dto) {
        Objective objective = new Objective();
        objective.setId(dto.getId());
        objective.setDescription(dto.getDescription());
        objective.setName(dto.getName());
        return objective;
    }

    @Override
    public List<ObjectiveDTO> convertToDtoList(List<Objective> entityList) {
        return entityList.stream()
                .map(objective -> convertToDto(objective))
                .collect(Collectors.toList());
    }

}
