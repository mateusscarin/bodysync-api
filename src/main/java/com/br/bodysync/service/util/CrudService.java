package com.br.bodysync.service.util;

import org.springframework.http.ResponseEntity;

/**
 *
 * @author Carlos
 * @param <T>
 */
public interface CrudService<T> {

    ResponseEntity<Object> save(T objectDTO) throws Exception;

    ResponseEntity<Object> edit(Long idObject, T object) throws Exception;

    ResponseEntity<Object> findById(Long idObject) throws Exception;

    ResponseEntity<Object> findAll() throws Exception;

    ResponseEntity<Object> delete(Long idObject) throws Exception;
}
