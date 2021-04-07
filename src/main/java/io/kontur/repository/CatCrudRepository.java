package io.kontur.repository;

import io.kontur.entity.Cat;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface CatCrudRepository extends CrudRepository<Cat, Integer> {
  Optional<Cat> findCatByName(String name);
}
