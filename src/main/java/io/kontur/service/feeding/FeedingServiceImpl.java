package io.kontur.service.feeding;

import io.kontur.service.dto.FeedingDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@AllArgsConstructor
@Service
public class FeedingServiceImpl implements FeedingService {
  @Override
  @Transactional
  public FeedingDto create(FeedingDto dto) {
    return null;
  }

  @Override
  public FeedingDto read(long id) {
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
