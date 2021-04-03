package io.kontur.utils.resource;

import io.kontur.service.dto.FeedingDto;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

public class FeedingLinkModifier implements DtoLinkModifier<FeedingDto> {
  @Override
  public void withTagLocation(FeedingDto feedingDto) {

  }

  @Override
  public CollectionModel<FeedingDto> allWithPagination(List<FeedingDto> dtos) {
    return null;
  }
}
