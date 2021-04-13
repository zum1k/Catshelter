package io.kontur.utils.resource;

import io.kontur.controller.UserController;
import io.kontur.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserLinkModifier implements DtoLinkModifier<UserDto> {
  private static final UserController controller = WebMvcLinkBuilder.methodOn(UserController.class);

  @Override
  public void withTagLocation(UserDto userDto) {
    long dtoId = userDto.getId();
    Link dtoLink = WebMvcLinkBuilder.linkTo(controller.findUser(dtoId)).withSelfRel();
    Link deleteLink = WebMvcLinkBuilder.linkTo(controller.deleteUserById(dtoId)).withRel("delete_user");
    userDto.add(dtoLink, deleteLink);
  }

  @Override
  public CollectionModel<UserDto> allWithPagination(List<UserDto> dtos) {
    CollectionModel<UserDto> model = CollectionModel.of(dtos);
    model.forEach(this::withTagLocation);
    return model;
  }
}
