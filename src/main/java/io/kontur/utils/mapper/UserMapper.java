package io.kontur.utils.mapper;

import io.kontur.entity.User;
import io.kontur.service.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
  @Mapping(target = "feedings", ignore = true)
  User toEntity(UserDto dto);

  UserDto toDto(User user);

  List<UserDto> toDtoList(List<User> users);
}
