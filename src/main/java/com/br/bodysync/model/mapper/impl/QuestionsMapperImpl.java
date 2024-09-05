/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.bodysync.model.mapper.impl;

import com.br.bodysync.model.Questions;
import com.br.bodysync.model.dto.QuestionsDTO;
import com.br.bodysync.model.mapper.CustomObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Carlos
 */

@Component
public class QuestionsMapperImpl implements CustomObjectMapper<Questions, QuestionsDTO>{

    @Override
    public QuestionsDTO convertToDto(Questions entity) {
        
        QuestionsDTO dto = new QuestionsDTO();
        
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.isStatus());
        
        return dto;
    }

    @Override
    public Questions convertToEntity(QuestionsDTO dto) {
       
        Questions questions = new Questions();
        
        questions.setId(dto.getId());
        questions.setDescription(dto.getDescription());
        questions.setStatus(dto.isStatus());
        
        return questions;
    }

    @Override
    public List<QuestionsDTO> convertToDtoList(List<Questions> entityList) {
        
        List<QuestionsDTO> lista = new ArrayList<>();
        for (Questions entity : entityList) {
            lista.add(convertToDto(entity));
        }
        return lista;
    }
    
}
