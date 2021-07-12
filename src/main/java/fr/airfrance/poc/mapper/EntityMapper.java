package fr.airfrance.poc.mapper;

import java.util.List;

/**
 *
 * @param <D> DTO
 * @param <E> Entity
 */

public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntityList(List<D> dtoList);

    List<D> toDtoList(List<E> entityList);
}
