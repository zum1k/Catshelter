package io.kontur.repository;

import io.kontur.entity.Cat;
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
import java.util.stream.Collectors;

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
  public Cat read(long id) {
    Cat cat = entityManager.find(Cat.class, id);
    if (cat != null) {
      return cat;
    }
    throw new EntityNotFoundException(ENTITY_NAME, id);
  }

  @Override
  public Optional<Cat> remove(long id) {
    Cat cat = entityManager.find(Cat.class, id);
    if (cat != null) {
      entityManager.remove(cat);
      return Optional.of(cat);
    }
    throw new EntityNotFoundException(ENTITY_NAME, id);
  }

  @Override
  public List<Cat> readAll() {
    TypedQuery<Cat> allQuery = typedQuery();
    return allQuery.getResultList();
  }

  @Override
  public List<Cat> readAllBySpecification(CriteriaSpecification<Cat> specification) {
    TypedQuery<Cat> query = entityManager.createQuery(mapQuery(specification));
    return query.getResultStream().collect(Collectors.toList());
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
