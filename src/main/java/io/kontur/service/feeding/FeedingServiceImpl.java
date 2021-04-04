package io.kontur.service.feeding;

import io.kontur.entity.Feeding;
import io.kontur.exception.EntityNotAddedException;
import io.kontur.exception.EntityNotFoundException;
import io.kontur.repository.Repository;
import io.kontur.service.dto.FeedingDto;
import io.kontur.utils.mapper.FeedingMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class FeedingServiceImpl implements FeedingService {
  private static final String ENTITY_NAME = "Feeding";
  private final Repository<Feeding> repository;
  private final FeedingMapper mapper;

  @Override
  @Transactional
  public FeedingDto create(FeedingDto dto) {
    log.info("add feeding");
    dto.setId(0L);
    return mapper.toDto
        (repository.create(mapper.toEntity(dto)).orElseThrow(
            () -> new EntityNotAddedException(ENTITY_NAME)));
  }

  @Override
  public FeedingDto read(long id) {
    log.info("find feeding {}", id);
    Feeding feeding = repository.read(id);
    return mapper.toDto(feeding);
  }

  @Override
  public List<FeedingDto> readAll() {
    List<Feeding> feedings = repository.readAll();
    if (feedings.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME, 0L);
    }
    return mapper.toDtoList(feedings);
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