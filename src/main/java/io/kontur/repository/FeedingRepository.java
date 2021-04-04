package io.kontur.repository;

import io.kontur.entity.Feeding;
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
public class FeedingRepository implements Repository<Feeding> {
  private static final String ENTITY_NAME = "Feeding";
  @PersistenceContext
  private final EntityManager entityManager;

  @Override
  public Optional<Feeding> create(Feeding feeding) {
    entityManager.persist(feeding);
    entityManager.flush();
    return Optional.of(feeding);
  }

  @Override
  public Feeding read(long id) {
    Feeding feeding = entityManager.find(Feeding.class, id);
    if (feeding != null) {
      entityManager.remove(feeding);
      return feeding;
    }
    throw new EntityNotFoundException(ENTITY_NAME, id);
  }

  @Override
  public Optional<Feeding> remove(long id) {
    return Optional.empty();
  }

  @Override
  public List<Feeding> readAll() {
    TypedQuery<Feeding> allQuery = typedQuery();
    return allQuery.getResultList();
  }

  @Override
  public List<Feeding> readAllBySpecification(CriteriaSpecification<Feeding> specification) {
    TypedQuery<Feeding> query = entityManager.createQuery(mapQuery(specification));
    return query.getResultStream().collect(Collectors.toList());
  }

  @Override
  public Optional<Feeding> findBySpecification(CriteriaSpecification<Feeding> specification) {
    TypedQuery<Feeding> query = entityManager.createQuery(mapQuery(specification));
    return query.getResultStream().findFirst();
  }

  private TypedQuery<Feeding> typedQuery() {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Feeding> query = builder.createQuery(Feeding.class);
    Root<Feeding> rootEntry = query.from(Feeding.class);
    CriteriaQuery<Feeding> all = query.select(rootEntry);
    return entityManager.createQuery(all);
  }

  private CriteriaQuery<Feeding> mapQuery(CriteriaSpecification<Feeding> specification) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Feeding> criteriaQuery = builder.createQuery(Feeding.class);
    Root<Feeding> tagRoot = criteriaQuery.from(Feeding.class);
    return criteriaQuery.where(specification.toPredicate(tagRoot, builder));
  }
}
