package io.kontur.repository;

import io.kontur.entity.AbstractEntity;
import io.kontur.repository.specification.CriteriaSpecification;

import java.util.List;
import java.util.Optional;

public interface Repository<T extends AbstractEntity> {
  Optional<T> create(T t);

  T read(long id);

  Optional<T> remove(long id);

  List<T> readAll();

  Optional<T> findBySpecification(CriteriaSpecification<T> specification);
}
