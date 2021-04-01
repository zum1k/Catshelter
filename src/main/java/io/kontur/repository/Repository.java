package io.kontur.repository;

import io.kontur.entity.AbstractEntity;
import io.kontur.service.dto.AbstractDto;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends AbstractEntity> {
  Optional<T> create(T t);

  Optional<T> read(long id);

  Optional<T> update(long id, AbstractDto dto);

  Optional<T> remove(long id);

  List<T> readAll();
}
