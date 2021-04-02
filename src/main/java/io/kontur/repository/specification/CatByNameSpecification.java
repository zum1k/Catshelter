package io.kontur.repository.specification;

import io.kontur.entity.Cat;
import lombok.AllArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class CatByNameSpecification implements CriteriaSpecification<Cat> {
  private final String catName;
  @Override
  public Predicate toPredicate(Root<Cat> root, CriteriaBuilder criteriaBuilder) {
    return criteriaBuilder.equal(root.get("name"), catName);
  }
}
