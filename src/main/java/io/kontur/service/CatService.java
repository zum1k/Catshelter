package io.kontur.service;

import io.kontur.service.dto.CatDto;

import java.util.List;
import java.util.Optional;

public interface CatService {
  CatDto create(CatDto dto);

  CatDto read(long id);

  CatDto delete(long id);

  List<CatDto> findHungryCats();

}
