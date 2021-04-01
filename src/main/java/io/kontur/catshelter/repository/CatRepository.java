package io.kontur.catshelter.repository;

import io.kontur.catshelter.entity.Cat;
import io.kontur.catshelter.service.dto.AbstractDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Slf4j
@org.springframework.stereotype.Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CatRepository implements Repository<Cat> {
  private static final String ENTITY_NAME = "Cat";
  @PersistenceContext
  private final EntityManager entityManager;

  @Override
  public Optional<Cat> create(Cat cat) {
    entityManager.persist(cat);
    entityManager.flush();
    return Optional.of(cat);
  }

  @Override
  public Optional<Cat> read(long id) {
    return Optional.empty();
  }

  @Override
  public Optional<Cat> update(long id, AbstractDto dto) {
    return Optional.empty();
  }

  @Override
  public Optional<Cat> remove(long id) {
    return Optional.empty();
  }

  @Override
  public List<Cat> readAll() {
    return null;
  }
}
