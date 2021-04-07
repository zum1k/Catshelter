package io.kontur.repository;

import io.kontur.entity.Feeding;
import org.springframework.data.repository.CrudRepository;

public interface FeedingCrudRepository extends CrudRepository<Feeding, Integer> {
}
