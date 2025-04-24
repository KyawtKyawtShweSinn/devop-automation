package com.lattmat.devop.utility;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class GenricMapperUtility {

    private final ModelMapper modelMapper;

    @Autowired
    public GenricMapperUtility(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <D, T> D mapToDto(T entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    public <D, T> T mapToEntity(D dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

    public <D, T> List<D> mapToDtoList(List<T> entities, Class<D> dtoClass) {
        return entities.stream()
        .map(entity -> mapToDto(entity, dtoClass))
        .toList();
    }

    public <D, T> List<T> mapToEntityList(List<D> dtos, Class<T> entityClass) {
        return dtos.stream()
        .map(dto -> mapToEntity(dto, entityClass))
        .toList();
    }
}