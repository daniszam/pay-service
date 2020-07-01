package ru.darzam.payservice.mapper;

/**
 * @author zamaliev
 */
public interface BaseMapper<T, K> {

  T toEntity(K dto);

  K toDto(T entity);
}
