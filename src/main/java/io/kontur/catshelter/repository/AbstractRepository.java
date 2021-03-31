package io.kontur.catshelter.repository;

import io.kontur.catshelter.entity.AbstractEntity;
import io.kontur.catshelter.service.dto.AbstractDto;

import java.util.List;

public abstract class AbstractRepository implements Repository<AbstractEntity<Number>> {
  @Override
  public AbstractEntity<Number> create(AbstractDto dto) {
    return null;
  }

  @Override
  public AbstractEntity<Number> read(long id) {
    return null;
  }

  @Override
  public AbstractEntity<Number> update(long id, AbstractDto dto) {
    return null;
  }

  @Override
  public AbstractEntity<Number> remove(long id) {
    return null;
  }

  @Override
  public List<AbstractEntity<Number>> readAll() {
    return null;
  }
}
