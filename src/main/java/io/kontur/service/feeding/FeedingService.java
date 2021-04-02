package io.kontur.service.feeding;

import io.kontur.entity.Feeding;
import io.kontur.service.dto.CatDto;
import io.kontur.service.dto.FeedingDto;

import java.util.List;

public interface FeedingService {
  Feeding create(FeedingDto dto);

  CatDto read(long id);

  CatDto delete(long id);

  List<FeedingDto> findUserFeeding(long userId);

  List<FeedingDto> findCatFeeding(long catId);
}
