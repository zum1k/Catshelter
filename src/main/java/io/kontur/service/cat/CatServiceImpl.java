package io.kontur.service.cat;

import io.kontur.entity.Cat;
import io.kontur.exception.EntityNotFoundException;
import io.kontur.repository.Repository;
import io.kontur.repository.specification.CatByNameSpecification;
import io.kontur.repository.specification.CriteriaSpecification;
import io.kontur.service.dto.CatDto;
import io.kontur.utils.mapper.CatMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class CatServiceImpl implements CatService {
  private static final String ENTITY_NAME = "Cat";
  private final Repository<Cat> repository;
  private final CatMapper catMapper;

  @Transactional
  @Override
  public CatDto create(CatDto dto) {
    log.info("add cat");
    CriteriaSpecification<Cat> specification = new CatByNameSpecification(dto.getName());
    Optional<Cat> catOptional = repository.findBySpecification(specification);
    if (catOptional.isEmpty()) {
      return catMapper.toDto(repository.create(catMapper.toEntity(dto)).get());
    }
    return catMapper.toDto(catOptional.get());
  }

  @Override
  public CatDto read(long id) {
    log.info("find cat {}", id);
    Cat cat = repository.read(id);
    return catMapper.toDto(cat);
  }

  @Override
  @Transactional
  public CatDto delete(long id) {
    log.info("delete cat {}", id);
    Optional<Cat> catOptional = repository.remove(id);
    return catMapper.toDto(catOptional.orElseThrow(() -> new EntityNotFoundException(ENTITY_NAME, id)));
  }

  @Override
  public List<CatDto> findHungryCats() {
    return null;
  }

  @Override
  public List<CatDto> allCats() {
    List<Cat> cats = repository.readAll();
    if (cats.isEmpty()) {
      throw new EntityNotFoundException(ENTITY_NAME, 0);
    }
    return catMapper.toDtoList(cats);
  }
}
