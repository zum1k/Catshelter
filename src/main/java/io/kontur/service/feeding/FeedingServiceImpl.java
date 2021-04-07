package io.kontur.service.feeding;

import io.kontur.entity.Feeding;
import io.kontur.exception.EntityNotFoundException;
import io.kontur.repository.FeedingCrudRepository;
import io.kontur.service.dto.FeedingDto;
import io.kontur.utils.mapper.FeedingMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class FeedingServiceImpl implements FeedingService {
  private static final String ENTITY_NAME = "Feeding";
  private final FeedingCrudRepository repository;
  private final FeedingMapper mapper;

  @Override
  public FeedingDto create(FeedingDto dto) {
    log.info("add feeding");
    Feeding feeding = mapper.toEntity(dto);
    return mapper.toDto(repository.save(feeding));
  }

  @Override
  public FeedingDto read(int id) {
    log.info("find feeding {}", id);
    Optional<Feeding> optionalFeeding = repository.findById(id);
    if (optionalFeeding.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME, id);
    }
    return mapper.toDto(optionalFeeding.get());
  }

  @Override
  public List<FeedingDto> readAll() {
    List<Feeding> feedings = (List<Feeding>) repository.findAll();
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
