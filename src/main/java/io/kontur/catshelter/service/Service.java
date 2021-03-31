package io.kontur.catshelter.service;

import io.kontur.catshelter.service.dto.AbstractDto;

public interface Service<T extends AbstractDto> {
  T create();

  T read(long id);

  T update(long id, T dto);

  T delete(long id);
}
