package io.kontur.service.feeding;

import io.kontur.entity.Cat;
import io.kontur.entity.Feeding;
import io.kontur.entity.User;
import io.kontur.exception.EntityNotFoundException;
import io.kontur.repository.CatCrudRepository;
import io.kontur.repository.FeedingCrudRepository;
import io.kontur.repository.UserCrudRepository;
import io.kontur.service.dto.CatDto;
import io.kontur.service.dto.FeedingDto;
import io.kontur.utils.mapper.CatMapper;
import io.kontur.utils.mapper.FeedingMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class FeedingServiceImpl implements FeedingService {
  private static final String ENTITY_NAME = "Feeding";
  private static final String USER_ENTITY_NAME = "User";
  private static final String CAT_ENTITY_NAME = "Cat";
  private final FeedingCrudRepository feedingCrudRepository;
  private final CatCrudRepository catCrudRepository;
  private final UserCrudRepository userCrudRepository;
  private final FeedingMapper feedingMapper;
//  private final CatMapper catMapper;

  @Override
  public FeedingDto create(FeedingDto dto) {
    log.info("add feeding");
    long userId = dto.getUserId();
    long catId = dto.getCatDto().getId();

    Feeding feeding = feedingMapper.toEntity(dto);
    feeding.setUser(findUserIfExists(userId));
    feeding.setCat(findCatIfExists(catId));
    feeding.setFeedingTime(ZonedDateTime.now());
    return feedingMapper.toDto(feedingCrudRepository.save(feeding));
  }

  @Override
  public FeedingDto read(long id) {
    log.info("find feeding {}", id);
    Optional<Feeding> optionalFeeding = feedingCrudRepository.findById(id);
    if (optionalFeeding.isEmpty()) {
      log.error("Feeding {} not found", id);
      throw new EntityNotFoundException(ENTITY_NAME, id);
    }
    return feedingMapper.toDto(optionalFeeding.get());
  }

  @Override
  public List<FeedingDto> readAll() {
    log.info("Find all feedings");
    List<Feeding> feedings = (List<Feeding>) feedingCrudRepository.findAll();
    if (feedings.isEmpty()) {
      log.error("Feedings not found");
      throw new EntityNotFoundException(ENTITY_NAME, 0L);
    }
    return feedingMapper.toDtoList(feedings);
  }

  @Override
  public List<FeedingDto> findUserFeeding(long userId) {
    log.info("find feedings by user {}", userId);
    User user = findUserIfExists(userId);
    List<Feeding> feedings = feedingCrudRepository.findAllByUser(user);
    if (feedings.isEmpty()) {
      log.error("Feedings by user {} not found", userId);
      throw new EntityNotFoundException(ENTITY_NAME, userId);
    }
    return feedingMapper.toDtoList(feedings);
  }

  @Override
  public List<FeedingDto> findCatFeeding(long catId) {
    log.info("find feedings by cat {}", catId);
    Cat cat = findCatIfExists(catId);
    List<Feeding> feedings = feedingCrudRepository.findAllByCat(cat);
    if (feedings.isEmpty()) {
      log.error("Feedings by cat {} not found", catId);
      throw new EntityNotFoundException(ENTITY_NAME, catId);
    }
    return feedingMapper.toDtoList(feedings);
  }

  private User findUserIfExists(long userId) {
    log.info("find user by {}", userId);
    Optional<User> optionalUser = userCrudRepository.findById(userId);
    if (optionalUser.isEmpty()) {
      log.error("User {} not found", userId);
      throw new EntityNotFoundException(USER_ENTITY_NAME, userId);
    }
    return optionalUser.get();
  }

  private Cat findCatIfExists(long catId) {
    log.info("find cat by {}", catId);
    Optional<Cat> optionalCat = catCrudRepository.findById(catId);
    if (optionalCat.isEmpty()) {
      log.error("Cat {} not found", catId);
      throw new EntityNotFoundException(CAT_ENTITY_NAME, catId);
    }
    return optionalCat.get();
  }
}
