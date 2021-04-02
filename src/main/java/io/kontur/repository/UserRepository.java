package io.kontur.repository;

import io.kontur.entity.Cat;
import io.kontur.entity.User;
import io.kontur.exception.EntityNotFoundException;
import io.kontur.repository.specification.CriteriaSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
  public User read(long id) {
    User user = entityManager.find(User.class, id);
    if (user != null) {
      entityManager.remove(user);
      return user;
    }
    throw new EntityNotFoundException(ENTITY_NAME, id);
  }

  @Override
  public Optional<User> remove(long id) {
    User user = entityManager.find(User.class, id);
    if (user != null) {
      entityManager.remove(user);
      return Optional.of(user);
    }
    throw new EntityNotFoundException(ENTITY_NAME, id);
  }

  @Override
  public List<User> readAll() {
    TypedQuery<User> allQuery = typedQuery();
    return allQuery.getResultList();
  }

  @Override
  public Optional<User> findBySpecification(CriteriaSpecification<User> specification) {
    return Optional.empty();
  }
  private TypedQuery<User> typedQuery() {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<User> query = builder.createQuery(User.class);
    Root<User> rootEntry = query.from(User.class);
    CriteriaQuery<User> all = query.select(rootEntry);
    return entityManager.createQuery(all);
  }

  private CriteriaQuery<Cat> mapQuery(CriteriaSpecification<Cat> specification) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Cat> criteriaQuery = builder.createQuery(Cat.class);
    Root<Cat> tagRoot = criteriaQuery.from(Cat.class);
    return criteriaQuery.where(specification.toPredicate(tagRoot, builder));
  }
}
