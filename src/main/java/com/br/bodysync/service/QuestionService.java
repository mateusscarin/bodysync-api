package com.br.bodysync.service;

import org.springframework.http.ResponseEntity;

import com.br.bodysync.model.dto.QuestionDTO;
import com.br.bodysync.service.util.CrudService;

/**
 *
 * @author Carlos
 */
public interface QuestionService extends CrudService<QuestionDTO> {

    ResponseEntity<Object> changeStatus(Long idObject) throws Exception;

}
