package io.kontur.repository;

import io.kontur.entity.Cat;
import io.kontur.entity.Feeding;
import io.kontur.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

public interface FeedingCrudRepository extends CrudRepository<Feeding, Long> {
  List<Feeding> findAllByCat(Cat cat);

  List<Feeding> findAllByUser(User user);

  Set<Feeding> findAllByFeedingTimeGreaterThan(ZonedDateTime feedingTime);
}
