/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.bodysync.service.impl;

import com.br.bodysync.model.Metric;
import com.br.bodysync.model.dto.MetricDTO;
import com.br.bodysync.model.mapper.CustomObjectMapper;
import com.br.bodysync.repository.MetricRepository;
import com.br.bodysync.service.MetricService;
import com.br.bodysync.service.util.ApiResponse;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author Carlos
 */

@Service
public class MetricServiceImpl implements MetricService{
    
    @Autowired
    private MetricRepository metricRepository;
    
    @Autowired
    private CustomObjectMapper<Metric, MetricDTO> metricMapper;

    @Override
    public ResponseEntity<Object> save(MetricDTO objectDTO) throws Exception {
        
        if (metricRepository.existsByDescription(objectDTO.getDescription())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar a Métrica. Já existe outro Métrica com o mesmo nome."));
        }

        Metric metric = new Metric();
        metric.setDescription(objectDTO.getDescription());

        Metric objetoCriado = metricRepository.saveAndFlush(metric);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(objetoCriado.getId())
                        .toUri())
                .build();
    }

    @Override
    public ResponseEntity<Object> edit(Long idObject, MetricDTO object) throws Exception {
        
        Metric dadosDto = metricMapper.convertToEntity(object);
        Metric paraEditar = metricRepository.findById(idObject)
                .orElseThrow(() -> new NoSuchElementException("A Métrica com ID " + idObject + " não foi encontrada!"));

        if (metricRepository.existsByDescription(object.getDescription())) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Não é possivel cadastrar a Métricas. Já existe outra Métricas com o mesmo nome."));
        }

       
        dadosDto.setId(idObject);
        BeanUtils.copyProperties(dadosDto, paraEditar, "id");
        Metric objetoAtualizado = metricRepository.saveAndFlush(dadosDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(metricMapper.convertToDto(objetoAtualizado)));
    
    }

    @Override
    public ResponseEntity<Object> findById(Long idObject) throws Exception {
        
         Metric metric = metricRepository.findById(idObject)
                .orElseThrow(() -> new NoSuchElementException("A Métrica com ID " + idObject + " não foi encontrada!"));

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(metricMapper.convertToDto(metric)));
    
    }

    @Override
    public ResponseEntity<Object> findAll() throws Exception {
        
         List<MetricDTO> metricDTOs = metricMapper.convertToDtoList(metricRepository.findAll());
        if (metricDTOs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Não existe Métricas cadastradas no Sistemas"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(metricDTOs));
    
    }

    @Override
    public ResponseEntity<Object> delete(Long idObject) throws Exception {
        
       metricRepository.findById(idObject)
                .orElseThrow(() -> new NoSuchElementException("A Métrica com ID " + idObject + " não foi encontrada!"));

        metricRepository.deleteById(idObject);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("A Métrica foi excluída com sucesso."));

    }
    
}
