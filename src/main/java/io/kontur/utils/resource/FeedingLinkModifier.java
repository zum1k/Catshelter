package io.kontur.utils.resource;

import io.kontur.service.dto.FeedingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeedingLinkModifier implements DtoLinkModifier<FeedingDto> {
  @Override
  public void withTagLocation(FeedingDto feedingDto) {
  }

  @Override
  public CollectionModel<FeedingDto> allWithPagination(List<FeedingDto> dtos) {
    return null;
  }
}
