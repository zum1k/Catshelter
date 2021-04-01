package io.kontur.service;

import io.kontur.service.dto.AbstractDto;

public interface Service<T extends AbstractDto> {
  T create();

  T read(long id);

  T update(long id, T dto);

  T delete(long id);
}
