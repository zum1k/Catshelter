package io.kontur.service.feeding;

import io.kontur.entity.Feeding;
import io.kontur.service.dto.CatDto;
import io.kontur.service.dto.FeedingDto;

import java.util.List;

public class FeedingServiceImpl implements FeedingService {
  @Override
  public Feeding create(FeedingDto dto) {
    return null;
  }

  @Override
  public CatDto read(long id) {
    return null;
  }

  @Override
  public CatDto delete(long id) {
    return null;
  }

  @Override
  public List<FeedingDto> findUserFeeding(long userId) {
    return null;
  }

  @Override
  public List<FeedingDto> findCatFeeding(long catId) {
    return null;
  }
}
