package io.kontur.catshelter.repository;

import io.kontur.catshelter.entity.AbstractEntity;
import io.kontur.catshelter.service.dto.AbstractDto;

import java.util.List;

public interface Repository<T extends AbstractEntity<Number>> {
  T create(AbstractDto dto);

  T read(long id);

  T update(long id, AbstractDto dto);

  T remove(long id);

  List<T> readAll();
}
