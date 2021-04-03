package io.kontur.repository;

import io.kontur.entity.Feeding;
import io.kontur.repository.specification.CriteriaSpecification;

import java.util.List;
import java.util.Optional;

public class FeedingRepository implements Repository<Feeding> {
  @Override
  public Optional<Feeding> create(Feeding feeding) {
    return Optional.empty();
  }

  @Override
  public Feeding read(long id) {
    return null;
  }

  @Override
  public Optional<Feeding> remove(long id) {
    return Optional.empty();
  }

  @Override
  public List<Feeding> readAll() {
    return null;
  }

  @Override
  public Optional<Feeding> findBySpecification(CriteriaSpecification<Feeding> specification) {
    return Optional.empty();
  }
}
