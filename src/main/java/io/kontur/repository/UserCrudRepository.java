package io.kontur.repository;

import io.kontur.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserCrudRepository extends CrudRepository<User, Integer> {
  Optional<User> findUserByLogin(String name);
}
