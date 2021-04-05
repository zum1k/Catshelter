package io.kontur.utils.resource;

import io.kontur.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserLinkModifier implements DtoLinkModifier<UserDto> {
  @Override
  public void withTagLocation(UserDto userDto) {
  }

  @Override
  public CollectionModel<UserDto> allWithPagination(List<UserDto> dtos) {
    return null;
  }
}
