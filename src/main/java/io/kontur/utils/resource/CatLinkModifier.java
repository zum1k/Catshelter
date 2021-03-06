package io.kontur.utils.resource;

import io.kontur.controller.CatController;
import io.kontur.service.cat.CatService;
import io.kontur.service.dto.CatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CatLinkModifier implements DtoLinkModifier<CatDto> {
  private static final int FIRST_PAGE = 1;
  private static final CatController controller = WebMvcLinkBuilder.methodOn(CatController.class);
  private final CatService service;

  @Override
  public void withTagLocation(CatDto catDto) {
    long dtoId = catDto.getId();
    Link dtoLink = WebMvcLinkBuilder.linkTo(controller.findCatById(dtoId)).withSelfRel();
    Link deleteLink = WebMvcLinkBuilder.linkTo(controller.deleteCatById(dtoId)).withRel("delete_cat");
    catDto.add(dtoLink, deleteLink);
  }

  @Override
  public CollectionModel<CatDto> allWithPagination(List<CatDto> dtos) {
    CollectionModel<CatDto> model = CollectionModel.of(dtos);
    model.forEach(this::withTagLocation);
    return model;
  }
}
