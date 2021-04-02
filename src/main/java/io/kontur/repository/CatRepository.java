package io.kontur.repository;

import io.kontur.entity.Cat;
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
  public Optional<Cat> update(long id, Cat cat) {
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

  @Override
  public Optional<Cat> findBySpecification(CriteriaSpecification<Cat> specification) {
    TypedQuery<Cat> query = entityManager.createQuery(mapQuery(specification));
    return query.getResultStream().findFirst();
  }
  private TypedQuery<Cat> typedQuery() {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Cat> query = builder.createQuery(Cat.class);
    Root<Cat> rootEntry = query.from(Cat.class);
    CriteriaQuery<Cat> all = query.select(rootEntry);
    return entityManager.createQuery(all);
  }

  private CriteriaQuery<Cat> mapQuery(CriteriaSpecification<Cat> specification) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Cat> criteriaQuery = builder.createQuery(Cat.class);
    Root<Cat> tagRoot = criteriaQuery.from(Cat.class);
    return criteriaQuery.where(specification.toPredicate(tagRoot, builder));
  }
}
