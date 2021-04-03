package io.kontur.utils.resource;

import io.kontur.service.dto.UserDto;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

public class UserLinkModifier implements DtoLinkModifier<UserDto> {
  @Override
  public void withTagLocation(UserDto userDto) {

  }

  @Override
  public CollectionModel<UserDto> allWithPagination(List<UserDto> dtos) {
    return null;
  }
}
