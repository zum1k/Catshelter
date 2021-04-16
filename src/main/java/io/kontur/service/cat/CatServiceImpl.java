package io.kontur.service.cat;

import io.kontur.entity.Cat;
import io.kontur.entity.Feeding;
import io.kontur.exception.EntityNotFoundException;
import io.kontur.repository.CatCrudRepository;
import io.kontur.repository.FeedingCrudRepository;
import io.kontur.service.dto.CatDto;
import io.kontur.utils.mapper.CatMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class CatServiceImpl implements CatService {
  private static final String ENTITY_NAME = "Cat";
  private static final int FEEDING_PERIOD_IN_HOURS = 4;
  private final CatMapper catMapper;
  private final CatCrudRepository catCrudRepository;
  private FeedingCrudRepository feedingCrudRepository;

  @Override
  public CatDto create(CatDto dto) {
    log.info("add cat");
    Optional<Cat> optionalCat = catCrudRepository.findCatByName(dto.getName());
    if (optionalCat.isEmpty()) {
      Cat cat = catMapper.toEntity(dto);
      cat.setCreateDate(ZonedDateTime.now());
      cat.setLastUpdateDate(ZonedDateTime.now());
      cat = catCrudRepository.save(cat);
      return catMapper.toDto(cat);
    }
    return dto;
  }

  @Override
  public CatDto read(long id) {
    log.info("find cat {}", id);
    Optional<Cat> catOptional = catCrudRepository.findById(id);
    if (catOptional.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME, id);
    }
    return catMapper.toDto(catOptional.get());
  }

  @Override
  public CatDto delete(long id) {
    log.info("delete cat {}", id);
    Optional<Cat> catOptional = catCrudRepository.findById(id);
    if (catOptional.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME, id);
    }
    catCrudRepository.delete(catOptional.get());
    return catMapper.toDto(catOptional.get());
  }

  @Override
  public List<CatDto> findHungryCats() {
    log.info("find hungry cats");
    ZonedDateTime lastFourHourTime = ZonedDateTime.now().minusHours(FEEDING_PERIOD_IN_HOURS);
    Set<Feeding> lastFourHourFeedings =
        feedingCrudRepository.findAllByFeedingTimeGreaterThan(lastFourHourTime);
    if (lastFourHourFeedings.isEmpty()) {
      List<Cat> cats = (List<Cat>) catCrudRepository.findAll();
      return catMapper.toDtoList(cats);
    }
    List<Cat> wellFedCats =
        catCrudRepository.findDistinctByFeedingsIn(lastFourHourFeedings);
    List<Cat> allCats = (List<Cat>) catCrudRepository.findAll();

    Set<Cat> hungryCats = allCats.stream()
        .filter(element -> !wellFedCats.contains(element))
        .collect(Collectors.toSet());

    return catMapper.toDtoList(new ArrayList<>(hungryCats));
  }

  @Override
  public List<CatDto> allCats() {
    List<Cat> cats = (List<Cat>) catCrudRepository.findAll();
    if (cats.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME, 0);
    }
    return catMapper.toDtoList(cats);
  }
}
