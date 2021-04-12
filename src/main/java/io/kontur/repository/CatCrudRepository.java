package io.kontur.repository;

import io.kontur.entity.Cat;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface CatCrudRepository extends CrudRepository<Cat, Long> {
  Optional<Cat> findCatByName(String name);
}


