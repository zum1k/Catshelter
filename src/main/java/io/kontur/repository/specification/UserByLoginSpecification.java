package io.kontur.repository.specification;

import io.kontur.entity.User;
import lombok.AllArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class UserByLoginSpecification implements CriteriaSpecification<User> {
  private final String login;

  @Override
  public Predicate toPredicate(Root<User> root, CriteriaBuilder criteriaBuilder) {
    return criteriaBuilder.equal(root.get("login"), login);
  }
}
