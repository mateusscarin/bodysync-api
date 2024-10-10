/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.bodysync.model.mapper.impl;

import org.springframework.stereotype.Component;
import com.br.bodysync.model.Metric;
import com.br.bodysync.model.dto.MetricDTO;
import com.br.bodysync.model.mapper.CustomObjectMapper;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Carlos
 */
@Component
public class MetricMapperImpl implements CustomObjectMapper<Metric, MetricDTO>{

    @Override
    public MetricDTO convertToDto(Metric entity) {
    
        MetricDTO dto = new MetricDTO();
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        
        return dto;
    }

    @Override
    public Metric convertToEntity(MetricDTO dto) {
      
        Metric metric = new Metric();
        metric.setId(dto.getId());
        metric.setDescription(dto.getDescription());
        
        return metric;
    }

    @Override
    public List<MetricDTO> convertToDtoList(List<Metric> entityList) {
        
        List<MetricDTO> lista = new ArrayList<>();
        for (Metric entity : entityList){
            lista.add(convertToDto(entity));
        }
        
        return lista;
    }
    
}
