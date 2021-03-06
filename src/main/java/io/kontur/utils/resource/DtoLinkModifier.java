package io.kontur.utils.resource;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public interface DtoLinkModifier<T extends RepresentationModel<T>> {
  void withTagLocation(T t);

  CollectionModel<T> allWithPagination(List<T> dtos);
}
