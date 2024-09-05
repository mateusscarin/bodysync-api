/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.br.bodysync.service;

import com.br.bodysync.model.dto.QuestionsDTO;
import com.br.bodysync.service.util.CrudService;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Carlos
 */
public interface QuestionsService extends CrudService<QuestionsDTO>{
    
    ResponseEntity<Object> changeStatus(Long idObject) throws Exception;
}
