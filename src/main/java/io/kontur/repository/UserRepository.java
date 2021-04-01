package io.kontur.repository;

import io.kontur.entity.User;
import io.kontur.exception.EntityNotFoundException;
import io.kontur.service.dto.AbstractDto;
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
public class UserRepository implements Repository<User> {
  private static final String ENTITY_NAME = "User";
  @PersistenceContext
  private final EntityManager entityManager;


  @Override
  public Optional<User> create(User user) {
    entityManager.persist(user);
    entityManager.flush();
    return Optional.of(user);
  }

  @Override
  public Optional<User> read(long id) {
    User user = entityManager.find(User.class, id);
    if (user != null) {
      entityManager.remove(user);
      return Optional.of(user);
    }
    throw new EntityNotFoundException(ENTITY_NAME, id);
  }

  @Override
  public Optional<User> update(long id, AbstractDto dto) {
    return Optional.empty();
  }

  @Override
  public Optional<User> remove(long id) {
    return Optional.empty();
  }

  @Override
  public List<User> readAll() {
    return null;
  }
}
