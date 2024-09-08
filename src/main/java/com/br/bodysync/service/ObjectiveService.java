package com.br.bodysync.service;

import org.springframework.http.ResponseEntity;

import com.br.bodysync.model.dto.ObjectiveDTO;
import com.br.bodysync.service.util.CrudService;

public interface ObjectiveService extends CrudService<ObjectiveDTO> {

    ResponseEntity<Object> changeStatus(Long idObject) throws Exception;

}
