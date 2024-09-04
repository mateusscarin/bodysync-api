package com.br.bodysync.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomObjectMapper<T, R> {

    R convertToDto(T entity);

    T convertToEntity(R dto);

    List<R> convertToDtoList(List<T> entityList);

}
