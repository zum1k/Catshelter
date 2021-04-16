package io.kontur.repository;

import io.kontur.entity.Cat;
import io.kontur.entity.Feeding;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CatCrudRepository extends CrudRepository<Cat, Long> {
  Optional<Cat> findCatByName(String name);

  List<Cat> findDistinctByFeedingsIn(Iterable<Feeding> feedings);
}


