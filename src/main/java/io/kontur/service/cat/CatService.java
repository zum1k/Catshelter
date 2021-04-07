package io.kontur.service.cat;

import io.kontur.service.dto.CatDto;

import java.util.List;

public interface CatService {
  CatDto create(CatDto dto);

  CatDto read(int id);

  CatDto delete(int id);

  List<CatDto> findHungryCats();

  List<CatDto> allCats();

}
