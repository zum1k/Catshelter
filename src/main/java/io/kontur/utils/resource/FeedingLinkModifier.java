package io.kontur.utils.resource;

import io.kontur.controller.FeedingController;
import io.kontur.service.dto.FeedingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeedingLinkModifier implements DtoLinkModifier<FeedingDto> {
  private static final FeedingController controller = WebMvcLinkBuilder.methodOn(FeedingController.class);

  @Override
  public void withTagLocation(FeedingDto feedingDto) {
    long dtoId = feedingDto.getId();
    Link dtoLink = WebMvcLinkBuilder.linkTo(controller.findFeedingByUser(dtoId)).withSelfRel();
    Link deleteLink = WebMvcLinkBuilder.linkTo(controller.findAll()).withRel("find all feedings");
    feedingDto.add(dtoLink, deleteLink);
  }

  @Override
  public CollectionModel<FeedingDto> allWithPagination(List<FeedingDto> dtos) {
    CollectionModel<FeedingDto> model = CollectionModel.of(dtos);
    model.forEach(this::withTagLocation);
    return model;
  }
}
