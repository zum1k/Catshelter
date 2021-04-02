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
    Link dtoLink = WebMvcLinkBuilder.linkTo(controller.findTagById(dtoId)).withSelfRel();
    Link deleteLink = WebMvcLinkBuilder.linkTo(controller.deleteTagById(dtoId)).withRel("delete_cat");
    catDto.add(dtoLink, deleteLink);
  }

  @Override
  public CollectionModel<CatDto> allWithPagination(List<CatDto> dtos) {
    CollectionModel<CatDto> model = CollectionModel.of(dtos);
//    int page = dto.getPage();
//    int pageAmount = (int) service.count(dto);
//    if (pageAmount != 0) {
//      dto.setPage(FIRST_PAGE);
//      Link firstPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CatController.class).
//          findAll(dto.getPage(), dto.getPageLimit())).withRel("first");
//      model.add(firstPage.expand());
//
//      dto.setPage(pageAmount);
//      Link lastPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CatController.class).
//          findAll(dto.getPage(), dto.getPageLimit())).withRel("last");
//      model.add(lastPage.expand());
//
//      if (dto.getPage() != 1) {
//        dto.setPage(dto.getPage() - 1);
//        Link prevPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CatController.class).
//            findAll(dto.getPage(), dto.getPageLimit()))
//            .withRel("prev");
//        model.add(prevPage.expand());
//      }
//
//      if (page != pageAmount) {
//        dto.setPage(page + 1);
//        Link nextPage = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class).
//            findAll(dto.getPage(), dto.getPageLimit()))
//            .withRel("next");
//        model.add(nextPage.expand());
//      }
//    }
    model.forEach(this::withTagLocation);
    return model;
  }
}
