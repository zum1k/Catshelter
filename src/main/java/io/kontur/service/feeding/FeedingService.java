package io.kontur.service.feeding;

import io.kontur.service.dto.FeedingDto;

import java.util.List;

public interface FeedingService {
  FeedingDto create(FeedingDto dto);

  FeedingDto read(int id);

  List<FeedingDto> readAll();

  List<FeedingDto> findUserFeeding(int userId);

  List<FeedingDto> findCatFeeding(int catId);
}
