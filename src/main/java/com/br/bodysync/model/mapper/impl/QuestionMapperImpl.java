package com.br.bodysync.model.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.br.bodysync.model.Question;
import com.br.bodysync.model.dto.QuestionDTO;
import com.br.bodysync.model.mapper.CustomObjectMapper;

/**
 *
 * @author Carlos
 */

@Component
public class QuestionMapperImpl implements CustomObjectMapper<Question, QuestionDTO>{

    @Override
    public QuestionDTO convertToDto(Question entity) {
        
        QuestionDTO dto = new QuestionDTO();
        
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.isStatus());
        
        return dto;
    }

    @Override
    public Question convertToEntity(QuestionDTO dto) {
       
        Question questions = new Question();
        
        questions.setId(dto.getId());
        questions.setDescription(dto.getDescription());
        questions.setStatus(dto.isStatus());
        
        return questions;
    }

    @Override
    public List<QuestionDTO> convertToDtoList(List<Question> entityList) {
        
        List<QuestionDTO> lista = new ArrayList<>();
        for (Question entity : entityList) {
            lista.add(convertToDto(entity));
        }
        return lista;
    }
    
}
