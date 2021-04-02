package io.kontur.service;

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

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class CatServiceImpl implements CatService {
  private static final String ENTITY_NAME = "Cat";
  private final Repository<Cat> repository;
  private final CatMapper catMapper;

  @Override
  public CatDto create(CatDto dto) {
    log.info("add cat");
    CriteriaSpecification<Cat> specification = new CatByNameSpecification(dto.getName());
    Optional<Cat> catOptional = repository.findBySpecification(specification);
    if (catOptional.isEmpty()) {
      dto.setId(0L);
      return catMapper.toDto(repository.create(catMapper.toEntity(dto)).get());
    }
    return catMapper.toDto(catOptional.get());
  }

  @Override
  public CatDto read(long id) {
    Optional<Cat> catOptional = repository.read(id);
    if(catOptional.isEmpty()){
      throw new EntityNotFoundException(ENTITY_NAME, id);
    }
    return catMapper.toDto(catOptional.get());
  }

  @Override
  public CatDto delete(long id) {
    return null;
  }

  @Override
  public List<CatDto> findHungryCats() {
    return null;
  }
}
